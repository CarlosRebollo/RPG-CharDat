package ies.quevedo.chardat.data.usecases

import ies.quevedo.chardat.data.Repository
import ies.quevedo.chardat.data.entities.toPersonaje
import javax.inject.Inject

class ListPersonajes @Inject constructor(private val repository: Repository)  {

    suspend fun getPersonajes() = repository.getPersonajes().map { it.toPersonaje() }

}