package ies.quevedo.chardat.data.repository

import ies.quevedo.chardat.data.local.DAOEscudo
import ies.quevedo.chardat.data.entities.toEscudo
import ies.quevedo.chardat.data.entities.toEscudoEntity
import ies.quevedo.chardat.domain.model.Escudo
import javax.inject.Inject

class EscudoRepository @Inject constructor(private val daoEscudo: DAOEscudo) {

    suspend fun getEscudos(idPJ: Int): List<Escudo> =
        daoEscudo.getEscudos(idPJ).map { it.toEscudo() }

    suspend fun insertEscudo(escudo: Escudo) =
        daoEscudo.insertEscudo(escudo.toEscudoEntity(escudo.idPJ))

    suspend fun deleteEscudo(escudo: Escudo) =
        daoEscudo.deleteEscudo(escudo.toEscudoEntity(escudo.idPJ))

    suspend fun updateEscudo(escudo: Escudo) =
        daoEscudo.updateEscudo(escudo.toEscudoEntity(escudo.idPJ))
}