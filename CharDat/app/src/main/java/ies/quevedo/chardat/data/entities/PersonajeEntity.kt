package ies.quevedo.chardat.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import ies.quevedo.chardat.domain.enums.Clase
import java.time.LocalDate

@Entity(tableName = "Personaje")
data class PersonajeEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
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
    val creationDate: LocalDate
)