package ies.quevedo.chardat.framework.viewModel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ies.quevedo.chardat.data.repository.ObjetoRepository
import javax.inject.Inject

@HiltViewModel
class ObjetoViewModel @Inject constructor(
    private val objetoRepository: ObjetoRepository
) : ViewModel() {

}