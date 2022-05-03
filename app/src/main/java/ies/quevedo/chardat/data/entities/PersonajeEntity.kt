package ies.quevedo.chardat.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity(tableName = "personaje")
data class PersonajeEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val clase: String,
    val level: Int,
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
    val creationDate: String
)