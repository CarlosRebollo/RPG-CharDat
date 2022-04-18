package ies.quevedo.chardat.ui.objeto

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import ies.quevedo.chardat.R
import ies.quevedo.chardat.databinding.FragmentObjetoBinding
import ies.quevedo.chardat.domain.Objeto
import ies.quevedo.chardat.domain.Personaje
import java.util.*

@AndroidEntryPoint
class ObjetoFragment : Fragment() {

    private var _binding: FragmentObjetoBinding? = null
    private val binding get() = _binding!!
    private lateinit var objeto: Objeto
    private lateinit var personaje: Personaje

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setHasOptionsMenu(true)
        _binding = FragmentObjetoBinding.inflate(inflater, container, false)
        personaje = arguments?.getParcelable("personaje")!!
        objeto = arguments?.getParcelable("objeto")!!
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            rellenarCamposDeObjeto()
            btCancelar.setOnClickListener {
                activity?.onBackPressed()
            }
            btModificar.setOnClickListener {
                if (faltaAlgunDato()) {
                    Toast.makeText(requireContext(), "Rellena todos los campos", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    val bundle = buildObjetoActualizado()
                    findNavController().navigate(
                        R.id.action_objetoFragment_to_RVObjetoFragment,
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

    private fun FragmentObjetoBinding.rellenarCamposDeObjeto() {
        etNombreObjeto.setText(objeto.name)
        etDescripcion.setText(objeto.description)
        etValor.setText(objeto.value.toString())
        etCantidad.setText(objeto.amount.toString())
        etPeso.setText(objeto.weight.toString())
    }

    private fun FragmentObjetoBinding.buildObjetoActualizado(): Bundle {
        objeto.name = etNombreObjeto.text.toString().uppercase(Locale.getDefault())
        objeto.description = etDescripcion.text.toString()
        objeto.value = etValor.text.toString().toInt()
        objeto.amount = etCantidad.text.toString().toInt()
        objeto.weight = etPeso.text.toString().toDouble()
        val bundle = Bundle()
        bundle.putParcelable("objetoActualizado", objeto)
        bundle.putParcelable("personaje", personaje)
        return bundle
    }

    private fun FragmentObjetoBinding.faltaAlgunDato() =
        etNombreObjeto.text.isNullOrEmpty() ||
                etDescripcion.text.isNullOrEmpty() ||
                etValor.text.isNullOrEmpty() ||
                etCantidad.text.isNullOrEmpty() ||
                etPeso.text.isNullOrEmpty()
}