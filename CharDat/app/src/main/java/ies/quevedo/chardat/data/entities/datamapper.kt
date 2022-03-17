package ies.quevedo.chardat.data.entities

import ies.quevedo.chardat.domain.Equipo
import ies.quevedo.chardat.domain.Objeto
import ies.quevedo.chardat.domain.Personaje

fun PersonajeWithAllEntity.toPersonaje(): Personaje {
    return Personaje(
        this.personajeEntity.id,
        this.personajeEntity.name,
        this.personajeEntity.clase,
        this.personajeEntity.level,
        this.personajeEntity.description,
        this.personajeEntity.currentHP,
        this.personajeEntity.totalHP,
        this.personajeEntity.currentStamina,
        this.personajeEntity.totalStamina,
        this.personajeEntity.attackHability,
        this.personajeEntity.dodge,
        this.personajeEntity.parryHability,
        this.personajeEntity.turn,
        this.personajeEntity.agility,
        this.personajeEntity.constitution,
        this.personajeEntity.dexterity,
        this.personajeEntity.strenght,
        this.personajeEntity.intelligence,
        this.personajeEntity.perception,
        this.personajeEntity.power,
        this.personajeEntity.will,
        this.personajeEntity.creationDate,
        this.equipment?.map { it.toEquipo() },
        this.inventory?.map { it.toObjeto() }
    )
}

fun PersonajeEntity.toPersonaje(): Personaje {
    return Personaje(
        this.id,
        this.name,
        this.clase,
        this.level,
        this.description,
        this.currentHP,
        this.totalHP,
        this.currentStamina,
        this.totalStamina,
        this.attackHability,
        this.dodge,
        this.parryHability,
        this.turn,
        this.agility,
        this.constitution,
        this.dexterity,
        this.strenght,
        this.intelligence,
        this.perception,
        this.power,
        this.will,
        this.creationDate,
        null,
        null
    )
}

fun EquipoEntity.toEquipo(): Equipo {
    return Equipo(
        this.id,
        this.name,
        this.value,
        this.weight,
        this.turn,
        this.attackHability,
        this.damage,
        this.parry,
        this.description,
        this.idPJ
    )
}

fun ObjetoEntity.toObjeto(): Objeto {
    return Objeto(
        this.id,
        this.name,
        this.value,
        this.weight,
        this.amount,
        this.description,
        this.idPJ
    )
}

fun Personaje.toPersonajeWithAllEntity(): PersonajeWithAllEntity {
    return PersonajeWithAllEntity(
        this.toPersonajeEntity(),
        this.equipment?.map { it.toEquipoEntity() },
        this.inventory?.map { it.toObjetoEntity() }
    )
}

fun Personaje.toPersonajeEntity(): PersonajeEntity {
    return PersonajeEntity(
        this.id,
        this.name,
        this.clase,
        this.level,
        this.description,
        this.currentHP,
        this.totalHP,
        this.currentStamina,
        this.totalStamina,
        this.attackHability,
        this.dodge,
        this.parryHability,
        this.turn,
        this.agility,
        this.constitution,
        this.dexterity,
        this.strenght,
        this.intelligence,
        this.perception,
        this.power,
        this.will,
        this.creationDate
    )
}

fun Equipo.toEquipoEntity(): EquipoEntity {
    return EquipoEntity(
        this.id,
        this.name,
        this.value,
        this.weight,
        this.turn,
        this.attackHability,
        this.damage,
        this.parry,
        this.description,
        this.idPJ
    )
}

fun Objeto.toObjetoEntity(): ObjetoEntity {
    return ObjetoEntity(
        this.id,
        this.name,
        this.value,
        this.weight,
        this.amount,
        this.description,
        this.idPJ
    )
}
