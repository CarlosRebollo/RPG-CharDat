package ies.quevedo.chardat.data.usecases

import ies.quevedo.chardat.data.Repository
import ies.quevedo.chardat.data.entities.toArmaEntity
import ies.quevedo.chardat.domain.Arma
import javax.inject.Inject

class InsertArma @Inject constructor(private val repository: Repository) {

    suspend fun invoke(arma: Arma) = repository.insertArma(arma.toArmaEntity())

}