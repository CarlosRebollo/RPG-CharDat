package ies.quevedo.chardat.domain

import java.io.Serializable
import java.time.LocalDate

data class Personaje(
    val id: Int,
    val name: String,
    val level: Int,
    val clase: String,
    val description: String,
    val currentHP: Int,
    val totalHP: Int,
    val currentStamina: Int,
    val totalStamina: Int,
    val attackHability: Int,
    val dodge: Int,
    val parryHability: Int,
    val armor: Int,
    val turn: Int,
    val agility: Int,
    val constitution: Int,
    val dexterity: Int,
    val strenght: Int,
    val intelligence: Int,
    val perception: Int,
    val power: Int,
    val will: Int,
    val RF: Int,
    val RM: Int,
    val RP: Int,
    val creationDate: LocalDate,
    val weapons: List<Arma>?,
) : Serializable
