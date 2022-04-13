package ies.quevedo.chardat.ui.rvPersonaje

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import ies.quevedo.chardat.R
import ies.quevedo.chardat.databinding.FragmentPersonajesListBinding
import ies.quevedo.chardat.domain.Personaje

@AndroidEntryPoint
class RVPersonajeFragment : Fragment() {

    private val viewModel by viewModels<RVPersonajeViewModel>()
    private lateinit var adapter: RVPersonajeAdapter
    private var personaje: Personaje? = null
    private var _binding: FragmentPersonajesListBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPersonajesListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        personaje = arguments?.getParcelable("personaje")
        if (personaje != null) {
            viewModel.insertPersonaje(personaje!!)
            findNavController().popBackStack(R.id.addPersonajeFragment1, true)
            findNavController().popBackStack(R.id.addPersonajeFragment2, true)
            findNavController().popBackStack(R.id.addPersonajeFragment3, true)
        }
        adapter = RVPersonajeAdapter(
            ::goMainMenu
        )
        binding.rvPersonajes.adapter = adapter
        binding.fbtRegister.setOnClickListener {
            findNavController().navigate(R.id.action_RVPersonajeFragment_to_addPersonajeFragment1)
        }
        observersRecyclerPersonajes()
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