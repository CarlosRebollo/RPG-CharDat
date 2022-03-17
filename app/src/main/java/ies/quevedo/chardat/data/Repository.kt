package ies.quevedo.chardat.data

import ies.quevedo.chardat.data.entities.EquipoEntity
import ies.quevedo.chardat.data.entities.ObjetoEntity
import ies.quevedo.chardat.data.entities.PersonajeEntity
import javax.inject.Inject

class Repository @Inject constructor(private val charDatDAO: CharDatDAO) {

    suspend fun getPersonajes(): List<PersonajeEntity> = charDatDAO.getPersonajes()

    suspend fun getEquipamiento(idPJ: Int): List<EquipoEntity> = charDatDAO.getEquipamiento(idPJ)

    suspend fun getObjetos(idPJ: Int): List<ObjetoEntity> = charDatDAO.getObjetos(idPJ)

    suspend fun insertPersonaje(personaje: PersonajeEntity) = charDatDAO.insertPersonaje(personaje)

    suspend fun insertEquipo(equipo: EquipoEntity) = charDatDAO.insertEquipo(equipo)

    suspend fun insertObjeto(objeto: ObjetoEntity) = charDatDAO.insertObjeto(objeto)

    suspend fun deletePersonaje(personaje: PersonajeEntity) = charDatDAO.deletePersonaje(personaje)

    suspend fun deleteEquipo(equipo: EquipoEntity) = charDatDAO.deleteEquipo(equipo)

    suspend fun deleteObjeto(objeto: ObjetoEntity) = charDatDAO.deleteObjeto(objeto)

    suspend fun updatePersonaje(personaje: PersonajeEntity) = charDatDAO.updatePersonaje(personaje)

    suspend fun updateEquipo(equipo: EquipoEntity) = charDatDAO.updateEquipo(equipo)

    suspend fun updateObjeto(objeto: ObjetoEntity) = charDatDAO.updateObjeto(objeto)

}