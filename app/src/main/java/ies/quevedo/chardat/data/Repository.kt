package ies.quevedo.chardat.data

import ies.quevedo.chardat.data.entities.ArmaEntity
import ies.quevedo.chardat.data.entities.PersonajeEntity
import javax.inject.Inject

class Repository @Inject constructor(private val charDatDAO: CharDatDAO) {

    suspend fun getPersonajes(): List<PersonajeEntity> = charDatDAO.getPersonajes()

    suspend fun getArmas(idPJ: Int): List<ArmaEntity> = charDatDAO.getArmas(idPJ)

    suspend fun insertPersonaje(personaje: PersonajeEntity) = charDatDAO.insertPersonaje(personaje)

    suspend fun insertArma(arma: ArmaEntity) = charDatDAO.insertArma(arma)

    suspend fun deletePersonaje(personaje: PersonajeEntity) = charDatDAO.deletePersonaje(personaje)

    suspend fun deleteArma(arma: ArmaEntity) = charDatDAO.deleteArma(arma)

    suspend fun updatePersonaje(personaje: PersonajeEntity) = charDatDAO.updatePersonaje(personaje)

    suspend fun updateArma(arma: ArmaEntity) = charDatDAO.updateArma(arma)

}