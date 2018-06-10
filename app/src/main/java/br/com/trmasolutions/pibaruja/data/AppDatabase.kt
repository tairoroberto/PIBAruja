package br.com.trmasolutions.pibaruja.data

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import android.content.Context
import br.com.trmasolutions.pibaruja.data.dao.EventosDao
import br.com.trmasolutions.pibaruja.model.Event


@Database(entities = [(Event::class)], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun eventsDAO(): EventosDao

    companion object {

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context?): AppDatabase =
                INSTANCE ?: synchronized(this) {
                    INSTANCE ?: buildDatabase(context as Context).also { INSTANCE = it }
                }


        /*private val MIGRATION_1_5: Migration = object : Migration(1, 5) {
            override fun migrate(database: SupportSQLiteDatabase) {
                // Since we didn't alter the table, there's nothing else to do here.
            }
        }*/

        private fun buildDatabase(context: Context) =
                Room.databaseBuilder(context.applicationContext,
                        AppDatabase::class.java, "pibaruja.db")
                        //.addMigrations(MIGRATION_1_5)
                        .fallbackToDestructiveMigration()
                        .build()
    }
}
