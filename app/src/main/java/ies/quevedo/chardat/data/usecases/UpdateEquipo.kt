package ies.quevedo.chardat.data.usecases

import ies.quevedo.chardat.data.Repository
import ies.quevedo.chardat.data.entities.toEquipoEntity
import ies.quevedo.chardat.domain.Arma
import javax.inject.Inject

class UpdateEquipo @Inject constructor(private val repository: Repository) {

    suspend fun updateEquipo(arma: Arma) = repository.updateEquipo(arma.toEquipoEntity())

}