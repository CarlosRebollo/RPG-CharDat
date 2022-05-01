package ies.quevedo.chardat.framework.objeto

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
import ies.quevedo.chardat.domain.model.Objeto
import ies.quevedo.chardat.domain.model.Personaje

@AndroidEntryPoint
class RVObjetoFragment : Fragment() {

    private val viewModel by viewModels<ObjetoViewModel>()
    private lateinit var adapter: RVObjetoAdapter
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
        adapter = RVObjetoAdapter(
            ::goObjectDetails
        )
        binding.rvObjetos.adapter = adapter
        binding.fbtRegister.setOnClickListener {
            findNavController().navigate(R.id.action_RVObjetoFragment_to_addObjetoFragment)
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
                    val objeto = adapter.currentList[viewHolder.adapterPosition]
                    Snackbar.make(
                        binding.root,
                        "Se ha eliminado: ${objeto.name}",
                        Snackbar.LENGTH_LONG
                    ).setAction("Deshacer") {
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
                return false
            }
        })
    }

    private fun goObjectDetails(position: Int) {
        val objeto = adapter.currentList[position]
        if (objeto != null) {
            findNavController().navigate(R.id.action_RVObjetoFragment_to_objetoFragment)
        } else {
            Toast.makeText(context, "No se ha podido obtener el objeto", Toast.LENGTH_SHORT).show()
        }
    }
}