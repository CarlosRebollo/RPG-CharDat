package ies.quevedo.chardat.ui.rvPersonaje

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ies.quevedo.chardat.data.repository.PersonajeRepository
import ies.quevedo.chardat.domain.Personaje
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RVPersonajeViewModel @Inject constructor(
    private val personajeRepository: PersonajeRepository
) : ViewModel() {

    private val _personajes = MutableLiveData<List<Personaje>>()
    val personajes: LiveData<List<Personaje>> get() = _personajes

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    fun getPersonajes() {
        viewModelScope.launch {
            try {
                _personajes.value = personajeRepository.getPersonajes()
            } catch (e: Exception) {
                _error.value = e.message
            }
        }
    }

    fun insertPersonajeConTodo(personaje: Personaje) {
        viewModelScope.launch {
            try {
                _personajes.let { personajeRepository.insertPersonajeConTodo(personaje) }
            } catch (e: Exception) {
                _error.value = e.message
            }
        }
    }

    fun deletePersonaje(personaje: Personaje) {
        viewModelScope.launch {
            try {
                _personajes.let { personajeRepository.deletePersonaje(personaje) }
            } catch (e: Exception) {
                _error.value = e.message
            }
        }
    }
}