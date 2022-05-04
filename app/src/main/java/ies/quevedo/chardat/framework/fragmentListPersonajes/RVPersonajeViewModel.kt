package ies.quevedo.chardat.framework.fragmentListPersonajes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ies.quevedo.chardat.data.repository.PersonajeRepository
import ies.quevedo.chardat.data.utils.NetworkResult
import ies.quevedo.chardat.domain.model.Personaje
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class RVPersonajeViewModel @Inject constructor(
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
    ) {
        when (event) {
            is PersonajeContract.Event.FetchPersonaje -> fetchPersonaje(event.id)
            PersonajeContract.Event.FetchPersonajes -> fetchPersonajes()
            is PersonajeContract.Event.PostPersonaje -> postPersonaje(event.personaje)
            is PersonajeContract.Event.PutPersonaje -> putPersonaje(event.personaje)
            is PersonajeContract.Event.DeletePersonaje -> deletePersonaje(event.id)
        }
    }

    private fun fetchPersonaje(id: Int) {
        viewModelScope.launch {
            personajeRepository.getPersonaje(id)
                .catch(action = { cause ->
                    _uiError.send(cause.message ?: "Error")
                    Timber.tag("Error").e(cause)
                })
                .collect { result ->
                    when (result) {
                        is NetworkResult.Error -> {
                            _uiState.update { it.copy(error = result.message) }
                            Timber.tag("Error").e(result.message)
                        }
                        is NetworkResult.Loading -> _uiState.update { it.copy(isLoading = true) }
                        is NetworkResult.Success -> _uiState.update {
                            PersonajeContract.State(personaje = result.data, isLoading = false)
                        }
                    }
                }
        }
    }

    private fun fetchPersonajes() {
        viewModelScope.launch {
            personajeRepository.getPersonajes()
                .catch(action = { cause ->
                    _uiError.send(cause.message ?: "Error")
                    Timber.tag("Error").e(cause)
                })
                .collect { result ->
                    when (result) {
                        is NetworkResult.Error -> {
                            _uiState.update { it.copy(error = result.message) }
                            Timber.tag("Error").e(result.message)
                        }
                        is NetworkResult.Loading -> _uiState.update { it.copy(isLoading = true) }
                        is NetworkResult.Success -> _uiState.update {
                            PersonajeContract.State(listaPersonajes = result.data ?: emptyList(), isLoading = false)
                        }
                    }
                }
        }
    }

    private fun postPersonaje(personaje: Personaje) {
        viewModelScope.launch {
            personajeRepository.insertPersonaje(personaje)
                .catch(action = { cause ->
                    _uiError.send(cause.message ?: "Error")
                    Timber.tag("Error").e(cause)
                })
                .collect { result ->
                    when (result) {
                        is NetworkResult.Error -> {
                            _uiState.update { it.copy(error = result.message) }
                            Timber.tag("Error").e(result.message)
                        }
                        is NetworkResult.Loading -> _uiState.update { it.copy(isLoading = true) }
                        is NetworkResult.Success -> _uiState.update {
                            PersonajeContract.State(personaje = result.data, isLoading = false)
                        }
                    }
                }
        }
    }

    private fun putPersonaje(personaje: Personaje) {
        viewModelScope.launch {
            personajeRepository.updatePersonaje(personaje)
                .catch(action = { cause ->
                    _uiError.send(cause.message ?: "Error")
                    Timber.tag("Error").e(cause)
                })
                .collect { result ->
                    when (result) {
                        is NetworkResult.Error -> {
                            _uiState.update { it.copy(error = result.message) }
                            Timber.tag("Error").e(result.message)
                        }
                        is NetworkResult.Loading -> _uiState.update { it.copy(isLoading = true) }
                        is NetworkResult.Success -> _uiState.update {
                            PersonajeContract.State(personaje = result.data, isLoading = false)
                        }
                    }
                }
        }

    }

    private fun deletePersonaje(idPersonaje: Int) {
        viewModelScope.launch {
            personajeRepository.deletePersonaje(idPersonaje)
                .catch(action = { cause ->
                    _uiError.send(cause.message ?: "Error")
                    Timber.tag("Error").e(cause)
                })
                .collect { result ->
                    when (result) {
                        is NetworkResult.Error -> {
                            _uiState.update { it.copy(error = result.message) }
                            Timber.tag("Error").e(result.message)
                        }
                        is NetworkResult.Loading -> _uiState.update { it.copy(isLoading = true) }
                        is NetworkResult.Success -> _uiState.update {
                            PersonajeContract.State(personaje = result.data, isLoading = false)
                        }
                    }
                }
        }
    }
}