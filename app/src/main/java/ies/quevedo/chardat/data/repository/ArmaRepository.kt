package ies.quevedo.chardat.data.repository

import ies.quevedo.chardat.data.entities.toArma
import ies.quevedo.chardat.data.entities.toArmaEntity
import ies.quevedo.chardat.data.local.DAOArma
import ies.quevedo.chardat.data.remote.dataSources.ArmaRemoteDataSource
import ies.quevedo.chardat.data.utils.NetworkResult
import ies.quevedo.chardat.domain.model.Arma
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class ArmaRepository @Inject constructor(
    private val daoArma: DAOArma,
    private val armaRemoteDataSource: ArmaRemoteDataSource
) {

    suspend fun getArmas(idPJ: Int): Flow<NetworkResult<List<Arma>>> {
        return flow {
            emit(fetchArmasCached(idPJ))
            emit(NetworkResult.Loading())
            val result = armaRemoteDataSource.fetchArmas(idPJ)
            if (result is NetworkResult.Success) {
                result.data?.let { armas ->
                    daoArma.deleteAll(armas.map { it.toArmaEntity(idPJ) })
                    daoArma.insertAll(armas.map { it.toArmaEntity(idPJ) })
                }
            }
            emit(result)
        }.flowOn(Dispatchers.IO)
    }

    suspend fun insertArma(arma: Arma): Flow<NetworkResult<Arma>> {
        return flow {
            emit(NetworkResult.Loading())
            val result = armaRemoteDataSource.postArma(arma)
            if (result is NetworkResult.Success) {
                result.data?.let { arma ->
                    daoArma.insertArma(arma.toArmaEntity(arma.idPJ))
                }
            }
            emit(result)
        }.flowOn(Dispatchers.IO)
    }


    suspend fun updateArma(arma: Arma): Flow<NetworkResult<Arma>> {
        return flow {
            emit(NetworkResult.Loading())
            val result = armaRemoteDataSource.putArma(arma)
            if (result is NetworkResult.Success) {
                result.data?.let { arma ->
                    daoArma.updateArma(arma.toArmaEntity(arma.idPJ))
                }
            }
            emit(result)
        }.flowOn(Dispatchers.IO)
    }

    suspend fun deleteArma(idArma: Int): Flow<NetworkResult<Arma>> {
        return flow {
            emit(NetworkResult.Loading())
            val result = armaRemoteDataSource.deleteArma(idArma)
            if (result is NetworkResult.Success) {
                result.data?.let { arma ->
                    daoArma.deleteArma(arma.toArmaEntity(arma.idPJ))
                }
            }
            emit(result)
        }.flowOn(Dispatchers.IO)
    }

    private fun fetchArmasCached(idPJ: Int): NetworkResult<List<Arma>> =
        daoArma.getArmas(idPJ).let { armas -> NetworkResult.Success(armas.map { it.toArma() }) }
}