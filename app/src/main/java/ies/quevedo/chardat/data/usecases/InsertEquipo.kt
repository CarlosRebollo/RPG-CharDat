package ies.quevedo.chardat.data.usecases

import ies.quevedo.chardat.data.Repository
import ies.quevedo.chardat.data.entities.toEquipoEntity
import ies.quevedo.chardat.domain.Equipo
import javax.inject.Inject

class InsertEquipo @Inject constructor(private val repository: Repository) {

    suspend fun insertEquipo(equipo: Equipo) = repository.insertEquipo(equipo.toEquipoEntity())

}