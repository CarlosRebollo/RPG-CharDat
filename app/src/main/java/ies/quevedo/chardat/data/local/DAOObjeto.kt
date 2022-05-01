package ies.quevedo.chardat.data.local

import androidx.room.*
import ies.quevedo.chardat.data.entities.ObjetoEntity

@Dao
interface DAOObjeto {

    @Query("SELECT * FROM objeto WHERE idPJ = :idPJ")
    suspend fun getObjetos(idPJ: Int): List<ObjetoEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertObjeto(objeto: ObjetoEntity)

    @Delete
    suspend fun deleteObjeto(objeto: ObjetoEntity)

    @Update
    suspend fun updateObjeto(objeto: ObjetoEntity)
}