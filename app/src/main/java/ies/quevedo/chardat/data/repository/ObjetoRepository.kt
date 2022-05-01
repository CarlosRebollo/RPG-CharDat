package ies.quevedo.chardat.data.repository

import ies.quevedo.chardat.data.local.DAOObjeto
import ies.quevedo.chardat.data.entities.toObjeto
import ies.quevedo.chardat.data.entities.toObjetoEntity
import ies.quevedo.chardat.domain.model.Objeto
import javax.inject.Inject

class ObjetoRepository @Inject constructor(private val daoObjeto: DAOObjeto) {

    suspend fun getObjetos(idPJ: Int): List<Objeto> =
        daoObjeto.getObjetos(idPJ).map { it.toObjeto() }

    suspend fun insertObjeto(objeto: Objeto) =
        daoObjeto.insertObjeto(objeto.toObjetoEntity(objeto.idPJ))

    suspend fun deleteObjeto(objeto: Objeto) =
        daoObjeto.deleteObjeto(objeto.toObjetoEntity(objeto.idPJ))

    suspend fun updateObjeto(objeto: Objeto) =
        daoObjeto.updateObjeto(objeto.toObjetoEntity(objeto.idPJ))
}