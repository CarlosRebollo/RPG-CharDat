package ies.quevedo.chardat.data.usecases

import ies.quevedo.chardat.data.Repository
import ies.quevedo.chardat.data.entities.toArmadura
import javax.inject.Inject

class ListArmadura @Inject constructor(private val repository: Repository) {

    suspend fun invoke(idPJ: Int) = repository.getArmaduras(idPJ).map { it.toArmadura() }

}