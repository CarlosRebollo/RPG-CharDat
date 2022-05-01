package ies.quevedo.chardat.framework.armadura

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ies.quevedo.chardat.data.repository.ArmaduraRepository
import javax.inject.Inject

@HiltViewModel
class ArmaduraViewModel @Inject constructor(
    private val armaduraRepository: ArmaduraRepository
) : ViewModel() {

}