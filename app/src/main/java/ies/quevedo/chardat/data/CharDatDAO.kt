package ies.quevedo.chardat.data

import androidx.room.*
import ies.quevedo.chardat.data.entities.ArmaEntity
import ies.quevedo.chardat.data.entities.PersonajeEntity

@Dao
interface CharDatDAO {

    @Query("SELECT * FROM personaje")
    suspend fun getPersonajes(): List<PersonajeEntity>

    @Query("SELECT * FROM arma WHERE idPJ = :idPJ")
    suspend fun getEquipamiento(idPJ: Int): List<ArmaEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertPersonaje(personaje: PersonajeEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertEquipo(arma: ArmaEntity)

    @Delete
    suspend fun deletePersonaje(personaje: PersonajeEntity)

    @Delete
    suspend fun deleteEquipo(arma: ArmaEntity)

    @Update
    suspend fun updatePersonaje(personaje: PersonajeEntity)

    @Update
    suspend fun updateEquipo(arma: ArmaEntity)
}