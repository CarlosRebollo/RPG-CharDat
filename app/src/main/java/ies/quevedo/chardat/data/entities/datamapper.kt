package ies.quevedo.chardat.data.entities

import ies.quevedo.chardat.domain.Arma
import ies.quevedo.chardat.domain.Personaje

fun PersonajeWithInventoryEntity.toPersonaje(): Personaje {
    return Personaje(
        this.personajeEntity.id,
        this.personajeEntity.name,
        this.personajeEntity.level,
        this.personajeEntity.clase,
        this.personajeEntity.description,
        this.personajeEntity.currentHP,
        this.personajeEntity.totalHP,
        this.personajeEntity.currentStamina,
        this.personajeEntity.totalStamina,
        this.personajeEntity.attackHability,
        this.personajeEntity.dodge,
        this.personajeEntity.parryHability,
        this.personajeEntity.armor,
        this.personajeEntity.turn,
        this.personajeEntity.agility,
        this.personajeEntity.constitution,
        this.personajeEntity.dexterity,
        this.personajeEntity.strenght,
        this.personajeEntity.intelligence,
        this.personajeEntity.perception,
        this.personajeEntity.power,
        this.personajeEntity.will,
        this.personajeEntity.RF,
        this.personajeEntity.RM,
        this.personajeEntity.RP,
        this.personajeEntity.creationDate,
        this.weapons?.map { it.toArma() })
}

fun PersonajeEntity.toPersonaje(): Personaje {
    return Personaje(
        this.id,
        this.name,
        this.level,
        this.clase,
        this.description,
        this.currentHP,
        this.totalHP,
        this.currentStamina,
        this.totalStamina,
        this.attackHability,
        this.dodge,
        this.parryHability,
        this.armor,
        this.turn,
        this.agility,
        this.constitution,
        this.dexterity,
        this.strenght,
        this.intelligence,
        this.perception,
        this.power,
        this.will,
        this.RF,
        this.RM,
        this.RP,
        this.creationDate,
        null
    )
}

fun ArmaEntity.toArma(): Arma {
    return Arma(
        this.id,
        this.name,
        this.value,
        this.weight,
        this.quality,
        this.turn,
        this.attackHability,
        this.damage,
        this.parry,
        this.description,
        this.idPJ
    )
}

fun Personaje.toPersonajeWithInventoryEntity(): PersonajeWithInventoryEntity {
    return PersonajeWithInventoryEntity(
        this.toPersonajeEntity(),
        this.weapons?.map { it.toArmaEntity() }
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
        this.armor,
        this.turn,
        this.agility,
        this.constitution,
        this.dexterity,
        this.strenght,
        this.intelligence,
        this.perception,
        this.power,
        this.will,
        this.RF,
        this.RM,
        this.RP,
        this.creationDate
    )
}

fun Arma.toArmaEntity(): ArmaEntity {
    return ArmaEntity(
        this.id,
        this.name,
        this.value,
        this.weight,
        this.quality,
        this.turn,
        this.attackHability,
        this.damage,
        this.parry,
        this.description,
        this.idPJ
    )
}
