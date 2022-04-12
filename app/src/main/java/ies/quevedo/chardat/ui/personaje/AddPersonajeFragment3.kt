package ies.quevedo.chardat.ui.personaje

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import ies.quevedo.chardat.R
import ies.quevedo.chardat.databinding.FragmentAddPersonaje3Binding
import ies.quevedo.chardat.domain.Personaje

@AndroidEntryPoint
class AddPersonajeFragment3 : Fragment() {

    private var _binding: FragmentAddPersonaje3Binding? = null
    private val binding get() = _binding!!
    private lateinit var personaje: Personaje

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddPersonaje3Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        personaje = arguments?.getParcelable("personaje")!!
        findNavController().popBackStack(R.id.addPersonajeFragment2, true)

    }
}