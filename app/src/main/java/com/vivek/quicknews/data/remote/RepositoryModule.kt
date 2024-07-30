package com.vivek.quicknews.data.remote

import com.vivek.quicknews.data.repository.NewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    /**
     * Provides RemoteDataRepository for access api service method
     */
    @Singleton
    @Provides
    fun provideNewsRepository(
        apiService: NewsApiService,
    ): NewsRepository {
        return NewsRepository(
            apiService
        )
    }

}