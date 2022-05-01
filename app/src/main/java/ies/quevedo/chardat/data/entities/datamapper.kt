package ies.quevedo.chardat.data.entities

import ies.quevedo.chardat.domain.model.*
import java.util.*

fun PersonajeConTodo.toPersonaje(): Personaje {
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
        this.armas?.map { it.toArma() },
        this.armaduras?.map { it.toArmadura() },
        this.escudos?.map { it.toEscudo() },
        this.objetos?.map { it.toObjeto() }
    )
}

fun Personaje.toPersonajeConTodo(): PersonajeConTodo {
    return PersonajeConTodo(
        this.toPersonajeEntity(),
        this.armas?.map { it.toArmaEntity(this.id) },
        this.armaduras?.map { it.toArmaduraEntity(this.id) },
        this.escudos?.map { it.toEscudoEntity(this.id) },
        this.objetos?.map { it.toObjetoEntity(this.id) }
    )
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
        Collections.emptyList(),
        Collections.emptyList(),
        Collections.emptyList(),
        Collections.emptyList()
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

fun Arma.toArmaEntity(idPJ: Int): ArmaEntity {
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
        idPJ
    )
}

fun Armadura.toArmaduraEntity(idPJ: Int): ArmaduraEntity {
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
        idPJ
    )
}

fun Escudo.toEscudoEntity(idPJ: Int): EscudoEntity {
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
        idPJ
    )
}

fun Objeto.toObjetoEntity(idPJ: Int): ObjetoEntity {
    return ObjetoEntity(
        this.id,
        this.name,
        this.value,
        this.weight,
        this.amount,
        this.description,
        idPJ
    )
}