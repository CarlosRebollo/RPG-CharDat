package ies.quevedo.chardat.ui.rvPersonaje

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import ies.quevedo.chardat.R
import ies.quevedo.chardat.databinding.FragmentPersonajeBinding
import ies.quevedo.chardat.databinding.FragmentPersonajesListBinding
import ies.quevedo.chardat.domain.Personaje
import ies.quevedo.chardat.placeholder.PlaceholderContent

class RVPersonajeFragment : Fragment() {

    private val viewModel by viewModels<RVPersonajeViewModel>()
    private lateinit var adapter: RVPersonajeAdapter
    private lateinit var binding: FragmentPersonajesListBinding
    private lateinit var personaje: Personaje

}