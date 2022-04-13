package ies.quevedo.chardat.data.usecases

import ies.quevedo.chardat.data.Repository
import ies.quevedo.chardat.data.entities.toPersonajeEntity
import ies.quevedo.chardat.domain.Personaje
import javax.inject.Inject

class DeletePersonaje @Inject constructor(private val repository: Repository) {

    suspend fun invoke(personaje: Personaje) =
        repository.deletePersonaje(personaje.toPersonajeEntity())

}