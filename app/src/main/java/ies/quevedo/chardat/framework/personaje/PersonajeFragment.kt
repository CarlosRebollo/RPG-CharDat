package ies.quevedo.chardat.framework.personaje

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import ies.quevedo.chardat.R
import ies.quevedo.chardat.databinding.FragmentPersonajeBinding
import ies.quevedo.chardat.domain.model.Personaje

@AndroidEntryPoint
class PersonajeFragment : Fragment() {

    private var _binding: FragmentPersonajeBinding? = null
    private val binding get() = _binding!!
    private lateinit var personaje: Personaje

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setHasOptionsMenu(true)
        _binding = FragmentPersonajeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            rellenarCamposDePersonaje()
            // TODO: Buscar el personaje por su id y cargar sus datos en la funcion rellenaresCamposDePersonaje()
            btCancelar.setOnClickListener {
                activity?.onBackPressed()
            }
            btModificar.setOnClickListener {
                buildPersonajeActualizado()
                if (faltaAlgunDato()) {
                    Toast.makeText(context, "Rellena todos los campos", Toast.LENGTH_SHORT).show()
                } else {
                    findNavController().navigate(R.id.action_personajeFragment_to_mainMenuFragment)
                }
            }
        }
    }

    /**
     * Al poner aquí el rellenado de clases y stats siempre saldrán todas las opciones
     * en el DropDownMenu y no únicamente la seleccionada
     */
    override fun onResume() {
        super.onResume()
        with(binding) {
            val listaDeClases: Array<String> = listaDeClases()
            val adapter1 = ArrayAdapter(requireContext(), R.layout.list_item, listaDeClases)
            (etClases as? AutoCompleteTextView)?.setAdapter(adapter1)
            val listaDeNumeros: Array<Int> = arrayOf(10, 9, 8, 7, 6, 5, 4, 3, 2, 1)
            val adapter2 = ArrayAdapter(requireContext(), R.layout.list_item, listaDeNumeros)
            (etAGI as? AutoCompleteTextView)?.setAdapter(adapter2)
            (etCON as? AutoCompleteTextView)?.setAdapter(adapter2)
            (etDES as? AutoCompleteTextView)?.setAdapter(adapter2)
            (etFUE as? AutoCompleteTextView)?.setAdapter(adapter2)
            (etINT as? AutoCompleteTextView)?.setAdapter(adapter2)
            (etPER as? AutoCompleteTextView)?.setAdapter(adapter2)
            (etPOD as? AutoCompleteTextView)?.setAdapter(adapter2)
            (etVOL as? AutoCompleteTextView)?.setAdapter(adapter2)
        }
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)
        menu.clear()
    }

    private fun FragmentPersonajeBinding.buildPersonajeActualizado() {
        personaje.clase = etClases.text.toString()
        personaje.name = etNombrePersonaje.text.toString()
        personaje.description = etDescripcion.text.toString()
        personaje.agility = etAGI.text.toString().toInt()
        personaje.constitution = etCON.text.toString().toInt()
        personaje.dexterity = etDES.text.toString().toInt()
        personaje.strength = etFUE.text.toString().toInt()
        personaje.intelligence = etINT.text.toString().toInt()
        personaje.perception = etPER.text.toString().toInt()
        personaje.power = etPOD.text.toString().toInt()
        personaje.will = etVOL.text.toString().toInt()
        personaje.level = etNivel.text.toString().toInt()
        personaje.attackHability = etHabilidadDeAtaque.text.toString().toInt()
        personaje.dodge = etEsquiva.text.toString().toInt()
        personaje.parryHability = etParada.text.toString().toInt()
        personaje.armor = etArmadura.text.toString().toInt()
        personaje.turn = etTurno.text.toString().toInt()
        personaje.RF = etRF.text.toString().toInt()
        personaje.RM = etRM.text.toString().toInt()
        personaje.RP = etRP.text.toString().toInt()
        personaje.totalHP = etPuntosHP.text.toString().toInt()
        personaje.totalStamina = etStamina.text.toString().toInt()
    }

    private fun FragmentPersonajeBinding.rellenarCamposDePersonaje() {
        etClases.setText(personaje.clase)
        etNombrePersonaje.setText(personaje.name)
        etDescripcion.setText(personaje.description)
        etAGI.setText(personaje.agility.toString())
        etCON.setText(personaje.constitution.toString())
        etDES.setText(personaje.dexterity.toString())
        etFUE.setText(personaje.strength.toString())
        etINT.setText(personaje.intelligence.toString())
        etPER.setText(personaje.perception.toString())
        etPOD.setText(personaje.power.toString())
        etVOL.setText(personaje.will.toString())
        etNivel.setText(personaje.level.toString())
        etHabilidadDeAtaque.setText(personaje.attackHability.toString())
        etEsquiva.setText(personaje.dodge.toString())
        etParada.setText(personaje.parryHability.toString())
        etArmadura.setText(personaje.armor.toString())
        etTurno.setText(personaje.turn.toString())
        etRF.setText(personaje.RF.toString())
        etRM.setText(personaje.RM.toString())
        etRP.setText(personaje.RP.toString())
        etPuntosHP.setText(personaje.totalHP.toString())
        etStamina.setText(personaje.totalStamina.toString())
    }

    private fun FragmentPersonajeBinding.faltaAlgunDato() =
        etClases.text.isNullOrBlank() ||
                etNombrePersonaje.text.isNullOrBlank() ||
                etDescripcion.text.isNullOrBlank() ||
                etAGI.text.isNullOrBlank() ||
                etCON.text.isNullOrBlank() ||
                etDES.text.isNullOrBlank() ||
                etFUE.text.isNullOrBlank() ||
                etINT.text.isNullOrBlank() ||
                etPER.text.isNullOrBlank() ||
                etVOL.text.isNullOrBlank() ||
                etPOD.text.isNullOrBlank() ||
                etNivel.text.isNullOrBlank() ||
                etHabilidadDeAtaque.text.isNullOrBlank() ||
                etEsquiva.text.isNullOrBlank() ||
                etParada.text.isNullOrBlank() ||
                etArmadura.text.isNullOrBlank() ||
                etTurno.text.isNullOrBlank() ||
                etRF.text.isNullOrBlank() ||
                etRM.text.isNullOrBlank() ||
                etRP.text.isNullOrBlank() ||
                etPuntosHP.text.isNullOrBlank() ||
                etStamina.text.isNullOrBlank()

    private fun listaDeClases(): Array<String> {
        return listOf(
            "GUERRERO",
            "GUERRERO ACRÓBATA",
            "PALADÍN",
            "PALADÍN OSCURO",
            "MAESTRO DE ARMAS",
            "TECNICISTA",
            "TAO",
            "EXPLORADOR",
            "SOMBRA",
            "LADRÓN",
            "ASESINO",
            "HECHICERO",
            "WARLOCK",
            "ILUSIONISTA",
            "HECHICERO MENTALISTA",
            "CONJURADOR",
            "GUERRERO CONJURADOR",
            "MENTALISTA",
            "GUERRERO MENTALISTA",
            "NOVEL"
        ).toTypedArray()
    }
}