package ies.quevedo.chardat.framework.fragmentListArmas

import ies.quevedo.chardat.domain.model.Arma

interface ArmaListContract {

    sealed class Event {
        class FetchArmas(val idPersonaje: Int) : Event()
        class DeleteArma(val idArma: Int) : Event()
        class PostArma(arma: Arma) : Event() {

        }
    }

    data class State(
        val arma: Arma? = null,
        val listaArmas: List<Arma>? = null,
        val isLoading: Boolean = false,
        val error: String? = null
    )
}