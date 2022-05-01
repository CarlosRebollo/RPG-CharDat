package ies.quevedo.chardat.framework.arma

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ies.quevedo.chardat.data.repository.ArmaRepository
import javax.inject.Inject

@HiltViewModel
class ArmaViewModel @Inject constructor(
    private val armaRepository: ArmaRepository
) : ViewModel() {

}