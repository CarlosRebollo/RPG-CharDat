package ies.quevedo.chardat.data.usecases

import ies.quevedo.chardat.data.Repository
import ies.quevedo.chardat.data.entities.toEscudoEntity
import ies.quevedo.chardat.domain.Escudo
import javax.inject.Inject

class DeleteEscudo @Inject constructor(private val repository: Repository) {

    suspend fun invoke(escudo: Escudo) = repository.deleteEscudo(escudo.toEscudoEntity())

}