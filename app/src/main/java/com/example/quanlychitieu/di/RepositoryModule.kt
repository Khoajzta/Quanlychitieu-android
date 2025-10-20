package com.example.quanlychitieu.di


import com.example.quanlychitieu.data.respository.AuthRepositoryImpl
import com.example.quanlychitieu.data.respository.ChiTieuRepositoryImpl
import com.example.quanlychitieu.data.respository.KhoanChiRepositoryImpl
import com.example.quanlychitieu.data.respository.NguoiDungRepositoryImpl
import com.example.quanlychitieu.data.respository.TaiKhoanRepositoryImpl
import com.example.quanlychitieu.data.respository.ThuNhapRepositoryImpl
import com.example.quanlychitieu.domain.respository.AuthRepository
import com.example.quanlychitieu.domain.respository.ChiTieuRespository
import com.example.quanlychitieu.domain.respository.KhoanChiRepository
import com.example.quanlychitieu.domain.respository.NguoiDungRepository
import com.example.quanlychitieu.domain.respository.TaiKhoanRepository
import com.example.quanlychitieu.domain.respository.ThuNhapRepository
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

    @Binds
    @Singleton
    abstract fun bindTaiKhoanRepository(
        impl: TaiKhoanRepositoryImpl
    ): TaiKhoanRepository

    @Binds
    @Singleton
    abstract fun bindChiTieuRepository(
        impl: ChiTieuRepositoryImpl
    ): ChiTieuRespository

    @Binds
    @Singleton
    abstract fun bindThuNhapRepository(
        impl: ThuNhapRepositoryImpl
    ): ThuNhapRepository
}
