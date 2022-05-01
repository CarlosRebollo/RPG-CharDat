package ies.quevedo.chardat.framework.armadura

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
import ies.quevedo.chardat.domain.model.Armadura
import ies.quevedo.chardat.domain.model.Personaje

@AndroidEntryPoint
class RVArmaduraFragment : Fragment() {

    private val viewModel by viewModels<ArmaduraViewModel>()
    private lateinit var adapter: RVArmaduraAdapter
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
        adapter = RVArmaduraAdapter(
            ::goArmaduraDetails
        )
        binding.rvArmaduras.adapter = adapter
        binding.fbtRegister.setOnClickListener {
            findNavController().navigate(R.id.action_RVArmaduraFragment_to_addArmaduraFragment)
        }
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
                    Snackbar.make(
                        binding.root,
                        "Se ha eliminado: ${armadura.name}",
                        Snackbar.LENGTH_LONG
                    ).setAction("Deshacer") {
                        adapter.notifyItemInserted(viewHolder.absoluteAdapterPosition)
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
        val armadura = adapter.currentList[position]
        if (armadura != null) {
            findNavController().navigate(R.id.action_RVArmaduraFragment_to_armaduraFragment)
        } else {
            Toast.makeText(context, "No se ha podido obtener la armadura", Toast.LENGTH_SHORT)
                .show()
        }
    }
}