package ies.quevedo.chardat.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ies.quevedo.chardat.data.entities.*
import ies.quevedo.chardat.data.utils.Converters

@Database(
    entities = [PersonajeEntity::class,
        ArmaEntity::class,
        ArmaduraEntity::class,
        EscudoEntity::class,
        ObjetoEntity::class],
    version = 10,
    exportSchema = true
)
@TypeConverters(Converters::class)
abstract class CharDatRoomDatabase : RoomDatabase() {
    abstract fun charDatDAO(): CharDatDAO
}