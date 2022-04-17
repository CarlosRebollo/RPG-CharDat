package ies.quevedo.chardat.ui.rvArmadura

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ies.quevedo.chardat.data.usecases.DeleteArmadura
import ies.quevedo.chardat.data.usecases.InsertArmadura
import ies.quevedo.chardat.data.usecases.ListArmadura
import ies.quevedo.chardat.data.usecases.UpdateArmadura
import ies.quevedo.chardat.domain.Armadura
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RVArmaduraViewModel @Inject constructor(
    private val listArmadura: ListArmadura,
    private val insertArmadura: InsertArmadura,
    private val updateArmadura: UpdateArmadura,
    private val deleteArmadura: DeleteArmadura
) : ViewModel() {

    private val _armaduras = MutableLiveData<List<Armadura>>()
    val armaduras: LiveData<List<Armadura>> get() = _armaduras

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    fun getArmaduras(idPJ: Int) {
        viewModelScope.launch {
            try {
                _armaduras.value = listArmadura.invoke(idPJ)
            } catch (e: Exception) {
                _error.value = e.message
            }
        }
    }

    fun insertArmadura(armadura: Armadura) {
        viewModelScope.launch {
            try {
                insertArmadura.invoke(armadura)
            } catch (e: Exception) {
                _error.value = e.message
            }
        }
    }

    fun updateArmadura(armadura: Armadura) {
        viewModelScope.launch {
            try {
                updateArmadura.invoke(armadura)
            } catch (e: Exception) {
                _error.value = e.message
            }
        }
    }

    fun deleteArmadura(armadura: Armadura) {
        viewModelScope.launch {
            try {
                deleteArmadura.invoke(armadura)
            } catch (e: Exception) {
                _error.value = e.message
            }
        }
    }
}