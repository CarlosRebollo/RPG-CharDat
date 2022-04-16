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
import ies.quevedo.chardat.R
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
            setImageClass()
            tvName.text = personaje.name
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

    private fun FragmentMainMenuBinding.setImageClass() {
        //TODO : Aquí seguir probando en fragment_main_menu.xml la imagen para que salga la cara bien, poner en scrollY los dp del layout
        when (personaje.clase) {
            "GUERRERO" -> {
                imageView.setImageResource(R.drawable.guerrero)
                imageView.scrollY = -190
            }
            "GUERRERO ACRÓBATA" -> {
                imageView.setImageResource(R.drawable.guerrero_acr_bata)
                imageView.scrollY = -190
            }
            "PALADÍN" -> {
                imageView.setImageResource(R.drawable.palad_n)
                imageView.scrollY = -190
            }
            "PALADÍN OSCURO" -> {
                imageView.setImageResource(R.drawable.paladin_oscuro)
                imageView.scrollY = -130
            }
            "MAESTRO DE ARMAS" -> {
                imageView.setImageResource(R.drawable.maestro_de_armas)
                imageView.scrollY = 200
            }
            "TECNICISTA" -> imageView.setImageResource(R.drawable.tecnicista)
            "TAO" -> imageView.setImageResource(R.drawable.tao)
            "EXPLORADOR" -> imageView.setImageResource(R.drawable.explorador)
            "SOMBRA" -> imageView.setImageResource(R.drawable.sombra)
            "LADRÓN" -> imageView.setImageResource(R.drawable.ladr_n)
            "ASESINO" -> imageView.setImageResource(R.drawable.asesino)
            "HECHICERO" -> imageView.setImageResource(R.drawable.hechicero)
            "WARLOCK" -> imageView.setImageResource(R.drawable.warlock)
            "ILUSIONISTA" -> imageView.setImageResource(R.drawable.ilusionista)
            "HECHICERO MENTALISTA" -> imageView.setImageResource(R.drawable.hechicero_mentalista)
            "CONJURADOR" -> imageView.setImageResource(R.drawable.conjurador)
            "GUERRERO CONJURADOR" -> imageView.setImageResource(R.drawable.guerrero_conjurador)
            "MENTALISTA" -> imageView.setImageResource(R.drawable.mentalista)
            "GUERRERO MENTALISTA" -> imageView.setImageResource(R.drawable.guerrero_mentalista)
            "NOVEL" -> imageView.setImageResource(R.drawable.novel)
        }
    }
}