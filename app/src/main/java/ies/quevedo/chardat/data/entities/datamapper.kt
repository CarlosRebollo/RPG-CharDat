package ies.quevedo.chardat.data.entities

import ies.quevedo.chardat.domain.Arma
import ies.quevedo.chardat.domain.Personaje

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
        this.creationDate
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
        this.strength,
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
