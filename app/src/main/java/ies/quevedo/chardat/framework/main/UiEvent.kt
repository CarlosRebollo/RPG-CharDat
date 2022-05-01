package ies.quevedo.chardat.framework.main

sealed class UiEvent {
    object PopBackStack : UiEvent()
    data class NavigateTo(val screen: String) : UiEvent()
    data class ShowSnackbar(
        val message: String,
        val action: String?
    ) : UiEvent()
}
