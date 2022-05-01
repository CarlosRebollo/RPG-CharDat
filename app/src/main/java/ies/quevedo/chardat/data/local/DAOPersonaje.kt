package ies.quevedo.chardat.data.local

import androidx.room.*
import ies.quevedo.chardat.data.entities.PersonajeConTodo

@Dao
interface DAOPersonaje {

    @Transaction
    @Query("SELECT * FROM personaje WHERE id = :id")
    fun getPersonaje(id: Int): PersonajeConTodo

    @Transaction
    @Query("SELECT * FROM personaje")
    fun getPersonajes(): List<PersonajeConTodo>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertPersonaje(personaje: PersonajeConTodo)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(personajes: List<PersonajeConTodo>)

    @Update
    fun updatePersonaje(personaje: PersonajeConTodo)

    @Delete
    fun deletePersonaje(personaje: PersonajeConTodo)

    @Delete
    fun deleteAll(personajes: List<PersonajeConTodo>)
}