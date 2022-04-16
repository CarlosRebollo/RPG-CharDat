package ies.quevedo.chardat.ui.personaje

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import ies.quevedo.chardat.R
import ies.quevedo.chardat.databinding.FragmentAddPersonaje3Binding
import ies.quevedo.chardat.domain.Personaje
import java.time.LocalDate

@AndroidEntryPoint
class AddPersonajeFragment3 : Fragment() {

    private var _binding: FragmentAddPersonaje3Binding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddPersonaje3Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            btCancelar.setOnClickListener {
                activity?.onBackPressed()
                findNavController().popBackStack(R.id.addPersonajeFragment1, true)
                findNavController().popBackStack(R.id.addPersonajeFragment2, true)
            }
            btCrear.setOnClickListener {
                val personajeCreado = buildPersonaje()
                val bundle = Bundle()
                bundle.putParcelable("personajeCreado", personajeCreado)
                findNavController().navigate(
                    R.id.action_addPersonajeFragment3_to_RVPersonajeFragment,
                    bundle
                )
            }
        }
    }

    private fun FragmentAddPersonaje3Binding.buildPersonaje(): Personaje {
        val nombrePersonaje = arguments?.getString("nombrePersonaje")
        val clasePersonaje = arguments?.getString("clasePersonaje")
        val descripcionPersonaje = arguments?.getString("descripcionPersonaje")
        val agilidadPersonaje = arguments?.getInt("agilidadPersonaje")
        val constitucionPersonaje = arguments?.getInt("constitucionPersonaje")
        val destrezaPersonaje = arguments?.getInt("destrezaPersonaje")
        val fuerzaPersonaje = arguments?.getInt("fuerzaPersonaje")
        val inteligenciaPersonaje = arguments?.getInt("inteligenciaPersonaje")
        val percepcionPersonaje = arguments?.getInt("percepcionPersonaje")
        val poderPersonaje = arguments?.getInt("poderPersonaje")
        val voluntadPersonaje = arguments?.getInt("voluntadPersonaje")
        val nivelPersonaje = etNivel.text.toString().toInt()
        val habilidadDeAtaquePersonaje = etHabilidadDeAtaque.text.toString().toInt()
        val esquivaPersonaje = etEsquiva.text.toString().toInt()
        val paradaPersonaje = etParada.text.toString().toInt()
        val armaduraPersonaje = etArmadura.text.toString().toInt()
        val turnoPersonaje = etTurno.text.toString().toInt()
        val rfPersonaje = etRF.text.toString().toInt()
        val rmPersonaje = etRM.text.toString().toInt()
        val rpPersonaje = etRP.text.toString().toInt()
        val hpPersonaje = etPuntosHP.text.toString().toInt()
        val staminaPersonaje = etStamina.text.toString().toInt()
        return Personaje(
            0,
            nombrePersonaje!!,
            nivelPersonaje,
            clasePersonaje!!,
            descripcionPersonaje!!,
            hpPersonaje,
            hpPersonaje,
            staminaPersonaje,
            staminaPersonaje,
            habilidadDeAtaquePersonaje,
            esquivaPersonaje,
            paradaPersonaje,
            armaduraPersonaje,
            turnoPersonaje,
            agilidadPersonaje!!,
            constitucionPersonaje!!,
            destrezaPersonaje!!,
            fuerzaPersonaje!!,
            inteligenciaPersonaje!!,
            percepcionPersonaje!!,
            poderPersonaje!!,
            voluntadPersonaje!!,
            rfPersonaje,
            rmPersonaje,
            rpPersonaje,
            LocalDate.now()
        )
    }
}