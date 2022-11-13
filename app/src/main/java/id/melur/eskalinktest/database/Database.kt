package id.melur.eskalinktest.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [Dummy::class], version = 3)
abstract class DummyDatabase: RoomDatabase() {
    abstract fun dummyDao(): DummyDao

    companion object {
        private var INSTANCE: DummyDatabase? = null

        fun getInstance(context: Context): DummyDatabase? {
            if (INSTANCE == null) {
                synchronized(DummyDatabase::class) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        DummyDatabase::class.java, "Dummy.db").build()
                }
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}