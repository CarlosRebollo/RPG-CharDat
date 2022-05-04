package ies.quevedo.chardat.framework.fragmentListObjetos

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ies.quevedo.chardat.data.repository.ObjetoRepository
import javax.inject.Inject

@HiltViewModel
class RVObjetoViewModel @Inject constructor(
    private val objetoRepository: ObjetoRepository
) : ViewModel() {

}