package ies.quevedo.chardat.data.usecases

import ies.quevedo.chardat.data.Repository
import ies.quevedo.chardat.data.entities.toEscudo
import javax.inject.Inject

class ListEscudo @Inject constructor(private val repository: Repository) {

    suspend fun invoke(idPJ: Int) = repository.getEscudos(idPJ).map { it.toEscudo() }

}