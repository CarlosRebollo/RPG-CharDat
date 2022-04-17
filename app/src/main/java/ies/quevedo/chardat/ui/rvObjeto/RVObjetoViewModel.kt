package ies.quevedo.chardat.ui.rvObjeto

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ies.quevedo.chardat.data.usecases.DeleteObjeto
import ies.quevedo.chardat.data.usecases.InsertObjeto
import ies.quevedo.chardat.data.usecases.ListObjeto
import ies.quevedo.chardat.data.usecases.UpdateObjeto
import ies.quevedo.chardat.domain.Objeto
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RVObjetoViewModel @Inject constructor(
    private val listObjeto: ListObjeto,
    private val insertObjeto: InsertObjeto,
    private val updateObjeto: UpdateObjeto,
    private val deleteObjeto: DeleteObjeto
) : ViewModel() {

    private val _objetos = MutableLiveData<List<Objeto>>()
    val objetos: LiveData<List<Objeto>> get() = _objetos

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    fun getObjetos(idPJ: Int) {
        viewModelScope.launch {
            try {
                _objetos.value = listObjeto.invoke(idPJ)
            } catch (e: Exception) {
                _error.value = e.message
            }
        }
    }

    fun insertObjeto(objeto: Objeto) {
        viewModelScope.launch {
            try {
                insertObjeto.invoke(objeto)
            } catch (e: Exception) {
                _error.value = e.message
            }
        }
    }

    fun updateObjeto(objeto: Objeto) {
        viewModelScope.launch {
            try {
                updateObjeto.invoke(objeto)
            } catch (e: Exception) {
                _error.value = e.message
            }
        }
    }

    fun deleteObjeto(objeto: Objeto) {
        viewModelScope.launch {
            try {
                deleteObjeto.invoke(objeto)
            } catch (e: Exception) {
                _error.value = e.message
            }
        }
    }
}