package ies.quevedo.chardat.domain

import ies.quevedo.chardat.domain.enums.Clase
import java.io.Serializable
import java.time.LocalDate

data class Personaje(
    val id: Int,
    val name: String,
    val clase: Clase,
    val level: Int,
    val description: String,
    val currentHP: Int,
    val totalHP: Int,
    val currentStamina: Int,
    val totalStamina: Int,
    val attackHability: Int,
    val dodge: Int,
    val parryHability: Int,
    val turn: Int,
    val agility: Int,
    val constitution: Int,
    val dexterity: Int,
    val strenght: Int,
    val intelligence: Int,
    val perception: Int,
    val power: Int,
    val will: Int,
    val creationDate: LocalDate,
    val equipment: List<Arma>?,
) : Serializable
