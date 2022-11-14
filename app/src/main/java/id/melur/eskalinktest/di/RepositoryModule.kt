package id.melur.eskalinktest.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import id.melur.eskalinktest.database.DummyDao
import id.melur.eskalinktest.helper.DataRepo
import id.melur.eskalinktest.service.ApiService
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideMovieRepository(apiService: ApiService, dummyDao: DummyDao) =
        DataRepo(apiService, dummyDao)
}