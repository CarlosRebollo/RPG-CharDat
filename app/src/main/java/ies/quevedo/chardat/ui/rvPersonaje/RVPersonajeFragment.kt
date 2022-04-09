package ies.quevedo.chardat.ui.rvPersonaje

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import dagger.hilt.android.AndroidEntryPoint
import ies.quevedo.chardat.R
import ies.quevedo.chardat.databinding.FragmentPersonajesListBinding

@AndroidEntryPoint
class RVPersonajeFragment : Fragment() {

    private val viewModel by viewModels<RVPersonajeViewModel>()
    private lateinit var adapter: RVPersonajeAdapter
    private var _binding: FragmentPersonajesListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPersonajesListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = RVPersonajeAdapter(
            ::goMainMenu
        )
        binding.rvPersonajes.adapter = adapter
        observersRecyclerPersonajes()
    }

    //TODO -> No sale el puto menu
    override fun onCreateOptionsMenu(menu: android.view.Menu, inflater: android.view.MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_item, menu)
        val actionSearch = menu.findItem(R.id.filter).actionView as SearchView
        actionSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                // TODO: Implementar filtro
                return false
            }
        })
        val addPersonaje = menu.findItem(R.id.register)
        addPersonaje.setOnMenuItemClickListener {
            goAddPersonaje()
            true
        }
    }

    private fun goAddPersonaje() {
        val navController = view?.findNavController()
        navController?.navigate(R.id.action_RVPersonajeFragment_to_addPersonajeFragment)
    }

    private fun goMainMenu(position: Int) {
        val personaje = viewModel.personajes.value?.get(position)
        if (personaje != null) {
            val bundle = Bundle()
            bundle.putParcelable("personaje", personaje)
            val navController = view?.findNavController()
            navController?.navigate(R.id.action_RVPersonajeFragment_to_mainMenuFragment, bundle)
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
            Toast.makeText(context, it, Toast.LENGTH_LONG).show()
        }
        viewModel.getPersonajes()
    }
}