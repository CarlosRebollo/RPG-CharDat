package ies.quevedo.chardat.framework.mainMenu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import ies.quevedo.chardat.R
import ies.quevedo.chardat.databinding.FragmentMainMenuBinding

@AndroidEntryPoint
class MainMenuFragment : Fragment() {

    private val viewModel by viewModels<MainMenuViewModel>()
    private var _binding: FragmentMainMenuBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setHasOptionsMenu(true)
        _binding = FragmentMainMenuBinding.inflate(inflater, container, false)
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
//            setImageClass()
//            tvName.text = personaje.name
//            tvClase.text = personaje.clase
            ivInfo.setOnClickListener {
                findNavController().navigate(R.id.action_mainMenuFragment_to_personajeFragment)
            }
            ivArmas.setOnClickListener {
                findNavController().navigate(R.id.action_mainMenuFragment_to_RVArmaFragment)
            }
            ivArmaduras.setOnClickListener {
                findNavController().navigate(R.id.action_mainMenuFragment_to_RVArmaduraFragment)
            }
            ivEscudos.setOnClickListener {
                findNavController().navigate(R.id.action_mainMenuFragment_to_RVEscudoFragment)
            }
            ivObjetos.setOnClickListener {
                findNavController().navigate(R.id.action_mainMenuFragment_to_RVObjetoFragment)
            }
        }
    }

    /*private fun setImageClass() {
        when (personaje.clase) {
            "GUERRERO" -> binding.ivClaseBanner.setImageResource(R.drawable.guerrero_banner)
            "GUERRERO ACRÓBATA" -> binding.ivClaseBanner.setImageResource(R.drawable.guerrero_acrobata_banner)
            "PALADÍN" -> binding.ivClaseBanner.setImageResource(R.drawable.paladin_banner)
            "PALADÍN OSCURO" -> binding.ivClaseBanner.setImageResource(R.drawable.paladin_oscuro_banner)
            "MAESTRO DE ARMAS" -> binding.ivClaseBanner.setImageResource(R.drawable.maestro_de_armas_banner)
            "TECNICISTA" -> binding.ivClaseBanner.setImageResource(R.drawable.tecnicista_banner)
            "TAO" -> binding.ivClaseBanner.setImageResource(R.drawable.tao_banner)
            "EXPLORADOR" -> binding.ivClaseBanner.setImageResource(R.drawable.explorador_banner)
            "SOMBRA" -> binding.ivClaseBanner.setImageResource(R.drawable.sombra_banner)
            "LADRÓN" -> binding.ivClaseBanner.setImageResource(R.drawable.ladron_banner)
            "ASESINO" -> binding.ivClaseBanner.setImageResource(R.drawable.asesino_banner)
            "HECHICERO" -> binding.ivClaseBanner.setImageResource(R.drawable.hechicero_banner)
            "WARLOCK" -> binding.ivClaseBanner.setImageResource(R.drawable.warlock_banner)
            "ILUSIONISTA" -> binding.ivClaseBanner.setImageResource(R.drawable.ilusionista_banner)
            "HECHICERO MENTALISTA" -> binding.ivClaseBanner.setImageResource(R.drawable.hechicero_mentalista_banner)
            "CONJURADOR" -> binding.ivClaseBanner.setImageResource(R.drawable.conjurador_banner)
            "GUERRERO CONJURADOR" -> binding.ivClaseBanner.setImageResource(R.drawable.guerrero_conjurador_banner)
            "MENTALISTA" -> binding.ivClaseBanner.setImageResource(R.drawable.mentalista_banner)
            "GUERRERO MENTALISTA" -> binding.ivClaseBanner.setImageResource(R.drawable.guerrero_mentalista_banner)
            "NOVEL" -> binding.ivClaseBanner.setImageResource(R.drawable.novel_banner)
        }
    }*/
}