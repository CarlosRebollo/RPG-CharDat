package ies.quevedo.chardat.framework.personaje

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ies.quevedo.chardat.data.repository.PersonajeRepository
import ies.quevedo.chardat.data.utils.NetworkResult
import ies.quevedo.chardat.domain.model.Personaje
import ies.quevedo.chardat.framework.main.PersonajeContract
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PersonajeViewModel @Inject constructor(
    private val personajeRepository: PersonajeRepository
) : ViewModel() {

    private val _uiState: MutableStateFlow<PersonajeContract.State> by lazy {
        MutableStateFlow(PersonajeContract.State())
    }
    val uiState: StateFlow<PersonajeContract.State> = _uiState

    private val _uiError = Channel<String>()
    val uiError = _uiError.receiveAsFlow()

    fun handleEvent(
        event: PersonajeContract.Event,
        personaje: Personaje?
    ) {
        when (event) {
            PersonajeContract.Event.FetchPersonaje -> {
                personaje?.let { fetchPersonaje(it.id) }
            }
            PersonajeContract.Event.FetchPersonajes -> {
                fetchPersonajes()
            }
            PersonajeContract.Event.PostPersonaje -> {
                personaje?.let { postPersonaje(it) }
            }
            PersonajeContract.Event.PutPersonaje -> {
                personaje?.let { putPersonaje(it) }
            }
            PersonajeContract.Event.DeletePersonaje -> {
                personaje?.let { deletePersonaje(it.id) }
            }
            PersonajeContract.Event.ShowMessage -> {
                _uiState.update { it.copy(error = null) }
            }
        }
    }

    private fun fetchPersonaje(id: Int) {
        viewModelScope.launch {
            personajeRepository.getPersonajeConTodo(id)
                .catch(action = { cause -> _uiError.send(cause.message ?: "Error") })
                .collect { result ->
                    when (result) {
                        is NetworkResult.Error -> {
                            _uiState.update { it.copy(error = result.message) }
                        }
                        is NetworkResult.Loading -> _uiState.update { it.copy(isLoading = true) }
                        is NetworkResult.Success -> _uiState.update {
                            it.copy(personaje = result.data, isLoading = false)
                        }
                    }
                }
        }
    }

    private fun fetchPersonajes() {
        viewModelScope.launch {
            personajeRepository.getPersonajesConTodo()
                .catch(action = { cause -> _uiError.send(cause.message ?: "Error") })
                .collect { result ->
                    when (result) {
                        is NetworkResult.Error -> {
                            _uiState.update { it.copy(error = result.message) }
                        }
                        is NetworkResult.Loading -> _uiState.update { it.copy(isLoading = true) }
                        is NetworkResult.Success -> _uiState.update {
                            it.copy(personajes = result.data ?: emptyList(), isLoading = false)
                        }
                    }
                }
        }
    }

    private fun postPersonaje(personaje: Personaje) {
        viewModelScope.launch {
            personajeRepository.insertPersonaje(personaje)
                .catch(action = { cause -> _uiError.send(cause.message ?: "Error") })
                .collect { result ->
                    when (result) {
                        is NetworkResult.Error -> {
                            _uiState.update { it.copy(error = result.message) }
                        }
                        is NetworkResult.Loading -> _uiState.update { it.copy(isLoading = true) }
                        is NetworkResult.Success -> _uiState.update {
                            it.copy(personaje = result.data, isLoading = false)
                        }
                    }
                }
        }
    }

    private fun putPersonaje(personaje: Personaje) {
        viewModelScope.launch {
            personajeRepository.updatePersonaje(personaje)
                .catch(action = { cause -> _uiError.send(cause.message ?: "Error") })
                .collect { result ->
                    when (result) {
                        is NetworkResult.Error -> {
                            _uiState.update { it.copy(error = result.message) }
                        }
                        is NetworkResult.Loading -> _uiState.update { it.copy(isLoading = true) }
                        is NetworkResult.Success -> _uiState.update {
                            it.copy(personaje = result.data, isLoading = false)
                        }
                    }
                }
        }

    }

    private fun deletePersonaje(idPersonaje: Int) {
        viewModelScope.launch {
            personajeRepository.deletePersonaje(idPersonaje)
                .catch(action = { cause -> _uiError.send(cause.message ?: "Error") })
                .collect { result ->
                    when (result) {
                        is NetworkResult.Error -> {
                            _uiState.update { it.copy(error = result.message) }
                        }
                        is NetworkResult.Loading -> _uiState.update { it.copy(isLoading = true) }
                        is NetworkResult.Success -> _uiState.update {
                            it.copy(personaje = result.data, isLoading = false)
                        }
                    }
                }
        }
    }
}