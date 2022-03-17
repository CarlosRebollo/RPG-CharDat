package ies.quevedo.chardat.data.usecases

import ies.quevedo.chardat.data.Repository
import ies.quevedo.chardat.data.entities.toEquipoEntity
import ies.quevedo.chardat.domain.Equipo
import javax.inject.Inject

class DeleteEquipo @Inject constructor(private val repository: Repository) {

    suspend fun deleteEquipo(equipo: Equipo) = repository.deleteEquipo(equipo.toEquipoEntity())

}