package ies.quevedo.chardat.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Objeto(
    val id: Int = 0,
    val name: String,
    val value: Int,
    val weight: Double,
    val amount: Int,
    val description: String,
    val idPJ: Int
) : Parcelable