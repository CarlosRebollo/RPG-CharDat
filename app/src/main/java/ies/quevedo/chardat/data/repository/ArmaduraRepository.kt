package ies.quevedo.chardat.data.repository

import ies.quevedo.chardat.data.dao.DAOArmadura
import ies.quevedo.chardat.data.entities.toArmadura
import ies.quevedo.chardat.data.entities.toArmaduraEntity
import ies.quevedo.chardat.domain.Armadura
import javax.inject.Inject

class ArmaduraRepository @Inject constructor(private val daoArmadura: DAOArmadura) {

    suspend fun getArmaduras(idPJ: Int): List<Armadura> =
        daoArmadura.getArmaduras(idPJ).map { it.toArmadura() }

    suspend fun insertArmadura(armadura: Armadura) =
        daoArmadura.insertArmadura(armadura.toArmaduraEntity(armadura.idPJ))

    suspend fun deleteArmadura(armadura: Armadura) =
        daoArmadura.deleteArmadura(armadura.toArmaduraEntity(armadura.idPJ))

    suspend fun updateArmadura(armadura: Armadura) =
        daoArmadura.updateArmadura(armadura.toArmaduraEntity(armadura.idPJ))
}