package ies.quevedo.chardat.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "objeto")
data class ObjetoEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val value: Int,
    val weight: Double,
    val amount: Int,
    val description: String,
    val idPJ: Int
)
