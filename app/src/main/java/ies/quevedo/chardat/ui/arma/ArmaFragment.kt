package ies.quevedo.chardat.ui.arma

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import dagger.hilt.android.AndroidEntryPoint
import ies.quevedo.chardat.R
import ies.quevedo.chardat.databinding.FragmentArmaBinding
import ies.quevedo.chardat.domain.Arma
import ies.quevedo.chardat.domain.Personaje

@AndroidEntryPoint
class ArmaFragment : Fragment() {

    private var _binding: FragmentArmaBinding? = null
    private val binding get() = _binding!!
    private lateinit var arma: Arma
    private lateinit var personaje: Personaje

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentArmaBinding.inflate(inflater, container, false)
        personaje = arguments?.getParcelable("personaje")!!
        arma = arguments?.getParcelable("arma")!!
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            etNombreArma.setText(arma.name)
            etCalidad.setText(arma.quality.toString())
            etDescripcion.setText(arma.description)
            etTurno.setText(arma.turn.toString())
            etHabilidadDeAtaque.setText(arma.attackHability.toString())
            etDamage.setText(arma.damage.toString())
            etParada.setText(arma.parry.toString())
            etValor.setText(arma.value.toString())
            etPeso.setText(arma.weight.toString())
            btCancelar.setOnClickListener {
                activity?.onBackPressed()
            }
            btModificar.setOnClickListener {
                arma.name = etNombreArma.text.toString()
                arma.quality = etCalidad.text.toString().toInt()
                arma.description = etDescripcion.text.toString()
                arma.turn = etTurno.text.toString().toInt()
                arma.attackHability = etHabilidadDeAtaque.text.toString().toInt()
                arma.damage = etDamage.text.toString().toInt()
                arma.parry = etParada.text.toString().toInt()
                arma.value = etValor.text.toString().toInt()
                arma.weight = etPeso.text.toString().toDouble()
                val bundle = Bundle()
                bundle.putParcelable("arma", arma)
                bundle.putParcelable("personaje", personaje)
                val navController = view.findNavController()
                navController.navigate(R.id.action_armaFragment_to_RVArmaFragment, bundle)
            }
        }
    }
}