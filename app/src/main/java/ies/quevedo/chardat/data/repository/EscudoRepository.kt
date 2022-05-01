package ies.quevedo.chardat.data.repository

import ies.quevedo.chardat.data.entities.toEscudo
import ies.quevedo.chardat.data.entities.toEscudoEntity
import ies.quevedo.chardat.data.local.DAOEscudo
import ies.quevedo.chardat.data.remote.dataSources.EscudoRemoteDataSource
import ies.quevedo.chardat.data.utils.NetworkResult
import ies.quevedo.chardat.domain.model.Escudo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class EscudoRepository @Inject constructor(
    private val daoEscudo: DAOEscudo,
    private val escudoRemoteDataSource: EscudoRemoteDataSource
) {

    suspend fun getEscudos(idPJ: Int): Flow<NetworkResult<List<Escudo>>> {
        return flow {
            emit(fetchEscudosCached(idPJ))
            emit(NetworkResult.Loading())
            val result = escudoRemoteDataSource.fetchEscudos(idPJ)
            if (result is NetworkResult.Success) {
                result.data?.let { escudos ->
                    daoEscudo.deleteAll(escudos.map { it.toEscudoEntity(idPJ) })
                    daoEscudo.insertAll(escudos.map { it.toEscudoEntity(idPJ) })
                }
            }
            emit(result)
        }.flowOn(Dispatchers.IO)
    }

    suspend fun insertEscudo(escudo: Escudo): Flow<NetworkResult<Escudo>> {
        return flow {
            emit(NetworkResult.Loading())
            val result = escudoRemoteDataSource.postEscudo(escudo)
            if (result is NetworkResult.Success) {
                result.data?.let { escudo ->
                    daoEscudo.insertEscudo(escudo.toEscudoEntity(escudo.idPJ))
                }
            }
            emit(result)
        }.flowOn(Dispatchers.IO)
    }


    suspend fun updateEscudo(escudo: Escudo): Flow<NetworkResult<Escudo>> {
        return flow {
            emit(NetworkResult.Loading())
            val result = escudoRemoteDataSource.putEscudo(escudo)
            if (result is NetworkResult.Success) {
                result.data?.let { escudo ->
                    daoEscudo.updateEscudo(escudo.toEscudoEntity(escudo.idPJ))
                }
            }
            emit(result)
        }.flowOn(Dispatchers.IO)
    }

    suspend fun deleteEscudo(idEscudo: Int): Flow<NetworkResult<Escudo>> {
        return flow {
            emit(NetworkResult.Loading())
            val result = escudoRemoteDataSource.deleteEscudo(idEscudo)
            if (result is NetworkResult.Success) {
                result.data?.let { escudo ->
                    daoEscudo.deleteEscudo(escudo.toEscudoEntity(escudo.idPJ))
                }
            }
            emit(result)
        }.flowOn(Dispatchers.IO)
    }

    private fun fetchEscudosCached(idPJ: Int): NetworkResult<List<Escudo>> =
        daoEscudo.getEscudos(idPJ)
            .let { escudos -> NetworkResult.Success(escudos.map { it.toEscudo() }) }
}