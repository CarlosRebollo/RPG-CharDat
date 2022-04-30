package ies.quevedo.chardat.ui.rvPersonaje

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import ies.quevedo.chardat.R
import ies.quevedo.chardat.databinding.FragmentPersonajesBinding
import ies.quevedo.chardat.domain.Personaje
import java.util.*

@AndroidEntryPoint
class RVPersonajeFragment : Fragment() {

    private val viewModel by viewModels<RVPersonajeViewModel>()
    private lateinit var adapter: RVPersonajeAdapter
    private var personajeCreado: Personaje? = null
    private lateinit var personajesList: ArrayList<Personaje>
    private lateinit var personajesListTemp: ArrayList<Personaje>
    private var _binding: FragmentPersonajesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPersonajesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        personajeCreado = arguments?.getParcelable("personajeCreado")
        if (personajeCreado != null) {
            viewModel.insertPersonaje(personajeCreado!!)

        }
        adapter = RVPersonajeAdapter(
            ::goMainMenu
        )
        binding.rvPersonajes.adapter = adapter
        binding.fbtRegister.setOnClickListener {
            findNavController().navigate(R.id.action_RVPersonajeFragment_to_addPersonajeFragment1)
        }
        /*Guardar los personajes en una lista temporal ¿De donde puedo cogerlos bien?
        personajesList = (viewModel.personajes.value as ArrayList<Personaje>?)!!
        personajesListTemp = personajesList*/
        observersRecyclerPersonajes()
        swipeToDelete()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_item, menu)
        val search = menu.findItem(R.id.filter)
        val searchView = search?.actionView as? SearchView
        searchView?.maxWidth = Int.MAX_VALUE
        searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(filtro: String): Boolean {
                /*personajesList.clear()
                if (filtro.isBlank()) {
                    personajesList.addAll(personajesListTemp)
                } else {
                    personajesList.addAll(
                        personajesListTemp.filter {
                            it.name.lowercase(Locale.ROOT)
                                .contains(filtro.lowercase(Locale.ROOT))
                        }
                    )
                }
                refreshList()*/
                return true
            }

            override fun onQueryTextChange(filtro: String): Boolean {
                /*personajesList.clear()
                if (filtro.isBlank()) {
                    personajesList.addAll(personajesListTemp)
                } else {
                    personajesList.addAll(
                        personajesListTemp.filter {
                            it.name.lowercase(Locale.ROOT)
                                .contains(filtro.lowercase(Locale.ROOT))
                        }
                    )
                }
                refreshList()*/
                return true
            }
        })
        searchView?.isSubmitButtonEnabled = true
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun refreshList() {
        binding.rvPersonajes.adapter?.notifyDataSetChanged()
    }

    private fun swipeToDelete() {
        binding.apply {
            ItemTouchHelper(object :
                ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
                override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    target: RecyclerView.ViewHolder
                ): Boolean {
                    return false
                }

                @SuppressLint("NotifyDataSetChanged")
                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    val personaje = adapter.currentList[viewHolder.adapterPosition]
                    viewModel.deletePersonaje(personaje)
                    Snackbar.make(
                        binding.root,
                        "Se ha eliminado: ${personaje.name.uppercase(Locale.getDefault())}",
                        Snackbar.LENGTH_LONG
                    ).setAction("Deshacer") {
                        viewModel.insertPersonajeConTodo(personaje)
                        adapter.notifyItemInserted(viewHolder.adapterPosition)
                        adapter.notifyDataSetChanged()
                    }.show()
                }
            }).attachToRecyclerView(binding.rvPersonajes)
        }
    }

    private fun goMainMenu(position: Int) {
        val personaje = viewModel.personajes.value?.get(position)
        if (personaje != null) {
            val action = RVPersonajeFragmentDirections
                .actionRVPersonajeFragmentToMainMenuFragment(personaje)
            findNavController().navigate(action)
        } else {
            Toast.makeText(context, "No se ha podido obtener el personaje", Toast.LENGTH_SHORT)
                .show()
        }
    }

    private fun observersRecyclerPersonajes() {
        viewModel.personajes.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
        viewModel.error.observe(viewLifecycleOwner) {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
            Log.d("Error", it)
        }
        viewModel.getPersonajes()
    }
}