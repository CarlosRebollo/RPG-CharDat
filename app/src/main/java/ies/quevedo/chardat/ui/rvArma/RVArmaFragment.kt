package ies.quevedo.chardat.ui.rvArma

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
import ies.quevedo.chardat.databinding.FragmentArmasBinding
import ies.quevedo.chardat.domain.Arma
import ies.quevedo.chardat.domain.Personaje

@AndroidEntryPoint
class RVArmaFragment : Fragment() {

    private val viewModel by viewModels<RVArmaViewModel>()
    private lateinit var adapter: RVArmaAdapter
    private lateinit var personaje: Personaje
    private var armaActualizada: Arma? = null
    private var armaCreada: Arma? = null
    private var _binding: FragmentArmasBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentArmasBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        personaje = arguments?.getParcelable("personaje")!!
        armaCreada = arguments?.getParcelable("armaCreada")
        if (armaCreada != null) {
            viewModel.insertArma(armaCreada!!)
            findNavController().popBackStack(R.id.addArmaFragment, true)
        }
        armaActualizada = arguments?.getParcelable("armaActualizada")
        if (armaActualizada != null) {
            viewModel.updateArma(armaActualizada!!)
            findNavController().popBackStack(R.id.armaFragment, true)
        }
        adapter = RVArmaAdapter(
            ::goWeaponDetails
        )
        binding.rvArmas.adapter = adapter
        binding.fbtRegister.setOnClickListener {
            val bundle = Bundle()
            bundle.putParcelable("personaje", personaje)
            findNavController().navigate(R.id.action_RVArmaFragment_to_addArmaFragment, bundle)
        }
        observersRecyclerArmas()
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
                override fun onSwiped(
                    viewHolder: RecyclerView.ViewHolder,
                    direction: Int
                ) {
                    val arma = adapter.currentList[viewHolder.adapterPosition]
                    viewModel.deleteArma(arma)
                    Snackbar.make(
                        binding.root,
                        "Se ha eliminado: ${arma.name}",
                        Snackbar.LENGTH_LONG
                    ).setAction("Deshacer") {
                        viewModel.insertArma(arma)
                        adapter.notifyItemInserted(viewHolder.adapterPosition)
                        adapter.notifyDataSetChanged()
                    }.show()
                }
            }).attachToRecyclerView(binding.rvArmas)
        }
    }

    override fun onCreateOptionsMenu(menu: android.view.Menu, inflater: android.view.MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_item, menu)
        val actionSearch = menu.findItem(R.id.filter).actionView as SearchView
        actionSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                // TODO: Implementar filtro de armas
                return false
            }
        })
    }

    private fun goWeaponDetails(position: Int) {
        val arma = viewModel.armas.value?.get(position)
        if (arma != null) {
            val bundle = Bundle()
            bundle.putParcelable("arma", arma)
            bundle.putParcelable("personaje", personaje)
            findNavController().navigate(R.id.action_RVArmaFragment_to_armaFragment, bundle)
        } else {
            Toast.makeText(context, "No se ha podido obtener el arma", Toast.LENGTH_SHORT).show()
        }
    }

    private fun observersRecyclerArmas() {
        viewModel.armas.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
        viewModel.error.observe(viewLifecycleOwner) {
            Toast.makeText(context, it, Toast.LENGTH_LONG).show()
        }
        viewModel.getArmas(personaje.id)
    }
}