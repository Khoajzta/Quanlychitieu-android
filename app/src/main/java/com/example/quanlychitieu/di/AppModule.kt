package com.example.quanlychitieu.di

import android.content.Context
import com.example.quanlychitieu.Repository.YourRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideYourRepository(
        @ApplicationContext context: Context
    ): TestRepository {
        return YourRepositoryImpl(context)
    }
}
