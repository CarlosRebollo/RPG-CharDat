package ies.quevedo.chardat.data

import androidx.room.*
import ies.quevedo.chardat.data.entities.EquipoEntity
import ies.quevedo.chardat.data.entities.ObjetoEntity
import ies.quevedo.chardat.data.entities.PersonajeEntity

@Dao
interface CharDatDAO {

    @Query("SELECT * FROM Personaje")
    suspend fun getPersonajes(): List<PersonajeEntity>

    @Query("SELECT * FROM Equipo WHERE idPJ = :idPJ")
    suspend fun getEquipamiento(idPJ: Int): List<EquipoEntity>

    @Query("SELECT * FROM Objeto WHERE idPJ = :idPJ")
    suspend fun getObjetos(idPJ: Int): List<ObjetoEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertPersonaje(personaje: PersonajeEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertEquipo(equipo: EquipoEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertObjeto(objeto: ObjetoEntity)

    @Delete
    suspend fun deletePersonaje(personaje: PersonajeEntity) {

    }

    @Delete
    suspend fun deleteEquipo(equipo: EquipoEntity)

    @Delete
    suspend fun deleteObjeto(objeto: ObjetoEntity)

    @Update
    suspend fun updatePersonaje(personaje: PersonajeEntity)

    @Update
    suspend fun updateEquipo(equipo: EquipoEntity)

    @Update
    suspend fun updateObjeto(objeto: ObjetoEntity)
}