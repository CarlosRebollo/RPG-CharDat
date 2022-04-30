package ies.quevedo.chardat.data.dao

import androidx.room.*
import ies.quevedo.chardat.data.entities.ArmaduraEntity

@Dao
interface DAOArmadura {

    @Query("SELECT * FROM armadura WHERE idPJ = :idPJ")
    suspend fun getArmaduras(idPJ: Int): List<ArmaduraEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertArmadura(armadura: ArmaduraEntity)

    @Delete
    suspend fun deleteArmadura(armadura: ArmaduraEntity)

    @Update
    suspend fun updateArmadura(armadura: ArmaduraEntity)
}