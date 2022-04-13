package ies.quevedo.chardat.ui.personaje

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import ies.quevedo.chardat.R
import ies.quevedo.chardat.databinding.FragmentAddPersonaje1Binding
import ies.quevedo.chardat.domain.Personaje
import java.time.LocalDate

class AddPersonajeFragment1 : Fragment() {

    private var _binding: FragmentAddPersonaje1Binding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddPersonaje1Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            // He intentado hacerlo con el enum pero no he encontrado la forma de hacerlo
            val listaDeClases: Array<String> = listaDeClases()
            val adapter = ArrayAdapter(requireContext(), R.layout.list_item, listaDeClases)
            (etClases as? AutoCompleteTextView)?.setAdapter(adapter)
            btCancelar.setOnClickListener {
                activity?.onBackPressed()
            }
            btSiguiente.setOnClickListener {
                //TODO: El personaje no se inicializa por tener lateinit
                val bundle = buildPersonaje()
                findNavController().navigate(
                    R.id.action_addPersonajeFragment1_to_addPersonajeFragment2,
                    bundle
                )
            }
        }
    }

    private fun FragmentAddPersonaje1Binding.buildPersonaje(): Bundle {
        val nombrePersonaje = etNombrePersonaje.text.toString()
        val clasePersonaje = etClases.text.toString()
        val descripcionPersonaje = etDescripcion.text.toString()
        val bundle = Bundle()
        bundle.putString("nombrePersonaje", nombrePersonaje)
        bundle.putString("clasePersonaje", clasePersonaje)
        bundle.putString("descripcionPersonaje", descripcionPersonaje)
        return bundle
    }

    private fun listaDeClases(): Array<String> {
        return listOf(
            "GUERRERO", "GUERRERO ACRÓBATA", "PALADÍN",
            "PALADÍN OSCURO", "MAESTRO DE ARMAS", "TECNICISTA", "TAO", "EXPLORADOR", "SOMBRA",
            "LADRÓN", "ASESINO", "HECHICERO", "WARLOCK", "ILUSIONISTA", "HECHICERO MENTALISTA",
            "CONJURADOR", "GUERRERO CONJURADOR", "MENTALISTA", "GUERRERO MENTALISTA", "NOVEL"
        ).toTypedArray()
    }

    /*personaje.name = etNombrePersonaje.text.toString()
                personaje.level = 0
                personaje.clase = etClases.text.toString()
                personaje.description = etDescripcion.text.toString()
                personaje.currentHP = 0
                personaje.totalHP = 0
                personaje.currentStamina = 0
                personaje.totalStamina = 0
                personaje.attackHability = 0
                personaje.dodge = 0
                personaje.parryHability = 0
                personaje.armor = 0
                personaje.turn = 0
                personaje.agility = 0
                personaje.constitution = 0
                personaje.dexterity = 0
                personaje.strength = 0
                personaje.intelligence = 0
                personaje.perception = 0
                personaje.power = 0
                personaje.will = 0
                personaje.RF = 0
                personaje.RM = 0
                personaje.RP = 0
                personaje.creationDate = LocalDate.now()*/
}