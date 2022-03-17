package ies.quevedo.chardat.domain

import java.io.Serializable

data class Equipo(
    val id: Int,
    val name: String,
    val value: Int,
    val weight: Double,
    val turn: Int,
    val attackHability: Int,
    val damage: Int,
    val parry: Int,
    val description: String,
    val idPJ: Int
) : Serializable
