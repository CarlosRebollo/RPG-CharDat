package ies.quevedo.chardat.ui.mainMenu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import ies.quevedo.chardat.databinding.FragmentMainMenuBinding
import ies.quevedo.chardat.domain.Personaje

@AndroidEntryPoint
class MainMenuFragment : Fragment() {

    private var _binding: FragmentMainMenuBinding? = null
    private val binding get() = _binding!!
    private lateinit var personaje: Personaje

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setHasOptionsMenu(true)
        _binding = FragmentMainMenuBinding.inflate(inflater, container, false)
        personaje = arguments?.getParcelable("personaje")!!
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListenerActions()
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)
        menu.clear()
    }

    private fun setListenerActions() {
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
            ivArmaduras.setOnClickListener {
                Toast.makeText(context, "En desarrollo", Toast.LENGTH_SHORT).show()
            }
            ivEscudos.setOnClickListener {
                Toast.makeText(context, "En desarrollo", Toast.LENGTH_SHORT).show()
            }
            ivObjetos.setOnClickListener {
                Toast.makeText(context, "En desarrollo", Toast.LENGTH_SHORT).show()
            }
        }
    }
}