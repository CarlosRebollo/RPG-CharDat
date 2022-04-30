package ies.quevedo.chardat.data.dao

import androidx.room.*
import ies.quevedo.chardat.data.entities.*

@Dao
interface DAOPersonaje {

    @Query("SELECT * FROM personaje")
    suspend fun getPersonajes(): List<PersonajeEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertPersonaje(personaje: PersonajeEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertPersonajeConTodo(personaje: PersonajeConTodo)

    @Delete
    suspend fun deletePersonaje(
        personaje: PersonajeEntity,
        armaduras: List<ArmaduraEntity>?,
        armas: List<ArmaEntity>?,
        escudos: List<EscudoEntity>?,
        objetos: List<ObjetoEntity>?
    )

    @Update
    suspend fun updatePersonaje(personaje: PersonajeConTodo)
}