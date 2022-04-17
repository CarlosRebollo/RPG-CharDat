package ies.quevedo.chardat.data

import androidx.room.*
import ies.quevedo.chardat.data.entities.*

@Dao
interface CharDatDAO {

    @Query("SELECT * FROM personaje")
    suspend fun getPersonajes(): List<PersonajeEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertPersonaje(personaje: PersonajeEntity)

    @Delete
    suspend fun deletePersonaje(personaje: PersonajeEntity)

    @Update
    suspend fun updatePersonaje(personaje: PersonajeEntity)

    @Query("SELECT * FROM arma WHERE idPJ = :idPJ")
    suspend fun getArmas(idPJ: Int): List<ArmaEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertArma(arma: ArmaEntity)

    @Delete
    suspend fun deleteArma(arma: ArmaEntity)

    @Update
    suspend fun updateArma(arma: ArmaEntity)

    @Query("SELECT * FROM armadura WHERE idPJ = :idPJ")
    suspend fun getArmaduras(idPJ: Int): List<ArmaduraEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertArmadura(armadura: ArmaduraEntity)

    @Delete
    suspend fun deleteArmadura(armadura: ArmaduraEntity)

    @Update
    suspend fun updateArmadura(armadura: ArmaduraEntity)

    @Query("SELECT * FROM escudo WHERE idPJ = :idPJ")
    suspend fun getEscudos(idPJ: Int): List<EscudoEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertEscudo(escudo: EscudoEntity)

    @Delete
    suspend fun deleteEscudo(escudo: EscudoEntity)

    @Update
    suspend fun updateEscudo(escudo: EscudoEntity)

    @Query("SELECT * FROM objeto WHERE idPJ = :idPJ")
    suspend fun getObjetos(idPJ: Int): List<ObjetoEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertObjeto(objeto: ObjetoEntity)

    @Delete
    suspend fun deleteObjeto(objeto: ObjetoEntity)

    @Update
    suspend fun updateObjeto(objeto: ObjetoEntity)
}