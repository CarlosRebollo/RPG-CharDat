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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddPersonaje2Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            rellenarNumeros()
            btCancelar.setOnClickListener {
                activity?.onBackPressed()
                findNavController().popBackStack(R.id.addPersonajeFragment1, true)
            }
            btSiguiente.setOnClickListener {
                val bundle = buildPersonaje()
                findNavController().navigate(
                    R.id.action_addPersonajeFragment2_to_addPersonajeFragment3,
                    bundle
                )
            }
        }
    }

    private fun FragmentAddPersonaje2Binding.rellenarNumeros() {
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
    }

    private fun FragmentAddPersonaje2Binding.buildPersonaje(): Bundle {
        val nombrePersonaje = arguments?.getString("nombrePersonaje")
        val clasePersonaje = arguments?.getString("clasePersonaje")
        val descripcionPersonaje = arguments?.getString("descripcionPersonaje")
        val agilidadPersonaje = etAGI.text.toString().toInt()
        val constitucionPersonaje = etCON.text.toString().toInt()
        val destrezaPersonaje = etDES.text.toString().toInt()
        val fuerzaPersonaje = etFUE.text.toString().toInt()
        val inteligenciaPersonaje = etINT.text.toString().toInt()
        val percepcionPersonaje = etPER.text.toString().toInt()
        val poderPersonaje = etPOD.text.toString().toInt()
        val voluntadPersonaje = etVOL.text.toString().toInt()
        val bundle = Bundle()
        bundle.putString("nombrePersonaje", nombrePersonaje)
        bundle.putString("clasePersonaje", clasePersonaje)
        bundle.putString("descripcionPersonaje", descripcionPersonaje)
        bundle.putInt("agilidadPersonaje", agilidadPersonaje)
        bundle.putInt("constitucionPersonaje", constitucionPersonaje)
        bundle.putInt("destrezaPersonaje", destrezaPersonaje)
        bundle.putInt("fuerzaPersonaje", fuerzaPersonaje)
        bundle.putInt("inteligenciaPersonaje", inteligenciaPersonaje)
        bundle.putInt("percepcionPersonaje", percepcionPersonaje)
        bundle.putInt("poderPersonaje", poderPersonaje)
        bundle.putInt("voluntadPersonaje", voluntadPersonaje)
        return bundle
    }
}