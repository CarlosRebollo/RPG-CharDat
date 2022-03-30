package ies.quevedo.chardat.data.entities

import androidx.room.Embedded
import androidx.room.Relation

data class PersonajeWithArmasEntity(
    @Embedded val personajeEntity: PersonajeEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "idPJ"
    )
    val equipment: List<ArmaEntity>?
)