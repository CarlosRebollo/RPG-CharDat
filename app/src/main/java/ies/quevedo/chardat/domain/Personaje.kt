package ies.quevedo.chardat.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.time.LocalDate

@Parcelize
data class Personaje(
    val id: Int,
    var name: String,
    var level: Int,
    var clase: String,
    var description: String,
    var currentHP: Int,
    var totalHP: Int,
    var currentStamina: Int,
    var totalStamina: Int,
    var attackHability: Int,
    var dodge: Int,
    var parryHability: Int,
    var armor: Int,
    var turn: Int,
    var agility: Int,
    var constitution: Int,
    var dexterity: Int,
    var strength: Int,
    var intelligence: Int,
    var perception: Int,
    var power: Int,
    var will: Int,
    var RF: Int,
    var RM: Int,
    var RP: Int,
    var creationDate: LocalDate
) : Parcelable
