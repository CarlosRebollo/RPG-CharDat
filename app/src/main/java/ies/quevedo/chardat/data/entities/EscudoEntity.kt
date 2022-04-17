package ies.quevedo.chardat.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "escudo")
data class EscudoEntity(
    @PrimaryKey(autoGenerate = true)
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
)
