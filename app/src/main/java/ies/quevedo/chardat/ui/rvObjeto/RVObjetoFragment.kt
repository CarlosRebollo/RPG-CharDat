package ies.quevedo.chardat.ui.rvObjeto

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
import ies.quevedo.chardat.databinding.FragmentObjetosBinding
import ies.quevedo.chardat.domain.Objeto
import ies.quevedo.chardat.domain.Personaje

@AndroidEntryPoint
class RVObjetoFragment : Fragment() {

    private val viewModel by viewModels<RVObjetoViewModel>()
    private lateinit var adapter: RVObjetoAdapter
    private lateinit var personaje: Personaje
    private var objetoActualizado: Objeto? = null
    private var objetoCreado: Objeto? = null
    private var _binding: FragmentObjetosBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentObjetosBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        personaje = arguments?.getParcelable("personaje")!!
        objetoCreado = arguments?.getParcelable("objetoCreado")
        if (objetoCreado != null) {
            viewModel.insertObjeto(objetoCreado!!)
            findNavController().popBackStack(R.id.addObjetoFragment, true)
        }
        objetoActualizado = arguments?.getParcelable("objetoActualizado")
        if (objetoActualizado != null) {
            viewModel.updateObjeto(objetoActualizado!!)
            findNavController().popBackStack(R.id.objetoFragment, true)
        }
        adapter = RVObjetoAdapter(
            ::goObjectDetails
        )
        binding.rvObjetos.adapter = adapter
        binding.fbtRegister.setOnClickListener {
            val bundle = Bundle()
            bundle.putParcelable("personaje", personaje)
            findNavController().navigate(R.id.action_RVObjetoFragment_to_addObjetoFragment, bundle)
        }
        observersRecyclerEscudos()
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
                    val objeto = adapter.currentList[viewHolder.adapterPosition]
                    viewModel.deleteObjeto(objeto)
                    Snackbar.make(
                        binding.root,
                        "${objeto.name} eliminado",
                        Snackbar.LENGTH_LONG
                    ).setAction("Deshacer") {
                        viewModel.insertObjeto(objeto)
                        adapter.notifyItemInserted(viewHolder.adapterPosition)
                        adapter.notifyDataSetChanged()
                    }.show()
                }
            }).attachToRecyclerView(binding.rvObjetos)
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
                // TODO: Implementar filtro de objetos
                return false
            }
        })
    }

    private fun goObjectDetails(position: Int) {
        val objeto = viewModel.objetos.value?.get(position)
        if (objeto != null) {
            val bundle = Bundle()
            bundle.putParcelable("objeto", objeto)
            bundle.putParcelable("personaje", personaje)
            findNavController().navigate(R.id.action_RVObjetoFragment_to_objetoFragment, bundle)
        } else {
            Toast.makeText(context, "No se ha podido obtener el objeto", Toast.LENGTH_SHORT).show()
        }
    }

    private fun observersRecyclerEscudos() {
        viewModel.objetos.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
        viewModel.error.observe(viewLifecycleOwner) {
            Toast.makeText(context, it, Toast.LENGTH_LONG).show()
        }
        viewModel.getObjetos(personaje.id)
    }
}