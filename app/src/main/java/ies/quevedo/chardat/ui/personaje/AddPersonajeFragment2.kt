package ies.quevedo.chardat.ui.personaje

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import ies.quevedo.chardat.R
import ies.quevedo.chardat.databinding.FragmentAddPersonaje2Binding
import ies.quevedo.chardat.domain.Personaje
import java.time.LocalDate

@AndroidEntryPoint
class AddPersonajeFragment2 : Fragment() {

    private var _binding: FragmentAddPersonaje2Binding? = null
    private val binding get() = _binding!!
    private lateinit var personajeAnterior: Personaje

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddPersonaje2Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        personajeAnterior = arguments?.getParcelable("personaje")!!
        findNavController().popBackStack(R.id.addPersonajeFragment1, true)
        with(binding) {
            val listaDeNumeros: Array<Int> = arrayOf(10, 9, 8, 7, 6, 5, 4, 3, 2, 1)
            val adapter = ArrayAdapter(requireContext(), R.layout.list_item, listaDeNumeros)
            (etAGI as? AutoCompleteTextView)?.setAdapter(adapter)
            (etCON as? AutoCompleteTextView)?.setAdapter(adapter)
            (etDES as? AutoCompleteTextView)?.setAdapter(adapter)
            (etFUE as? AutoCompleteTextView)?.setAdapter(adapter)
            (etINT as? AutoCompleteTextView)?.setAdapter(adapter)
            (etPER as? AutoCompleteTextView)?.setAdapter(adapter)
            (etPOD as? AutoCompleteTextView)?.setAdapter(adapter)
            (etVOL as? AutoCompleteTextView)?.setAdapter(adapter)
            btCancelar.setOnClickListener {
                activity?.onBackPressed()
            }
            btSiguiente.setOnClickListener {
                with(binding) {
                    personajeAnterior.let {
                        it.agility = etAGI.text.toString().toInt()
                        it.constitution = etCON.text.toString().toInt()
                        it.dexterity = etDES.text.toString().toInt()
                        it.strength = etFUE.text.toString().toInt()
                        it.intelligence = etINT.text.toString().toInt()
                        it.perception = etPER.text.toString().toInt()
                        it.power = etPOD.text.toString().toInt()
                        it.will = etVOL.text.toString().toInt()
                    }
                }
                val personaje: Personaje? = null
                personaje?.name = personajeAnterior.name
                personaje?.level = 0
                personaje?.clase = personajeAnterior.clase
                personaje?.description = personajeAnterior.description
                personaje?.currentHP = 0
                personaje?.totalHP = 0
                personaje?.currentStamina = 0
                personaje?.totalStamina = 0
                personaje?.attackHability = 0
                personaje?.dodge = 0
                personaje?.parryHability = 0
                personaje?.armor = 0
                personaje?.turn = 0
                personaje?.agility = etAGI.text.toString().toInt()
                personaje?.constitution = etCON.text.toString().toInt()
                personaje?.dexterity = etDES.text.toString().toInt()
                personaje?.strength = etFUE.text.toString().toInt()
                personaje?.intelligence = etINT.text.toString().toInt()
                personaje?.perception = etPER.text.toString().toInt()
                personaje?.power = etPOD.text.toString().toInt()
                personaje?.will = etVOL.text.toString().toInt()
                personaje?.RF = 0
                personaje?.RM = 0
                personaje?.RP = 0
                personaje?.creationDate = LocalDate.now()
                val bundle = Bundle()
                bundle.putParcelable("personaje", personajeAnterior)
                findNavController().navigate(
                    R.id.action_addPersonajeFragment2_to_addPersonajeFragment3,
                    bundle
                )
            }
        }
    }
}