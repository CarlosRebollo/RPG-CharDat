package ies.quevedo.chardat.ui.rvEscudo

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
import ies.quevedo.chardat.databinding.FragmentEscudosBinding
import ies.quevedo.chardat.domain.Escudo
import ies.quevedo.chardat.domain.Personaje

@AndroidEntryPoint
class RVEscudoFragment : Fragment() {

    private val viewModel by viewModels<RVEscudoViewModel>()
    private lateinit var adapter: RVEscudoAdapter
    private lateinit var personaje: Personaje
    private var escudoActualizado: Escudo? = null
    private var escudoCreado: Escudo? = null
    private var _binding: FragmentEscudosBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEscudosBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        personaje = arguments?.getParcelable("personaje")!!
        escudoCreado = arguments?.getParcelable("escudoCreado")
        if (escudoCreado != null) {
            viewModel.insertEscudo(escudoCreado!!)
            findNavController().popBackStack(R.id.addEscudoFragment, true)
        }
        escudoActualizado = arguments?.getParcelable("escudoActualizado")
        if (escudoActualizado != null) {
            viewModel.updateEscudo(escudoActualizado!!)
            findNavController().popBackStack(R.id.escudoFragment, true)
        }
        adapter = RVEscudoAdapter(
            ::goShieldDetails
        )
        binding.rvEscudos.adapter = adapter
        binding.fbtRegister.setOnClickListener {
            val bundle = Bundle()
            bundle.putParcelable("personaje", personaje)
            findNavController().navigate(R.id.action_RVEscudoFragment_to_addEscudoFragment, bundle)
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
                    val escudo = adapter.currentList[viewHolder.adapterPosition]
                    viewModel.deleteEscudo(escudo)
                    Snackbar.make(
                        binding.root,
                        "${escudo.name} eliminado",
                        Snackbar.LENGTH_LONG
                    ).setAction("Deshacer") {
                        viewModel.insertEscudo(escudo)
                        adapter.notifyItemInserted(viewHolder.adapterPosition)
                        adapter.notifyDataSetChanged()
                    }.show()
                }
            }).attachToRecyclerView(binding.rvEscudos)
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

    private fun goShieldDetails(position: Int) {
        val escudo = viewModel.escudos.value?.get(position)
        if (escudo != null) {
            val bundle = Bundle()
            bundle.putParcelable("escudo", escudo)
            bundle.putParcelable("personaje", personaje)
            findNavController().navigate(R.id.action_RVEscudoFragment_to_escudoFragment, bundle)
        } else {
            Toast.makeText(context, "No se ha podido obtener el escudo", Toast.LENGTH_SHORT).show()
        }
    }

    private fun observersRecyclerEscudos() {
        viewModel.escudos.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
        viewModel.error.observe(viewLifecycleOwner) {
            Toast.makeText(context, it, Toast.LENGTH_LONG).show()
        }
        viewModel.getEscudos(personaje.id)
    }
}