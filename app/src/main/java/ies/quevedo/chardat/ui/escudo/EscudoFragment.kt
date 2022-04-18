package ies.quevedo.chardat.ui.escudo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import dagger.hilt.android.AndroidEntryPoint
import ies.quevedo.chardat.R
import ies.quevedo.chardat.databinding.FragmentEscudoBinding
import ies.quevedo.chardat.domain.Escudo
import ies.quevedo.chardat.domain.Personaje

@AndroidEntryPoint
class EscudoFragment : Fragment() {

    private var _binding: FragmentEscudoBinding? = null
    private val binding get() = _binding!!
    private lateinit var escudo: Escudo
    private lateinit var personaje: Personaje

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setHasOptionsMenu(true)
        _binding = FragmentEscudoBinding.inflate(inflater, container, false)
        personaje = arguments?.getParcelable("personaje")!!
        escudo = arguments?.getParcelable("escudo")!!
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            rellenarCamposDeEscudo()
            btCancelar.setOnClickListener {
                activity?.onBackPressed()
            }
            btModificar.setOnClickListener {
                if (faltaAlgunDato()) {
                    Toast.makeText(requireContext(), "Rellena todos los campos", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    val bundle = buildEscudoActualizado()
                    val navController = view.findNavController()
                    navController.navigate(
                        R.id.action_escudoFragment_to_RVEscudoFragment,
                        bundle
                    )
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        with(binding) {
            val listaDeArmaduras: Array<String> = listaDeEscudos()
            val adapter1 = ArrayAdapter(requireContext(), R.layout.list_item, listaDeArmaduras)
            (etNombreEscudo as? AutoCompleteTextView)?.setAdapter(adapter1)
            val listaDeNumeros: Array<Int> = arrayOf(-10, -5, 0, 5, 10, 15, 20, 25, 30)
            val adapter2 = ArrayAdapter(requireContext(), R.layout.list_item, listaDeNumeros)
            (etCalidad as? AutoCompleteTextView?)?.setAdapter(adapter2)
        }
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)
        menu.clear()
    }

    private fun FragmentEscudoBinding.rellenarCamposDeEscudo() {
        etNombreEscudo.setText(escudo.name)
        etCalidad.setText(escudo.quality.toString())
        etDescripcion.setText(escudo.description)
        etHabilidadDeAtaque.setText(escudo.attackHability.toString())
        etDamage.setText(escudo.damage.toString())
        etParada.setText(escudo.parry.toString())
        etValor.setText(escudo.value.toString())
        etPeso.setText(escudo.weight.toString())
    }

    private fun FragmentEscudoBinding.buildEscudoActualizado(): Bundle {
        escudo.name = etNombreEscudo.text.toString()
        escudo.quality = etCalidad.text.toString().toInt()
        escudo.description = etDescripcion.text.toString()
        escudo.attackHability = etHabilidadDeAtaque.text.toString().toInt()
        escudo.damage = etDamage.text.toString().toInt()
        escudo.parry = etParada.text.toString().toInt()
        escudo.value = etValor.text.toString().toInt()
        escudo.weight = etPeso.text.toString().toDouble()
        val bundle = Bundle()
        bundle.putParcelable("escudoActualizado", escudo)
        bundle.putParcelable("personaje", personaje)
        return bundle
    }

    private fun FragmentEscudoBinding.faltaAlgunDato() =
        etNombreEscudo.text.isNullOrBlank() ||
                etCalidad.text.isNullOrBlank() ||
                etDescripcion.text.isNullOrBlank() ||
                etHabilidadDeAtaque.text.isNullOrBlank() ||
                etDamage.text.isNullOrBlank() ||
                etParada.text.isNullOrBlank() ||
                etValor.text.isNullOrBlank() ||
                etPeso.text.isNullOrBlank()

    private fun listaDeEscudos(): Array<String> {
        return listOf(
            "ESCUDO",
            "ESCUDO CORPORAL",
            "RODELA"
        ).toTypedArray()
    }
}