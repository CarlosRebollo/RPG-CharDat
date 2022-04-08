package ies.quevedo.chardat.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Arma(
    val id: Int,
    val name: String,
    val value: Int,
    val weight: Double,
    val quality: Int,
    val turn: Int,
    val attackHability: Int,
    val damage: Int,
    val parry: Int,
    val description: String,
    val idPJ: Int
) : Parcelable
