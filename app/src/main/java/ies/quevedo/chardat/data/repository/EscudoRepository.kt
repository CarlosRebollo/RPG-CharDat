package ies.quevedo.chardat.data.repository

import ies.quevedo.chardat.data.remote.dataSources.EscudoRemoteDataSource
import ies.quevedo.chardat.data.utils.NetworkResult
import ies.quevedo.chardat.domain.model.Escudo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class EscudoRepository @Inject constructor(
    private val escudoRemoteDataSource: EscudoRemoteDataSource
) {

    suspend fun getEscudos(idPJ: Int): Flow<NetworkResult<List<Escudo>>> {
        return flow {
            emit(NetworkResult.Loading())
            emit(escudoRemoteDataSource.fetchEscudos(idPJ))
        }.flowOn(Dispatchers.IO)
    }

    suspend fun insertEscudo(escudo: Escudo): Flow<NetworkResult<Escudo>> {
        return flow {
            emit(NetworkResult.Loading())
            emit(escudoRemoteDataSource.postEscudo(escudo))
        }.flowOn(Dispatchers.IO)
    }


    suspend fun updateEscudo(escudo: Escudo): Flow<NetworkResult<Escudo>> {
        return flow {
            emit(NetworkResult.Loading())
            emit(escudoRemoteDataSource.putEscudo(escudo))
        }.flowOn(Dispatchers.IO)
    }

    suspend fun deleteEscudo(idEscudo: Int): Flow<NetworkResult<Escudo>> {
        return flow {
            emit(NetworkResult.Loading())
            emit(escudoRemoteDataSource.deleteEscudo(idEscudo))
        }.flowOn(Dispatchers.IO)
    }

}