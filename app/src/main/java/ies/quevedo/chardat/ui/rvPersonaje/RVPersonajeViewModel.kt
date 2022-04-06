package ies.quevedo.chardat.ui.rvPersonaje

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ies.quevedo.chardat.data.usecases.ListPersonajes
import ies.quevedo.chardat.domain.Personaje
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RVPersonajeViewModel @Inject constructor(
    private val listPersonajes: ListPersonajes
) : ViewModel() {

    private val _personajes = MutableLiveData<List<Personaje>>()
    val personajes: LiveData<List<Personaje>> get() = _personajes

    private val _error = MutableLiveData<List<String>>()
    val error: LiveData<List<String>> get() = _error

    fun getPersonajes() {
        viewModelScope.launch {
            try {
                _personajes.value = listPersonajes.getPersonajes()
            } catch (e: Exception) {
                _error.value = listOf(e.message ?: "Ha al cargar los personajes")
            }
        }
    }
}