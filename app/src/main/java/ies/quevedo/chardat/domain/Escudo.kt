package ies.quevedo.chardat.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Escudo(
    val id: Int = 0,
    val name: String,
    val value: Int,
    val weight: Double,
    val quality: Int,
    val attackHability: Int,
    val damage: Int,
    val parry: Int,
    val description: String,
    val idPJ: Int
) : Parcelable