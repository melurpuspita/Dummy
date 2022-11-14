package id.melur.eskalinktest.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [Dummy::class], version = 3, exportSchema = false)
abstract class DummyDatabase: RoomDatabase() {

    abstract fun dummyDao(): DummyDao

}