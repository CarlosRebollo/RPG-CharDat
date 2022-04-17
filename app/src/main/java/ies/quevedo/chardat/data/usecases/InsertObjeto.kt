package ies.quevedo.chardat.data.usecases

import ies.quevedo.chardat.data.Repository
import ies.quevedo.chardat.data.entities.toObjetoEntity
import ies.quevedo.chardat.domain.Objeto
import javax.inject.Inject

class InsertObjeto @Inject constructor(private val repository: Repository) {

    suspend fun invoke(objeto: Objeto) = repository.insertObjeto(objeto.toObjetoEntity())
}