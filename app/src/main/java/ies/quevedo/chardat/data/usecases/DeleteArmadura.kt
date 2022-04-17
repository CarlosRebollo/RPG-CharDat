package ies.quevedo.chardat.data.usecases

import ies.quevedo.chardat.data.Repository
import ies.quevedo.chardat.data.entities.toArmaduraEntity
import ies.quevedo.chardat.domain.Armadura
import javax.inject.Inject

class DeleteArmadura @Inject constructor(private val repository: Repository) {

    suspend fun invoke(armadura: Armadura) = repository.deleteArmadura(armadura.toArmaduraEntity())

}