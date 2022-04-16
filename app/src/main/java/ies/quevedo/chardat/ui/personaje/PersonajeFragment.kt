package ies.quevedo.chardat.ui.personaje

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint
import ies.quevedo.chardat.databinding.FragmentPersonajeBinding
import ies.quevedo.chardat.domain.Personaje

@AndroidEntryPoint
class PersonajeFragment : Fragment() {

    private var _binding: FragmentPersonajeBinding? = null
    private val binding get() = _binding!!
    private lateinit var personaje: Personaje

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setHasOptionsMenu(true)
        _binding = FragmentPersonajeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        personaje = arguments?.getParcelable("personaje")!!
        with(binding) {

        }
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)
        menu.clear()
    }
}