package ies.quevedo.chardat.data.entities

import androidx.room.Embedded
import androidx.room.Relation

data class PersonajeWithAllEntity(
    @Embedded val personajeEntity: PersonajeEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "idPJ"
    )
    val equipment: List<EquipoEntity>?,
    @Relation(
        parentColumn = "id",
        entityColumn = "idPJ"
    )
    val inventory: List<ObjetoEntity>?
)