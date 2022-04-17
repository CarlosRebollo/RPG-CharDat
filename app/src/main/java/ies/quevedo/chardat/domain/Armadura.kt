package ies.quevedo.chardat.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Armadura(
    val id: Int = 0,
    val name: String,
    val value: Int,
    val weight: Double,
    val quality: Int,
    val armor: Int,
    val fil: Int,
    val con: Int,
    val pen: Int,
    val cal: Int,
    val ele: Int,
    val fri: Int,
    val ene: Int,
    val description: String,
    val idPJ: Int
) : Parcelable