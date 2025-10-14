package com.example.quanlychitieu.di


import com.example.quanlychitieu.data.respository.KhoanChiRepositoryImpl
import com.example.quanlychitieu.domain.respository.KhoanChiRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindKhoanChiRepository(
        impl: KhoanChiRepositoryImpl
    ): KhoanChiRepository
}
