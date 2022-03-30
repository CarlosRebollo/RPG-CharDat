package ies.quevedo.chardat.data.usecases

import ies.quevedo.chardat.data.Repository
import ies.quevedo.chardat.data.entities.toEquipoEntity
import ies.quevedo.chardat.domain.Arma
import javax.inject.Inject

class DeleteArma @Inject constructor(private val repository: Repository) {

    suspend fun deleteEquipo(arma: Arma) = repository.deleteEquipo(arma.toEquipoEntity())

}