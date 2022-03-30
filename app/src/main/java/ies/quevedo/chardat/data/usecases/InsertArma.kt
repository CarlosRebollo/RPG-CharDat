package ies.quevedo.chardat.data.usecases

import ies.quevedo.chardat.data.Repository
import ies.quevedo.chardat.data.entities.toEquipoEntity
import ies.quevedo.chardat.domain.Arma
import javax.inject.Inject

class InsertArma @Inject constructor(private val repository: Repository) {

    suspend fun insertEquipo(arma: Arma) = repository.insertEquipo(arma.toEquipoEntity())

}