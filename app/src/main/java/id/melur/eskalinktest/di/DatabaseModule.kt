package id.melur.eskalinktest.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import id.melur.eskalinktest.database.DummyDao
import id.melur.eskalinktest.database.DummyDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): DummyDatabase =
        Room.databaseBuilder(context, DummyDatabase::class.java, "Dummy.db").build()

    @Provides
    fun provideMovieDao(database: DummyDatabase): DummyDao =
        database.dummyDao()

}