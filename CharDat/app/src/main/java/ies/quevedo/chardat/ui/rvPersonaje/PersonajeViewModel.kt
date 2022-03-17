package ies.quevedo.chardat.ui.rvPersonaje

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ies.quevedo.chardat.data.usecases.DeletePersonaje
import ies.quevedo.chardat.data.usecases.InsertPersonaje
import ies.quevedo.chardat.data.usecases.ListPersonajes
import ies.quevedo.chardat.data.usecases.UpdatePersonaje
import ies.quevedo.chardat.domain.Personaje
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PersonajeViewModel @Inject constructor(
    private val listPersonajes: ListPersonajes,
    private val insertPersonaje: InsertPersonaje,
    private val deletePersonaje: DeletePersonaje,
    private val updatePersonaje: UpdatePersonaje
) : ViewModel() {

    private val _personajes = MutableLiveData<List<Personaje>>()
    val personajes: LiveData<List<Personaje>> get() = _personajes

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    fun getPersonajesConTodo() {
        viewModelScope.launch {
            _personajes.value = listPersonajes.getPersonajes()
        }
    }

    fun insertPersonaje(personaje: Personaje) {
        viewModelScope.launch {
            insertPersonaje.insertPersonaje(personaje)
        }
    }

    fun deletePersonaje(personaje: Personaje) {
        viewModelScope.launch {
            deletePersonaje.deletePersonaje(personaje)
        }
    }

    fun updatePersonaje(personaje: Personaje) {
        viewModelScope.launch {
            updatePersonaje.updatePersonaje(personaje)
        }
    }
}