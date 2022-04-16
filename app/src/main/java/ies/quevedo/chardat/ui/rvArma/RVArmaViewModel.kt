package ies.quevedo.chardat.ui.rvArma

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ies.quevedo.chardat.data.usecases.DeleteArma
import ies.quevedo.chardat.data.usecases.InsertArma
import ies.quevedo.chardat.data.usecases.ListArma
import ies.quevedo.chardat.data.usecases.UpdateArma
import ies.quevedo.chardat.domain.Arma
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RVArmaViewModel @Inject constructor(
    private val listArmas: ListArma,
    private val insertArma: InsertArma,
    private val updateArma: UpdateArma,
    private val deleteArma: DeleteArma
) : ViewModel() {

    private val _armas = MutableLiveData<List<Arma>>()
    val armas: LiveData<List<Arma>> get() = _armas

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    fun getArmas(idPJ: Int) {
        viewModelScope.launch {
            try {
                _armas.value = listArmas.invoke(idPJ)
            } catch (e: Exception) {
                _error.value = e.message
            }
        }
    }

    fun insertArma(arma: Arma) {
        viewModelScope.launch {
            try {
                insertArma.invoke(arma)
            } catch (e: Exception) {
                _error.value = e.message
            }
        }
    }

    fun updateArma(arma: Arma) {
        viewModelScope.launch {
            try {
                updateArma.invoke(arma)
            } catch (e: Exception) {
                _error.value = e.message
            }
        }
    }

    fun deleteArma(arma: Arma?) {
        viewModelScope.launch {
            try {
                arma?.let { deleteArma.invoke(it) }
            } catch (e: Exception) {
                _error.value = e.message
            }
        }
    }
}