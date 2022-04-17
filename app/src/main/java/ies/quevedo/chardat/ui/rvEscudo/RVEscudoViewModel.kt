package ies.quevedo.chardat.ui.rvEscudo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ies.quevedo.chardat.data.usecases.DeleteEscudo
import ies.quevedo.chardat.data.usecases.InsertEscudo
import ies.quevedo.chardat.data.usecases.ListEscudo
import ies.quevedo.chardat.data.usecases.UpdateEscudo
import ies.quevedo.chardat.domain.Escudo
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RVEscudoViewModel @Inject constructor(
    private val listEscudo: ListEscudo,
    private val insertEscudo: InsertEscudo,
    private val updateEscudo: UpdateEscudo,
    private val deleteEscudo: DeleteEscudo
) : ViewModel() {

    private val _escudos = MutableLiveData<List<Escudo>>()
    val escudos: LiveData<List<Escudo>> get() = _escudos

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    fun getEscudos(idPJ: Int) {
        viewModelScope.launch {
            try {
                _escudos.value = listEscudo.invoke(idPJ)
            } catch (e: Exception) {
                _error.value = e.message
            }
        }
    }

    fun insertEscudo(escudo: Escudo) {
        viewModelScope.launch {
            try {
                insertEscudo.invoke(escudo)
            } catch (e: Exception) {
                _error.value = e.message
            }
        }
    }

    fun updateEscudo(escudo: Escudo) {
        viewModelScope.launch {
            try {
                updateEscudo.invoke(escudo)
            } catch (e: Exception) {
                _error.value = e.message
            }
        }
    }

    fun deleteEscudo(escudo: Escudo) {
        viewModelScope.launch {
            try {
                deleteEscudo.invoke(escudo)
            } catch (e: Exception) {
                _error.value = e.message
            }
        }
    }
}