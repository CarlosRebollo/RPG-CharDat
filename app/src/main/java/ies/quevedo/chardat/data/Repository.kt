package ies.quevedo.chardat.data

import ies.quevedo.chardat.data.entities.*
import javax.inject.Inject

class Repository @Inject constructor(private val charDatDAO: CharDatDAO) {

    suspend fun getPersonajes(): List<PersonajeEntity> = charDatDAO.getPersonajes()

    suspend fun insertPersonaje(personaje: PersonajeEntity) = charDatDAO.insertPersonaje(personaje)

    suspend fun deletePersonaje(personaje: PersonajeEntity) = charDatDAO.deletePersonaje(personaje)

    suspend fun updatePersonaje(personaje: PersonajeEntity) = charDatDAO.updatePersonaje(personaje)

    suspend fun getArmas(idPJ: Int): List<ArmaEntity> = charDatDAO.getArmas(idPJ)

    suspend fun insertArma(arma: ArmaEntity) = charDatDAO.insertArma(arma)

    suspend fun deleteArma(arma: ArmaEntity) = charDatDAO.deleteArma(arma)

    suspend fun updateArma(arma: ArmaEntity) = charDatDAO.updateArma(arma)

    suspend fun getArmaduras(idPJ: Int): List<ArmaduraEntity> = charDatDAO.getArmaduras(idPJ)

    suspend fun insertArmadura(armadura: ArmaduraEntity) = charDatDAO.insertArmadura(armadura)

    suspend fun deleteArmadura(armadura: ArmaduraEntity) = charDatDAO.deleteArmadura(armadura)

    suspend fun updateArmadura(armadura: ArmaduraEntity) = charDatDAO.updateArmadura(armadura)

    suspend fun getEscudos(idPJ: Int): List<EscudoEntity> = charDatDAO.getEscudos(idPJ)

    suspend fun insertEscudo(escudo: EscudoEntity) = charDatDAO.insertEscudo(escudo)

    suspend fun deleteEscudo(escudo: EscudoEntity) = charDatDAO.deleteEscudo(escudo)

    suspend fun updateEscudo(escudo: EscudoEntity) = charDatDAO.updateEscudo(escudo)

    suspend fun getObjetos(idPJ: Int): List<ObjetoEntity> = charDatDAO.getObjetos(idPJ)

    suspend fun insertObjeto(objeto: ObjetoEntity) = charDatDAO.insertObjeto(objeto)

    suspend fun deleteObjeto(objeto: ObjetoEntity) = charDatDAO.deleteObjeto(objeto)

    suspend fun updateObjeto(objeto: ObjetoEntity) = charDatDAO.updateObjeto(objeto)

}