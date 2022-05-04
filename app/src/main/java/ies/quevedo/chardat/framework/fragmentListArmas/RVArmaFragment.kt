package ies.quevedo.chardat.framework.fragmentListArmas

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import ies.quevedo.chardat.R
import ies.quevedo.chardat.databinding.FragmentArmasBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RVArmaFragment : Fragment() {

    private val viewModel by viewModels<RVArmaViewModel>()
    private lateinit var adapter: RVArmaAdapter
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
        val layoutManager = LinearLayoutManager(context)
        binding.rvArmas.addItemDecoration(
            DividerItemDecoration(
                binding.rvArmas.context,
                layoutManager.orientation
            )
        )
        adapter = RVArmaAdapter(
            ::goWeaponDetails
        )
        binding.rvArmas.adapter = adapter
        val idPersonaje = arguments?.getInt("idPersonaje") ?: 0
        pedirArmasDelPersonaje(idPersonaje)
        binding.fbtRegister.setOnClickListener {
            findNavController().navigate(R.id.action_RVArmaFragment_to_addArmaFragment)
        }
        swipeToDelete()
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
                return false
            }
        })
    }

    private fun pedirArmasDelPersonaje(idPersonaje: Int) {
        viewModel.handleEvent(ArmaListContract.Event.FetchArmas(idPersonaje))
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.uiState.collect { value ->
                binding.loading.visibility = if (value.isLoading) View.VISIBLE else View.GONE
                if (value.listaArmas != null) {
                    adapter.submitList(value.listaArmas)
                }
            }
        }
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.uiError.collect {
                Toast.makeText(context, it, Toast.LENGTH_LONG).show()
            }
        }
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
                override fun onSwiped(
                    viewHolder: RecyclerView.ViewHolder,
                    direction: Int
                ) {
                    val arma = adapter.currentList[viewHolder.absoluteAdapterPosition]
                    viewModel.handleEvent(ArmaListContract.Event.DeleteArma(arma.id))
                    viewLifecycleOwner.lifecycleScope.launch {
                        viewModel.uiState.collect { value ->
                            if (value.arma != null) {
                                adapter.notifyDataSetChanged()
                            }
                        }
                    }
                    viewLifecycleOwner.lifecycleScope.launch {
                        viewModel.uiError.collect {
                            Toast.makeText(context, it, Toast.LENGTH_LONG).show()
                        }
                    }
                    Snackbar.make(
                        binding.root,
                        "Se ha eliminado: ${arma.name}",
                        Snackbar.LENGTH_LONG
                    ).setAction("Deshacer") {
                        viewModel.handleEvent(ArmaListContract.Event.PostArma(arma))
                        viewLifecycleOwner.lifecycleScope.launch {
                            viewModel.uiState.collect { value ->
                                if (value.arma != null) {
                                    adapter.notifyDataSetChanged()
                                }
                            }
                        }
                        viewLifecycleOwner.lifecycleScope.launch {
                            viewModel.uiError.collect {
                                Toast.makeText(context, it, Toast.LENGTH_LONG).show()
                            }
                        }
                    }.show()
                }
            }).attachToRecyclerView(binding.rvArmas)
        }
    }

    private fun goWeaponDetails(position: Int) {
        val arma = adapter.currentList[position]
        if (arma != null) {
            findNavController().navigate(R.id.action_RVArmaFragment_to_armaFragment)
        } else {
            Toast.makeText(context, "No se ha podido obtener el arma", Toast.LENGTH_SHORT).show()
        }
    }
}