package ies.quevedo.chardat.data.usecases

import ies.quevedo.chardat.data.Repository
import ies.quevedo.chardat.data.entities.toObjeto
import javax.inject.Inject

class ListObjeto @Inject constructor(private val repository: Repository) {

    suspend fun invoke(idPJ: Int) = repository.getObjetos(idPJ).map { it.toObjeto() }

}