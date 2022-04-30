package ies.quevedo.chardat.data.repository

import ies.quevedo.chardat.data.dao.DAOPersonaje
import ies.quevedo.chardat.data.entities.*
import ies.quevedo.chardat.domain.Personaje
import javax.inject.Inject

class PersonajeRepository @Inject constructor(
    private val daoPersonaje: DAOPersonaje,
    private val armaRepository: ArmaRepository,
    private val armaduraRepository: ArmaduraRepository,
    private val escudoRepository: EscudoRepository,
    private val objetoRepository: ObjetoRepository
) {

    suspend fun getPersonajesConTodo(): List<Personaje> {
        val personajes = daoPersonaje.getPersonajes().map { it.toPersonaje() }
        for (personaje in personajes) {
            personaje.armas = armaRepository.getArmas(personaje.id)
            personaje.armaduras =
                armaduraRepository.getArmaduras(personaje.id)
            personaje.escudos = escudoRepository.getEscudos(personaje.id)
            personaje.objetos = objetoRepository.getObjetos(personaje.id)
        }
        return personajes
    }

    suspend fun getPersonajes(): List<Personaje> =
        daoPersonaje.getPersonajes().map { it.toPersonaje() }

    suspend fun insertPersonaje(personaje: Personaje) =
        daoPersonaje.insertPersonaje(personaje.toPersonajeEntity())

    suspend fun insertPersonajeConTodo(personaje: Personaje) =
        daoPersonaje.insertPersonajeConTodo(personaje.toPersonajeConTodo())


    suspend fun deletePersonaje(personaje: Personaje) {
        daoPersonaje.deletePersonaje(
            personaje.toPersonajeEntity(),
            personaje.armaduras?.map { it.toArmaduraEntity(personaje.id) },
            personaje.armas?.map { it.toArmaEntity(personaje.id) },
            personaje.escudos?.map { it.toEscudoEntity(personaje.id) },
            personaje.objetos?.map { it.toObjetoEntity(personaje.id) }
        )
    }

    suspend fun updatePersonaje(personaje: Personaje) =
        daoPersonaje.updatePersonaje(personaje.toPersonajeConTodo())
}