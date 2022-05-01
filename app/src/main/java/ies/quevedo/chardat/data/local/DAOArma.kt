package ies.quevedo.chardat.data.local

import androidx.room.*
import ies.quevedo.chardat.data.entities.ArmaEntity

@Dao
interface DAOArma {

    @Query("SELECT * FROM arma WHERE idPJ = :idPJ")
    suspend fun getArmas(idPJ: Int): List<ArmaEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertArma(arma: ArmaEntity)

    @Delete
    suspend fun deleteArma(arma: ArmaEntity)

    @Update
    suspend fun updateArma(arma: ArmaEntity)
}