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

    suspend fun getArmaduras(idPJ: Int): Flow<NetworkResult<List<Armadura>>> {
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

    private suspend fun fetchArmadurasCached(idPJ: Int): NetworkResult<List<Armadura>> =
        daoArmadura.getArmaduras(idPJ)
            .let { armaduras -> NetworkResult.Success(armaduras.map { it.toArmadura() }) }


    //TODO: Buscar una forma de hacer esto con Flows porque en el proyecto de oscar solo tiene ejemplos de pedir datos y no de tratarlos
    suspend fun insertArmadura(armadura: Armadura) =
        daoArmadura.insertArmadura(armadura.toArmaduraEntity(armadura.idPJ))

    suspend fun deleteArmadura(armadura: Armadura) =
        daoArmadura.deleteArmadura(armadura.toArmaduraEntity(armadura.idPJ))

    suspend fun updateArmadura(armadura: Armadura) =
        daoArmadura.updateArmadura(armadura.toArmaduraEntity(armadura.idPJ))
}