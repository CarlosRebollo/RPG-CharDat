package ies.quevedo.chardat.ui.mainMenu

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ies.quevedo.chardat.data.usecases.UpdatePersonaje
import ies.quevedo.chardat.domain.Personaje
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainMenuViewModel @Inject constructor(
    private val updatePersonaje: UpdatePersonaje
) : ViewModel() {

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    fun updatePersonaje(personaje: Personaje) {
        viewModelScope.launch {
            try {
                updatePersonaje.invoke(personaje)
            } catch (e: Exception) {
                _error.value = e.message
            }
        }
    }
}