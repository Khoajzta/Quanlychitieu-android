package com.example.quanlychitieu.di


import com.example.quanlychitieu.data.respository.AuthRepositoryImpl
import com.example.quanlychitieu.data.respository.KhoanChiRepositoryImpl
import com.example.quanlychitieu.data.respository.NguoiDungRepositoryImpl
import com.example.quanlychitieu.domain.respository.AuthRepository
import com.example.quanlychitieu.domain.respository.KhoanChiRepository
import com.example.quanlychitieu.domain.respository.NguoiDungRepository
import com.google.firebase.auth.FirebaseAuth
import dagger.Binds
import dagger.Module
import dagger.Provides
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

    @Binds
    @Singleton
    abstract fun bindNguoiDungRepository(
        impl: NguoiDungRepositoryImpl
    ): NguoiDungRepository

    @Binds
    @Singleton
    abstract fun bindAuthRepository(
        impl: AuthRepositoryImpl
    ): AuthRepository
}
