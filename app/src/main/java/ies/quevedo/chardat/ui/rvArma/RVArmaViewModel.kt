package ies.quevedo.chardat.ui.rvArma

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ies.quevedo.chardat.data.usecases.ListArma
import ies.quevedo.chardat.data.usecases.UpdateArma
import ies.quevedo.chardat.domain.Arma
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RVArmaViewModel @Inject constructor(
    private val listArmas: ListArma,
    private val updateArma: UpdateArma
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

    fun updateArma(arma: Arma) {
        viewModelScope.launch {
            try {
                updateArma.invoke(arma)
            } catch (e: Exception) {
                _error.value = e.message
            }
        }
    }
}