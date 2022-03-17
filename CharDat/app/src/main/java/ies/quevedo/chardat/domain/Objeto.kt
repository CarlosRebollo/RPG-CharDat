package ies.quevedo.chardat.domain

import java.io.Serializable

data class Objeto(
    val id: Int,
    val name: String,
    val value: Int,
    val weight: Double,
    val amount: Int,
    val description: String,
    val idPJ: Int
) : Serializable
