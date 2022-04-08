package ies.quevedo.chardat.ui.mainMenu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import ies.quevedo.chardat.MainMenuFragmentDirections
import ies.quevedo.chardat.databinding.FragmentMainMenuBinding
import ies.quevedo.chardat.domain.Personaje

class MainMenuFragment : Fragment() {

    private var _binding: FragmentMainMenuBinding? = null
    private val binding get() = _binding!!
    private lateinit var personaje: Personaje

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainMenuBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        personaje = arguments?.getParcelable("personaje")!!
        with(binding) {
            tvName.text = personaje.name
            // TODO: Aquí meter la imagen de la clase del personaje
            ivInfo.setOnClickListener {
                val action = MainMenuFragmentDirections
                    .actionMainMenuFragmentToPersonajeFragment(personaje)
                findNavController().navigate(action)
            }
            ivArmas.setOnClickListener {
                val action = MainMenuFragmentDirections
                    .actionMainMenuFragmentToRVArmaFragment(personaje)
                findNavController().navigate(action)
            }
            // TODO: Aquí seguir haciendo onClickListener para los demás botones
        }
    }
}