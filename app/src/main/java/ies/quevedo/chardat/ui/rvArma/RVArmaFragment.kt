package ies.quevedo.chardat.ui.rvArma

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
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
    private var arma: Arma? = null
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
        arma = arguments?.getParcelable("arma")
        if (arma != null) {
            viewModel.updateArma(arma!!)
            // Con esto evito que se pueda volver atrás al fragment de update de armas
            findNavController().popBackStack(R.id.armaFragment, true)
        }
        adapter = RVArmaAdapter(
            ::goWeaponDetails
        )
        binding.rvArmas.adapter = adapter
        observersRecyclerArmas()
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
                // TODO: Implementar filtro
                return false
            }
        })
        val addPersonaje = menu.findItem(R.id.register)
        addPersonaje.setOnMenuItemClickListener {
            goAddArma()
            true
        }
    }

    private fun goAddArma() {
        val navController = view?.findNavController()
        navController?.navigate(R.id.action_RVArmaFragment_to_armaFragment)
        //TODO: El action está mal, hay que crear la vista de registro de armas
    }

    private fun goWeaponDetails(position: Int) {
        val arma = viewModel.armas.value?.get(position)
        if (arma != null) {
            val bundle = Bundle()
            bundle.putParcelable("arma", arma)
            bundle.putParcelable("personaje", personaje)
            val navController = view?.findNavController()
            navController?.navigate(R.id.action_RVArmaFragment_to_armaFragment, bundle)
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