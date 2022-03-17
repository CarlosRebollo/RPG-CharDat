package ies.quevedo.chardat.data.usecases

import ies.quevedo.chardat.data.Repository
import ies.quevedo.chardat.data.entities.toObjetoEntity
import ies.quevedo.chardat.domain.Objeto
import javax.inject.Inject

class UpdateObjeto @Inject constructor(private val repository: Repository) {

    suspend fun updateObjeto(objeto: Objeto) = repository.updateObjeto(objeto.toObjetoEntity())

}