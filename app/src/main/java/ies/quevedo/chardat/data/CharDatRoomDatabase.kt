package ies.quevedo.chardat.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ies.quevedo.chardat.data.entities.ArmaEntity
import ies.quevedo.chardat.data.entities.PersonajeEntity
import ies.quevedo.chardat.data.utils.Converters

@Database(
    entities = [PersonajeEntity::class, ArmaEntity::class],
    version = 7,
    exportSchema = true
)
@TypeConverters(Converters::class)
abstract class CharDatRoomDatabase : RoomDatabase() {
    abstract fun charDatDAO(): CharDatDAO
}