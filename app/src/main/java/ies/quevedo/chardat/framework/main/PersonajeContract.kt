package ies.quevedo.chardat.framework.main

import ies.quevedo.chardat.domain.model.Personaje

interface PersonajeContract {

    sealed class Event {
        object FetchPersonaje : Event()
        object FetchPersonajes : Event()
        object PostPersonaje : Event()
        object PutPersonaje : Event()
        object DeletePersonaje : Event()
        object ShowMessage : Event()
    }

    data class State(
        val personajes: List<Personaje> = emptyList(),
        val personaje: Personaje? = null,
        val isLoading: Boolean = false,
        val error: String? = null
    )
}