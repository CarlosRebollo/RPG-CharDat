package ies.quevedo.chardat.ui.rvPersonaje

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ies.quevedo.chardat.data.usecases.InsertPersonaje
import ies.quevedo.chardat.data.usecases.ListPersonajes
import ies.quevedo.chardat.domain.Personaje
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RVPersonajeViewModel @Inject constructor(
    private val listPersonajes: ListPersonajes,
    private val insertPersonaje: InsertPersonaje
) : ViewModel() {

    private val _personajes = MutableLiveData<List<Personaje>>()
    val personajes: LiveData<List<Personaje>> get() = _personajes

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    fun getPersonajes() {
        viewModelScope.launch {
            try {
                _personajes.value = listPersonajes.invoke()
            } catch (e: Exception) {
                _error.value = e.message
            }
        }
    }

    fun insertPersonaje(personaje: Personaje) {
        viewModelScope.launch {
            try {
                insertPersonaje.invoke(personaje)
            } catch (e: Exception) {
                _error.value = e.message
            }
        }
    }
}