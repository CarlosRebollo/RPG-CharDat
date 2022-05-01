package ies.quevedo.chardat.data.repository

import ies.quevedo.chardat.data.entities.toObjeto
import ies.quevedo.chardat.data.entities.toObjetoEntity
import ies.quevedo.chardat.data.local.DAOObjeto
import ies.quevedo.chardat.data.remote.dataSources.ObjetoRemoteDataSource
import ies.quevedo.chardat.data.utils.NetworkResult
import ies.quevedo.chardat.domain.model.Objeto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class ObjetoRepository @Inject constructor(
    private val daoObjeto: DAOObjeto,
    private val objetoRemoteDataSource: ObjetoRemoteDataSource
) {

    fun getObjetos(idPJ: Int): Flow<NetworkResult<List<Objeto>>> {
        return flow {
            emit(fetchObjetosCached(idPJ))
            emit(NetworkResult.Loading())
            val result = objetoRemoteDataSource.fetchObjetos(idPJ)
            if (result is NetworkResult.Success) {
                result.data?.let { objetos ->
                    daoObjeto.deleteAll(objetos.map { it.toObjetoEntity(idPJ) })
                    daoObjeto.insertAll(objetos.map { it.toObjetoEntity(idPJ) })
                }
            }
            emit(result)
        }.flowOn(Dispatchers.IO)
    }

    private fun fetchObjetosCached(idPJ: Int): NetworkResult<List<Objeto>> =
        daoObjeto.getObjetos(idPJ)
            .let { objetos -> NetworkResult.Success(objetos.map { it.toObjeto() }) }

    suspend fun insertObjeto(objeto: Objeto) =
        daoObjeto.insertObjeto(objeto.toObjetoEntity(objeto.idPJ))

    suspend fun deleteObjeto(objeto: Objeto) =
        daoObjeto.deleteObjeto(objeto.toObjetoEntity(objeto.idPJ))

    suspend fun updateObjeto(objeto: Objeto) =
        daoObjeto.updateObjeto(objeto.toObjetoEntity(objeto.idPJ))
}