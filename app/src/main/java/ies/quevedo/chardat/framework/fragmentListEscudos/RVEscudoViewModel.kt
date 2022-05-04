package ies.quevedo.chardat.framework.fragmentListEscudos

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ies.quevedo.chardat.data.repository.EscudoRepository
import javax.inject.Inject

@HiltViewModel
class RVEscudoViewModel @Inject constructor(
    private val escudoRepository: EscudoRepository
) : ViewModel() {

}