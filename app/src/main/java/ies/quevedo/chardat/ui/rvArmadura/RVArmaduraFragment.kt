package ies.quevedo.chardat.ui.rvArmadura

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
import ies.quevedo.chardat.databinding.FragmentArmadurasBinding
import ies.quevedo.chardat.domain.Armadura
import ies.quevedo.chardat.domain.Personaje

@AndroidEntryPoint
class RVArmaduraFragment : Fragment() {

    private val viewModel by viewModels<RVArmaduraViewModel>()
    private lateinit var adapter: RVArmaduraAdapter
    private lateinit var personaje: Personaje
    private var armaduraActualizada: Armadura? = null
    private var armaduraCreada: Armadura? = null
    private var _binding: FragmentArmadurasBinding? = null
    private val binding get() = _binding!!

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
        personaje = arguments?.getParcelable("personaje")!!
        armaduraCreada = arguments?.getParcelable("armaduraCreada")
        if (armaduraCreada != null) {
            viewModel.insertArmadura(armaduraCreada!!)
            findNavController().popBackStack(R.id.addArmaduraFragment, true)
        }
        armaduraActualizada = arguments?.getParcelable("armaduraActualizada")
        if (armaduraActualizada != null) {
            viewModel.updateArmadura(armaduraActualizada!!)
            findNavController().popBackStack(R.id.armaduraFragment, true)
        }
        adapter = RVArmaduraAdapter(
            ::goArmaduraDetails
        )
        binding.rvArmaduras.adapter = adapter
        binding.fbtRegister.setOnClickListener {
            val bundle = Bundle()
            bundle.putParcelable("personaje", personaje)
            findNavController().navigate(
                R.id.action_RVArmaduraFragment_to_addArmaduraFragment,
                bundle
            )
        }
        observersRecyclerArmaduras()
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
                    val armadura = adapter.currentList[viewHolder.adapterPosition]
                    viewModel.deleteArmadura(armadura)
                    Snackbar.make(
                        binding.root,
                        "Se ha eliminado: ${armadura.name}",
                        Snackbar.LENGTH_LONG
                    ).setAction("Deshacer") {
                        viewModel.insertArmadura(armadura)
                        adapter.notifyItemInserted(viewHolder.adapterPosition)
                        adapter.notifyDataSetChanged()
                    }.show()
                }
            }).attachToRecyclerView(binding.rvArmaduras)
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
                // TODO: Implementar filtro de armaduras
                return false
            }
        })
    }

    private fun goArmaduraDetails(position: Int) {
        val armadura = viewModel.armaduras.value?.get(position)
        if (armadura != null) {
            val bundle = Bundle()
            bundle.putParcelable("armadura", armadura)
            bundle.putParcelable("personaje", personaje)
            findNavController().navigate(R.id.action_RVArmaduraFragment_to_armaduraFragment, bundle)
        } else {
            Toast.makeText(context, "No se ha podido obtener la armadura", Toast.LENGTH_SHORT)
                .show()
        }
    }

    private fun observersRecyclerArmaduras() {
        viewModel.armaduras.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
        viewModel.error.observe(viewLifecycleOwner) {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        }
        viewModel.getArmaduras(personaje.id)
    }
}