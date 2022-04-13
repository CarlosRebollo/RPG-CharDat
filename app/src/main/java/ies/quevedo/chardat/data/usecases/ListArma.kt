package ies.quevedo.chardat.data.usecases

import ies.quevedo.chardat.data.Repository
import ies.quevedo.chardat.data.entities.toArma
import javax.inject.Inject

class ListArma @Inject constructor(private val repository: Repository) {

    suspend fun invoke(idPJ: Int) = repository.getArmas(idPJ).map { it.toArma() }

}