package ies.quevedo.chardat.framework.fragmentListArmaduras

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
import ies.quevedo.chardat.databinding.FragmentArmadurasBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RVArmaduraFragment : Fragment() {

    private val viewModel by viewModels<RVArmaduraViewModel>()
    private lateinit var adapter: RVArmaduraAdapter
    private var _binding: FragmentArmadurasBinding? = null
    private val binding get() = _binding!!
    private var idPersonaje: Int = arguments?.getInt("idPersonaje") ?: 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentArmadurasBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val layoutManager = LinearLayoutManager(context)
        binding.rvArmaduras.addItemDecoration(
            DividerItemDecoration(
                binding.rvArmaduras.context,
                layoutManager.orientation
            )
        )
        adapter = RVArmaduraAdapter(
            ::goArmaduraDetails
        )
        binding.rvArmaduras.adapter = adapter
        pedirArmadurasDelPersonaje()
        binding.fbtRegister.setOnClickListener {
            val action = RVArmaduraFragmentDirections.actionRVArmaduraFragmentToAddArmaduraFragment(
                idPersonaje
            )
            findNavController().navigate(action)
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

    private fun pedirArmadurasDelPersonaje() {
        viewModel.handleEvent(ArmaduraListContract.Event.FetchArmaduras(idPersonaje))
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.uiState.collect { value ->
                binding.loading.visibility = if (value.isLoading) View.VISIBLE else View.GONE
                if (value.listaArmaduras != null) {
                    adapter.submitList(value.listaArmaduras)
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
                    val armadura = adapter.currentList[viewHolder.absoluteAdapterPosition]
                    viewModel.handleEvent(ArmaduraListContract.Event.DeleteArmadura(armadura.id))
                    viewLifecycleOwner.lifecycleScope.launch {
                        viewModel.uiState.collect { value ->
                            if (value.armaduraBorrada != null) {
                                pedirArmadurasDelPersonaje()
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
                        "Se ha eliminado: ${armadura.name}",
                        Snackbar.LENGTH_LONG
                    ).setAction("Deshacer") {
                        viewModel.handleEvent(ArmaduraListContract.Event.PostArmadura(armadura))
                        viewLifecycleOwner.lifecycleScope.launch {
                            viewModel.uiState.collect { value ->
                                if (value.armaduraRecuperada != null) {
                                    pedirArmadurasDelPersonaje()
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
            }).attachToRecyclerView(binding.rvArmaduras)
        }
    }

    private fun goArmaduraDetails(position: Int) {
        val armadura = adapter.currentList[position]
        if (armadura != null) {
            val action =
                RVArmaduraFragmentDirections.actionRVArmaduraFragmentToArmaduraFragment(
                    armadura.id,
                    idPersonaje
                )
            findNavController().navigate(action)
        } else {
            Toast.makeText(context, "No se ha podido obtener la armadura", Toast.LENGTH_SHORT)
                .show()
        }
    }
}