package ies.quevedo.chardat.framework.mainMenu

import ies.quevedo.chardat.domain.model.Personaje

interface MainMenuContract {

    sealed class Event {
        class FetchPersonaje(val id: Int) : Event()
    }

    data class State(
        val personaje: Personaje? = null,
        val isLoading: Boolean = false,
        val error: String? = null
    )
}