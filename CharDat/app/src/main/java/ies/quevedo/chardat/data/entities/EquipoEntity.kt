package ies.quevedo.chardat.data.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "Equipo",
    foreignKeys = [
        ForeignKey(
            entity = PersonajeEntity::class,
            parentColumns = ["id"],
            childColumns = ["idPJ"]
        )
    ]
)
data class EquipoEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val value: Int,
    val weight: Double,
    val turn: Int,
    val attackHability: Int,
    val damage: Int,
    val parry: Int,
    val description: String,
    val idPJ: Int
)