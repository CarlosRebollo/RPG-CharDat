package ies.quevedo.chardat.ui.personaje

import android.os.Bundle
import android.view.*
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import ies.quevedo.chardat.R
import ies.quevedo.chardat.databinding.FragmentAddPersonaje1Binding

class AddPersonajeFragment1 : Fragment() {

    private var _binding: FragmentAddPersonaje1Binding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setHasOptionsMenu(true)
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
                if (faltaAlgunDato()) {
                    Toast.makeText(requireContext(), "Rellena todos los campos", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    val bundle = buildPersonaje()
                    findNavController().navigate(
                        R.id.action_addPersonajeFragment1_to_addPersonajeFragment2,
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

    private fun FragmentAddPersonaje1Binding.faltaAlgunDato() =
        etNombrePersonaje.text.isNullOrBlank() ||
                etClases.text.isNullOrBlank() ||
                etDescripcion.text.isNullOrBlank()

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