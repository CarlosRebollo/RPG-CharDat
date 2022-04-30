package ies.quevedo.chardat.data.dao

import androidx.room.*
import ies.quevedo.chardat.data.entities.EscudoEntity

@Dao
interface DAOEscudo {

    @Query("SELECT * FROM escudo WHERE idPJ = :idPJ")
    suspend fun getEscudos(idPJ: Int): List<EscudoEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertEscudo(escudo: EscudoEntity)

    @Delete
    suspend fun deleteEscudo(escudo: EscudoEntity)

    @Update
    suspend fun updateEscudo(escudo: EscudoEntity)
}