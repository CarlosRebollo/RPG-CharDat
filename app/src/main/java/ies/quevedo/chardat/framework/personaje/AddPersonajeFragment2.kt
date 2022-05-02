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
import ies.quevedo.chardat.databinding.FragmentAddPersonaje2Binding

@AndroidEntryPoint
class AddPersonajeFragment2 : Fragment() {

    private var _binding: FragmentAddPersonaje2Binding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setHasOptionsMenu(true)
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
                if (faltaAlgunDato()) {
                    Toast.makeText(context, "Rellena todos los campos", Toast.LENGTH_SHORT).show()
                } else {
                    val bundle = buildPersonaje()
                    findNavController().navigate(
                        R.id.action_addPersonajeFragment2_to_addPersonajeFragment3,
                        bundle
                    )
                }
            }
        }
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)
        menu.clear()
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

    private fun FragmentAddPersonaje2Binding.faltaAlgunDato() =
        etAGI.text.isNullOrBlank() ||
                etCON.text.isNullOrBlank() ||
                etDES.text.isNullOrBlank() ||
                etFUE.text.isNullOrBlank() ||
                etINT.text.isNullOrBlank() ||
                etPER.text.isNullOrBlank() ||
                etPOD.text.isNullOrBlank() ||
                etVOL.text.isNullOrBlank()

    private fun FragmentAddPersonaje2Binding.buildPersonaje(): Bundle {
        val clasePersonaje = arguments?.getString("clase")
        val nombrePersonaje = arguments?.getString("nombre")
        val descripcionPersonaje = arguments?.getString("descripcion")
        val agilidadPersonaje = etAGI.text.toString().toInt()
        val constitucionPersonaje = etCON.text.toString().toInt()
        val destrezaPersonaje = etDES.text.toString().toInt()
        val fuerzaPersonaje = etFUE.text.toString().toInt()
        val inteligenciaPersonaje = etINT.text.toString().toInt()
        val percepcionPersonaje = etPER.text.toString().toInt()
        val poderPersonaje = etPOD.text.toString().toInt()
        val voluntadPersonaje = etVOL.text.toString().toInt()
        val bundle = Bundle()
        bundle.putString("clase", clasePersonaje)
        bundle.putString("nombre", nombrePersonaje)
        bundle.putString("descripcion", descripcionPersonaje)
        bundle.putInt("agilidad", agilidadPersonaje)
        bundle.putInt("constitucion", constitucionPersonaje)
        bundle.putInt("destreza", destrezaPersonaje)
        bundle.putInt("fuerza", fuerzaPersonaje)
        bundle.putInt("inteligencia", inteligenciaPersonaje)
        bundle.putInt("percepcion", percepcionPersonaje)
        bundle.putInt("poder", poderPersonaje)
        bundle.putInt("voluntad", voluntadPersonaje)
        return bundle
    }
}