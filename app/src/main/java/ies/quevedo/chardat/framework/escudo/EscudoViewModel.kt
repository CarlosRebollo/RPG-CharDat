package ies.quevedo.chardat.framework.escudo

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ies.quevedo.chardat.data.repository.EscudoRepository
import javax.inject.Inject

@HiltViewModel
class EscudoViewModel @Inject constructor(
    private val escudoRepository: EscudoRepository
) : ViewModel() {

}