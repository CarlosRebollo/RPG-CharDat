package ies.quevedo.chardat.ui.rvArma

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import ies.quevedo.chardat.R
import ies.quevedo.chardat.databinding.FragmentArmasBinding
import ies.quevedo.chardat.domain.Personaje

class RVArmaFragment : Fragment() {

    private val viewModel by viewModels<RVArmaViewModel>()
    private lateinit var adapter: RVArmaAdapter
    private lateinit var binding: FragmentArmasBinding
    private lateinit var navController: NavController
    private lateinit var personaje: Personaje

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        personaje = arguments?.getParcelable("personaje")!!
        binding = FragmentArmasBinding.inflate(layoutInflater)
        adapter = RVArmaAdapter(
            ::goWeaponDetails
        )
        binding.rvArmas.adapter = adapter
        observersRecyclerArmas()
    }

    private fun goWeaponDetails(position: Int) {
        val arma = viewModel.armas.value?.get(position)
        if (arma != null) {
            val bundle = Bundle()
            bundle.putParcelable("arma", arma)
            navController.navigate(R.id.action_RVArmaFragment_to_armaFragment, bundle)
        } else {
            Toast.makeText(context, "No se ha podido obtener el arma", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_armas, container, false)
        if (view is RecyclerView) {
            with(view) {
                layoutManager = androidx.recyclerview.widget.LinearLayoutManager(context)
                adapter = RVArmaAdapter(
                    ::goWeaponDetails
                )
            }
        }
        return view
    }

    private fun observersRecyclerArmas() {
        viewModel.armas.observe(this) {
            adapter.submitList(it)
        }
        viewModel.error.observe(this) {
            Toast.makeText(context, it, Toast.LENGTH_LONG).show()
        }
        viewModel.getArmas(personaje.id)
    }
}