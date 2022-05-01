package ies.quevedo.chardat.data.repository

import ies.quevedo.chardat.data.entities.toArmadura
import ies.quevedo.chardat.data.entities.toArmaduraEntity
import ies.quevedo.chardat.data.local.DAOArmadura
import ies.quevedo.chardat.data.remote.dataSources.ArmaduraRemoteDataSource
import ies.quevedo.chardat.data.utils.NetworkResult
import ies.quevedo.chardat.domain.model.Armadura
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class ArmaduraRepository @Inject constructor(
    private val daoArmadura: DAOArmadura,
    private val armaduraRemoteDataSource: ArmaduraRemoteDataSource
) {

    fun getArmaduras(idPJ: Int): Flow<NetworkResult<List<Armadura>>> {
        return flow {
            emit(fetchArmadurasCached(idPJ))
            emit(NetworkResult.Loading())
            val result = armaduraRemoteDataSource.fetchArmaduras(idPJ)
            if (result is NetworkResult.Success) {
                result.data?.let { armaduras ->
                    daoArmadura.deleteAll(armaduras.map { it.toArmaduraEntity(idPJ) })
                    daoArmadura.insertAll(armaduras.map { it.toArmaduraEntity(idPJ) })
                }
            }
            emit(result)
        }.flowOn(Dispatchers.IO)
    }

    fun insertArmadura(armadura: Armadura): Flow<NetworkResult<Armadura>> {
        return flow {
            emit(NetworkResult.Loading())
            val result = armaduraRemoteDataSource.postArmadura(armadura)
            if (result is NetworkResult.Success) {
                result.data?.let { armadura ->
                    daoArmadura.insertArmadura(armadura.toArmaduraEntity(armadura.idPJ))
                }
            }
            emit(result)
        }.flowOn(Dispatchers.IO)
    }

    fun updateArmadura(armadura: Armadura): Flow<NetworkResult<Armadura>> {
        return flow {
            emit(NetworkResult.Loading())
            val result = armaduraRemoteDataSource.putArmadura(armadura)
            if (result is NetworkResult.Success) {
                result.data?.let { armadura ->
                    daoArmadura.updateArmadura(armadura.toArmaduraEntity(armadura.idPJ))
                }
            }
            emit(result)
        }.flowOn(Dispatchers.IO)
    }

    fun deleteArmadura(idArmadura: Int): Flow<NetworkResult<Armadura>> {
        return flow {
            emit(NetworkResult.Loading())
            val result = armaduraRemoteDataSource.deleteArmadura(idArmadura)
            if (result is NetworkResult.Success) {
                result.data?.let { armadura ->
                    daoArmadura.deleteArmadura(armadura.toArmaduraEntity(armadura.idPJ))
                }
            }
            emit(result)
        }.flowOn(Dispatchers.IO)
    }

    private fun fetchArmadurasCached(idPJ: Int): NetworkResult<List<Armadura>> =
        daoArmadura.getArmaduras(idPJ)
            .let { armaduras -> NetworkResult.Success(armaduras.map { it.toArmadura() }) }
}