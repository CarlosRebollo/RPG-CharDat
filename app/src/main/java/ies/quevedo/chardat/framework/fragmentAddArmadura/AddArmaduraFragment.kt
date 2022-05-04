package ies.quevedo.chardat.framework.fragmentAddArmadura

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
import ies.quevedo.chardat.R
import ies.quevedo.chardat.databinding.FragmentAddArmaduraBinding
import ies.quevedo.chardat.domain.model.Armadura
import ies.quevedo.chardat.domain.model.Personaje

class AddArmaduraFragment : Fragment() {

    private var _binding: FragmentAddArmaduraBinding? = null
    private val binding get() = _binding!!
    private lateinit var personaje: Personaje

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setHasOptionsMenu(true)
        _binding = FragmentAddArmaduraBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            rellenarCalidad()
            rellenarArmaduras()
            btCancelar.setOnClickListener {
                findNavController().navigateUp()
            }
            btCrear.setOnClickListener {
                if (faltaAlgunDato()) {
                    Toast.makeText(context, "Rellena todos los campos", Toast.LENGTH_SHORT).show()
                } else {
                    val armadura = buildArmadura()
                    // TODO: Guardar en retrofit
                    findNavController().navigate(R.id.action_addArmaduraFragment_to_RVArmaduraFragment)
                }
            }
        }
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)
        menu.clear()
    }

    private fun FragmentAddArmaduraBinding.buildArmadura(): Armadura {
        val nombreArmadura = etNombreArmadura.text.toString()
        val calidadArma = etCalidad.text.toString().toInt()
        val descripcionArmadura = etDescripcion.text.toString()
        val fil = etFIL.text.toString().toInt()
        val con = etCON.text.toString().toInt()
        val pen = etPEN.text.toString().toInt()
        val cal = etCAL.text.toString().toInt()
        val ele = etELE.text.toString().toInt()
        val fri = etFRI.text.toString().toInt()
        val ene = etENE.text.toString().toInt()
        val llevarArmadura = etArmadura.text.toString().toInt()
        val valorArma = etValor.text.toString().toInt()
        val pesoArma = etPeso.text.toString().toDouble()
        return Armadura(
            0,
            nombreArmadura,
            valorArma,
            pesoArma,
            calidadArma,
            llevarArmadura,
            fil,
            con,
            pen,
            cal,
            ele,
            fri,
            ene,
            descripcionArmadura,
            personaje.id
        )
    }

    private fun FragmentAddArmaduraBinding.faltaAlgunDato() =
        etNombreArmadura.text.isNullOrBlank() ||
                etCalidad.text.isNullOrBlank() ||
                etDescripcion.text.isNullOrBlank() ||
                etFIL.text.isNullOrBlank() ||
                etCON.text.isNullOrBlank() ||
                etPEN.text.isNullOrBlank() ||
                etCAL.text.isNullOrBlank() ||
                etELE.text.isNullOrBlank() ||
                etFRI.text.isNullOrBlank() ||
                etENE.text.isNullOrBlank() ||
                etArmadura.text.isNullOrBlank() ||
                etValor.text.isNullOrBlank() ||
                etPeso.text.isNullOrBlank()

    private fun FragmentAddArmaduraBinding.rellenarArmaduras() {
        val listaDeArmaduras: Array<String> = listaDeArmaduras()
        val adapter = ArrayAdapter(requireContext(), R.layout.list_item, listaDeArmaduras)
        (etNombreArmadura as? AutoCompleteTextView)?.setAdapter(adapter)
    }

    private fun FragmentAddArmaduraBinding.rellenarCalidad() {
        val listaDeNumeros: Array<Int> = arrayOf(-10, -5, 0, 5, 10, 15, 20, 25, 30)
        val adapter = ArrayAdapter(requireContext(), R.layout.list_item, listaDeNumeros)
        (etCalidad as? AutoCompleteTextView?)?.setAdapter(adapter)
    }

    private fun listaDeArmaduras(): Array<String> {
        return listOf(
            "ACOLCHADA",
            "ANILLAS",
            "COMPLETA",
            "COMPLETA DE CUERO",
            "COMPLETA PESADA",
            "COTA DE CUERO",
            "CUERO ENDURECIDO",
            "CUERO TACHONADO",
            "DE CAMPAÑA PESADA",
            "ESCAMAS",
            "GABARDINA",
            "MALLAS",
            "PETO",
            "PIEL",
            "PIEZAS",
            "PLACAS",
            "SEMICOMPLETA"
        ).toTypedArray()
    }
}