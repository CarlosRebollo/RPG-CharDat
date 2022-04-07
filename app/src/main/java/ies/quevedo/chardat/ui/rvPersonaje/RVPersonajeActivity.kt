package ies.quevedo.chardat.ui.rvPersonaje

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import ies.quevedo.chardat.R
import ies.quevedo.chardat.databinding.RecyclerPersonajeBinding

@AndroidEntryPoint
class RVPersonajeActivity : AppCompatActivity() {

    private val viewModel by viewModels<RVPersonajeViewModel>()
    private lateinit var adapter: RVPersonajeAdapter
    private lateinit var binding: RecyclerPersonajeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        binding = RecyclerPersonajeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        adapter = RVPersonajeAdapter(
            ::goMainMenu
        )
        binding.rvPersonajes.adapter = adapter
        observersRecyclerPersonaje()
    }

    private fun goMainMenu(position: Int) {
        val personaje = viewModel.personajes.value?.get(position)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_item, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.addPersonaje -> {
                // TODO: Add personaje
                true
            }
            R.id.filterPersonajes -> {
                // TODO: Filter personajes
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun observersRecyclerPersonaje() {
        viewModel.personajes.observe(this) {
            adapter.submitList(it)
        }
        viewModel.error.observe(this) {
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        }
        viewModel.getPersonajes()
    }
}