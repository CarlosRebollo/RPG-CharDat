package ies.quevedo.chardat.data.entities

import ies.quevedo.chardat.domain.*

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

fun ArmaduraEntity.toArmadura(): Armadura {
    return Armadura(
        this.id,
        this.name,
        this.value,
        this.weight,
        this.quality,
        this.armor,
        this.fil,
        this.con,
        this.pen,
        this.cal,
        this.ele,
        this.fri,
        this.ene,
        this.description,
        this.idPJ
    )
}

fun EscudoEntity.toEscudo(): Escudo {
    return Escudo(
        this.id,
        this.name,
        this.value,
        this.weight,
        this.quality,
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

fun Armadura.toArmaduraEntity(): ArmaduraEntity {
    return ArmaduraEntity(
        this.id,
        this.name,
        this.value,
        this.weight,
        this.quality,
        this.armor,
        this.fil,
        this.con,
        this.pen,
        this.cal,
        this.ele,
        this.fri,
        this.ene,
        this.description,
        this.idPJ
    )
}

fun Escudo.toEscudoEntity(): EscudoEntity {
    return EscudoEntity(
        this.id,
        this.name,
        this.value,
        this.weight,
        this.quality,
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