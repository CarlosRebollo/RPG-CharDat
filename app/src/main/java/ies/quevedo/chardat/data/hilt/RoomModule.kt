package ies.quevedo.chardat.data.hilt

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ies.quevedo.chardat.data.CharDatRoomDatabase
import ies.quevedo.chardat.data.utils.Constants
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Provides
    @Singleton
    fun providesDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(context, CharDatRoomDatabase::class.java, Constants.DB_NAME)
        .fallbackToDestructiveMigration()
        .createFromAsset(Constants.DB_LOCATION)
        .build()

    @Provides
    fun providesCharDatDAO(articlesDatabase: CharDatRoomDatabase) =
        articlesDatabase.charDatDAO()
}