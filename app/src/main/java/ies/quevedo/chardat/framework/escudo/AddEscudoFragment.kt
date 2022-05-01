package ies.quevedo.chardat.framework.escudo

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
import ies.quevedo.chardat.databinding.FragmentAddEscudoBinding
import ies.quevedo.chardat.domain.model.Escudo
import ies.quevedo.chardat.domain.model.Personaje

@AndroidEntryPoint
class AddEscudoFragment : Fragment() {

    private var _binding: FragmentAddEscudoBinding? = null
    private val binding get() = _binding!!
    private lateinit var personaje: Personaje

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setHasOptionsMenu(true)
        _binding = FragmentAddEscudoBinding.inflate(inflater, container, false)
        personaje = arguments?.getParcelable("personaje")!!
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            rellenarCalidad()
            rellenarEscudos()
            btCancelar.setOnClickListener {
                findNavController().navigateUp()
            }
            btCrear.setOnClickListener {
                if (faltaAlgunDato()) {
                    Toast.makeText(context, "Rellena todos los campos", Toast.LENGTH_SHORT).show()
                } else {
                    val bundle = buildEscudo()
                    findNavController().navigate(
                        R.id.action_addEscudoFragment_to_RVEscudoFragment,
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

    private fun FragmentAddEscudoBinding.buildEscudo(): Bundle {
        val nombreEscudo = etNombreEscudo.text.toString()
        val calidadEscudo = etCalidad.text.toString().toInt()
        val descripcionEscudo = etDescripcion.text.toString()
        val habilidadDeAtaqueDeEscudo = etHabilidadDeAtaque.text.toString().toInt()
        val damageEscudo = etDamage.text.toString().toInt()
        val paradaEscudo = etParada.text.toString().toInt()
        val valorEscudo = etValor.text.toString().toInt()
        val pesoEscudo = etPeso.text.toString().toDouble()
        val escudo = Escudo(
            0,
            nombreEscudo,
            valorEscudo,
            pesoEscudo,
            calidadEscudo,
            habilidadDeAtaqueDeEscudo,
            damageEscudo,
            paradaEscudo,
            descripcionEscudo,
            personaje.id
        )
        val bundle = Bundle()
        bundle.putParcelable("escudoCreado", escudo)
        bundle.putParcelable("personaje", personaje)
        return bundle
    }

    private fun FragmentAddEscudoBinding.faltaAlgunDato() =
        etNombreEscudo.text.isNullOrBlank() ||
                etCalidad.text.isNullOrBlank() ||
                etDescripcion.text.isNullOrBlank() ||
                etHabilidadDeAtaque.text.isNullOrBlank() ||
                etDamage.text.isNullOrBlank() ||
                etParada.text.isNullOrBlank() ||
                etValor.text.isNullOrBlank() ||
                etPeso.text.isNullOrBlank()

    private fun FragmentAddEscudoBinding.rellenarEscudos() {
        val listaDeArmaduras: Array<String> = listaDeEscudos()
        val adapter = ArrayAdapter(requireContext(), R.layout.list_item, listaDeArmaduras)
        (etNombreEscudo as? AutoCompleteTextView)?.setAdapter(adapter)
    }

    private fun FragmentAddEscudoBinding.rellenarCalidad() {
        val listaDeNumeros: Array<Int> = arrayOf(-10, -5, 0, 5, 10, 15, 20, 25, 30)
        val adapter = ArrayAdapter(requireContext(), R.layout.list_item, listaDeNumeros)
        (etCalidad as? AutoCompleteTextView?)?.setAdapter(adapter)
    }

    private fun listaDeEscudos(): Array<String> {
        return listOf(
            "ESCUDO",
            "ESCUDO CORPORAL",
            "RODELA"
        ).toTypedArray()
    }
}