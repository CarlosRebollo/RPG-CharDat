package ies.quevedo.chardat.data.usecases

import ies.quevedo.chardat.data.Repository
import ies.quevedo.chardat.data.entities.toArma
import javax.inject.Inject

class ListArma @Inject constructor(private val repository: Repository) {

    suspend fun getEquipamiento(idPJ: Int) = repository.getEquipamiento(idPJ).map { it.toArma() }

}