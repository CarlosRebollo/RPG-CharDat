package ies.quevedo.chardat.data.local

import androidx.room.*
import ies.quevedo.chardat.data.entities.ArmaduraEntity

@Dao
interface DAOArmadura {

    @Query("SELECT * FROM armadura WHERE idPJ = :idPJ")
    suspend fun getArmaduras(idPJ: Int): List<ArmaduraEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertArmadura(armadura: ArmaduraEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(armaduras: List<ArmaduraEntity>)

    @Update
    suspend fun updateArmadura(armadura: ArmaduraEntity)

    @Delete
    suspend fun deleteArmadura(armadura: ArmaduraEntity)

    @Delete
    suspend fun deleteAll(armaduras: List<ArmaduraEntity>)
}