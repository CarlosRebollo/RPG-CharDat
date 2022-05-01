package ies.quevedo.chardat.data.repository

import ies.quevedo.chardat.data.local.DAOArma
import ies.quevedo.chardat.data.entities.toArma
import ies.quevedo.chardat.data.entities.toArmaEntity
import ies.quevedo.chardat.domain.model.Arma
import javax.inject.Inject

class ArmaRepository @Inject constructor(private val daoArma: DAOArma) {

    suspend fun getArmas(idPJ: Int): List<Arma> = daoArma.getArmas(idPJ).map { it.toArma() }

    suspend fun insertArma(arma: Arma) = daoArma.insertArma(arma.toArmaEntity(arma.idPJ))

    suspend fun deleteArma(arma: Arma) = daoArma.deleteArma(arma.toArmaEntity(arma.idPJ))

    suspend fun updateArma(arma: Arma) = daoArma.updateArma(arma.toArmaEntity(arma.idPJ))
}