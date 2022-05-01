package ies.quevedo.chardat.framework.objeto

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
import ies.quevedo.chardat.databinding.FragmentAddObjetoBinding
import ies.quevedo.chardat.domain.model.Objeto
import ies.quevedo.chardat.domain.model.Personaje
import java.util.*

@AndroidEntryPoint
class AddObjetoFragment : Fragment() {

    private var _binding: FragmentAddObjetoBinding? = null
    private val binding get() = _binding!!
    private lateinit var personaje: Personaje

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setHasOptionsMenu(true)
        _binding = FragmentAddObjetoBinding.inflate(inflater, container, false)
        personaje = arguments?.getParcelable("personaje")!!
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            btCancelar.setOnClickListener {
                findNavController().navigateUp()
            }
            btCrear.setOnClickListener {
                if (faltaAlgunDato()) {
                    Toast.makeText(context, "Rellena todos los campos", Toast.LENGTH_SHORT).show()
                } else {
                    val bundle = buildObjeto()
                    findNavController().navigate(
                        R.id.action_addObjetoFragment_to_RVObjetoFragment,
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

    private fun FragmentAddObjetoBinding.buildObjeto(): Bundle {
        val nombreObjeto = etNombreObjeto.text.toString().uppercase(Locale.getDefault())
        val descripcionObjeto = etDescripcion.text.toString()
        val valorObjeto = etValor.text.toString().toInt()
        val cantidadObjeto = etCantidad.text.toString().toInt()
        val pesoObjeto = etPeso.text.toString().toDouble()
        val objeto = Objeto(
            0,
            nombreObjeto,
            valorObjeto,
            pesoObjeto,
            cantidadObjeto,
            descripcionObjeto,
            personaje.id
        )
        val bundle = Bundle()
        bundle.putParcelable("objetoCreado", objeto)
        bundle.putParcelable("personaje", personaje)
        return bundle
    }

    private fun FragmentAddObjetoBinding.faltaAlgunDato() =
        etNombreObjeto.text.isNullOrEmpty() ||
                etDescripcion.text.isNullOrEmpty() ||
                etValor.text.isNullOrEmpty() ||
                etCantidad.text.isNullOrEmpty() ||
                etPeso.text.isNullOrEmpty()
}