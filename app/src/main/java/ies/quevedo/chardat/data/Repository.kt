package ies.quevedo.chardat.data

import ies.quevedo.chardat.data.entities.ArmaEntity
import ies.quevedo.chardat.data.entities.PersonajeEntity
import javax.inject.Inject

class Repository @Inject constructor(private val charDatDAO: CharDatDAO) {

    suspend fun getPersonajes(): List<PersonajeEntity> = charDatDAO.getPersonajes()

    suspend fun getEquipamiento(idPJ: Int): List<ArmaEntity> = charDatDAO.getEquipamiento(idPJ)

    suspend fun insertPersonaje(personaje: PersonajeEntity) = charDatDAO.insertPersonaje(personaje)

    suspend fun insertEquipo(arma: ArmaEntity) = charDatDAO.insertEquipo(arma)

    suspend fun deletePersonaje(personaje: PersonajeEntity) = charDatDAO.deletePersonaje(personaje)

    suspend fun deleteEquipo(arma: ArmaEntity) = charDatDAO.deleteEquipo(arma)

    suspend fun updatePersonaje(personaje: PersonajeEntity) = charDatDAO.updatePersonaje(personaje)

    suspend fun updateEquipo(arma: ArmaEntity) = charDatDAO.updateEquipo(arma)

}