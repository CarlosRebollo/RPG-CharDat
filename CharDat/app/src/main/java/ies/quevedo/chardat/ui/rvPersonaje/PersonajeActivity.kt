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
class PersonajeActivity : AppCompatActivity() {

    private lateinit var binding: RecyclerPersonajeBinding
    private lateinit var personajeAdapter: PersonajeAdapter
    private val viewModel: PersonajeViewModel by viewModels()
    private lateinit var createdPersonaje: Personaje
    private lateinit var updatedPersonaje: Personaje

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_item, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.addItem -> {
                //TODO -> Hacer el intent para ir a la pantalla de registro de nuevo personaje al pulsar el + de arriba.
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun observersRVPersonajes() {
        viewModel.personajes.observe(this) { personajes ->
            personajeAdapter.submitList(personajes)
        }
        viewModel.error.observe(this) {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        }
        viewModel.getPersonajesConTodo()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = RecyclerPersonajeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        personajeAdapter = PersonajeAdapter(
            ::showPersonaje,
            ::editPersonaje,
            ::deletePersonaje,
        )
        binding.rvPersonajes.adapter = personajeAdapter
        observersRVPersonajes()
        //TODO -> Comprobar que al crear la activity tenga o no un intent con datos de personajes creados, etc.
    }

    private fun showPersonaje(position: Int) {
        //TODO -> Enviar en un intent el Personaje para mostrar sus datos en otro activity.
    }

    private fun editPersonaje(position: Int) {
        //TODO -> Enviar en un intent el Personaje oara editar sus datos en otro activity.
    }

    private fun deletePersonaje(position: Int) {
        //TODO -> Eliminar el personaje de la aplicación. Sacar un Snackbar con un UNDO.
    }
}