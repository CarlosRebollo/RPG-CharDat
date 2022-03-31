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
import ies.quevedo.chardat.domain.Personaje

@AndroidEntryPoint
class RVPersonajeActivity : AppCompatActivity() {

    private lateinit var binding: RecyclerPersonajeBinding
    private lateinit var rvPersonajeAdapter: RVPersonajeAdapter
    private val viewModelRV: RVPersonajeViewModel by viewModels()
    private lateinit var createdPersonaje: Personaje
    private lateinit var updatedPersonaje: Personaje

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_item, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.addPersonaje -> {
                true
            }
            R.id.filterPersonajes -> {
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun observersRVPersonajes() {
        viewModelRV.personajes.observe(this) { personajes ->
            rvPersonajeAdapter.submitList(personajes)
        }
        viewModelRV.error.observe(this) {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        }
        viewModelRV.getPersonajesConTodo()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = RecyclerPersonajeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        rvPersonajeAdapter = RVPersonajeAdapter()
        binding.rvPersonajes.adapter = rvPersonajeAdapter
        observersRVPersonajes()
    }
}