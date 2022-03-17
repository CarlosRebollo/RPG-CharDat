package ies.quevedo.chardat.data.usecases

import ies.quevedo.chardat.data.Repository
import ies.quevedo.chardat.data.entities.toEquipo
import javax.inject.Inject

class ListEquipo @Inject constructor(private val repository: Repository) {

    suspend fun getEquipamiento(idPJ: Int) = repository.getEquipamiento(idPJ).map { it.toEquipo() }

}