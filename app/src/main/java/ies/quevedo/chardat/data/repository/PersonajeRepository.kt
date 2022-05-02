package ies.quevedo.chardat.data.repository

import ies.quevedo.chardat.data.entities.*
import ies.quevedo.chardat.data.local.DAOPersonaje
import ies.quevedo.chardat.data.remote.dataSources.PersonajeRemoteDataSource
import ies.quevedo.chardat.data.utils.NetworkResult
import ies.quevedo.chardat.domain.model.Personaje
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class PersonajeRepository @Inject constructor(
    private val daoPersonaje: DAOPersonaje,
    private val personajeRemoteDataSource: PersonajeRemoteDataSource
) {

    fun getPersonajeConTodo(id: Int): Flow<NetworkResult<Personaje>> {
        return flow {
            emit(fetchPersonajeConTodoCached(id))
            emit(NetworkResult.Loading())
            val result = personajeRemoteDataSource.fetchPersonaje(id)
            if (result is NetworkResult.Success) {
                result.data?.let {
                    daoPersonaje.getPersonaje(id).toPersonaje()
                }
            }
            emit(result)
        }.flowOn(Dispatchers.IO)
    }

    fun getPersonajesConTodo(): Flow<NetworkResult<List<Personaje>>> {
        return flow {
            emit(fetchedPersonajesCached())
            emit(NetworkResult.Loading())
            val result = personajeRemoteDataSource.fetchPersonajes()
            if (result is NetworkResult.Success) {
                result.data?.let {
                    daoPersonaje.getPersonajes().map { it.toPersonaje() }
                }
            }
            emit(result)
        }.flowOn(Dispatchers.IO)
    }

    fun insertPersonaje(personaje: Personaje): Flow<NetworkResult<Personaje>> {
        return flow {
            emit(NetworkResult.Loading())
            val result = personajeRemoteDataSource.postPersonaje(personaje)
            if (result is NetworkResult.Success) {
                result.data?.let { personaje ->
                    daoPersonaje.insertPersonaje(personaje.toPersonajeEntity())
                }
            }
            emit(result)
        }.flowOn(Dispatchers.IO)
    }

    fun updatePersonaje(personaje: Personaje): Flow<NetworkResult<Personaje>> {
        return flow {
            emit(NetworkResult.Loading())
            val result = personajeRemoteDataSource.putPersonaje(personaje)
            if (result is NetworkResult.Success) {
                result.data?.let { personaje ->
                    daoPersonaje.updatePersonaje(personaje.toPersonajeEntity())
                }
            }
            emit(result)
        }.flowOn(Dispatchers.IO)
    }

    fun deletePersonaje(idPersonaje: Int): Flow<NetworkResult<Personaje>> {
        return flow {
            emit(NetworkResult.Loading())
            val result = personajeRemoteDataSource.deletePersonaje(idPersonaje)
            if (result is NetworkResult.Success) {
                result.data?.let { personaje ->
                    daoPersonaje.deletePersonaje(
                        personaje.toPersonajeEntity(),
                        personaje.armaduras?.map { it.toArmaduraEntity(idPersonaje) }
                            ?: emptyList(),
                        personaje.armas?.map { it.toArmaEntity(idPersonaje) }
                            ?: emptyList(),
                        personaje.escudos?.map { it.toEscudoEntity(idPersonaje) }
                            ?: emptyList(),
                        personaje.objetos?.map { it.toObjetoEntity(idPersonaje) }
                            ?: emptyList())
                }
            }
            emit(result)
        }.flowOn(Dispatchers.IO)
    }

    private fun fetchPersonajeConTodoCached(id: Int): NetworkResult<Personaje> =
        daoPersonaje.getPersonaje(id)
            .let { personaje -> NetworkResult.Success(personaje.toPersonaje()) }

    private fun fetchedPersonajesCached(): NetworkResult<List<Personaje>> =
        daoPersonaje.getPersonajes()
            .let { personajes -> NetworkResult.Success(personajes.map { it.toPersonaje() }) }
}