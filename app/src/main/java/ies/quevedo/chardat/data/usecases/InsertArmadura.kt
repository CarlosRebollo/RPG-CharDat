package ies.quevedo.chardat.data.usecases

import ies.quevedo.chardat.data.Repository
import ies.quevedo.chardat.data.entities.toArmaduraEntity
import ies.quevedo.chardat.domain.Armadura
import javax.inject.Inject

class InsertArmadura @Inject constructor(private val repository: Repository) {

    suspend fun invoke(armadura: Armadura) = repository.insertArmadura(armadura.toArmaduraEntity())

}