package id.melur.eskalinktest.database

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [Dummy::class], version = 3, exportSchema = false)
abstract class DummyDatabase: RoomDatabase() {

    abstract fun dummyDao(): DummyDao

}