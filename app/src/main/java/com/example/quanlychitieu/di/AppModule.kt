package com.example.quanlychitieu.di

import android.content.Context
import android.util.Log
import com.example.quanlychitieu.Utils.BASE_URL
import com.example.quanlychitieu.data.local.DataStoreManager
import com.example.quanlychitieu.data.remote.ChiTieuAPIService
import com.example.quanlychitieu.data.remote.KhoanChiApiService
import com.example.quanlychitieu.data.remote.NguoiDungAPIService
import com.example.quanlychitieu.data.remote.TaiKhoanAPIService
import com.example.quanlychitieu.data.remote.ThuNhapAPIService
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val logger = HttpLoggingInterceptor()
        logger.setLevel(HttpLoggingInterceptor.Level.BODY)
        return OkHttpClient.Builder()
            .addInterceptor(logger)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(ok: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(ok)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideKhoanChiApiService(retrofit: Retrofit): KhoanChiApiService {
        return retrofit.create(KhoanChiApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideNguoiDungApiService(retrofit: Retrofit): NguoiDungAPIService {
        return retrofit.create(NguoiDungAPIService::class.java)
    }

    @Provides
    @Singleton
    fun proviceTaiKhoanApiService(retrofit: Retrofit): TaiKhoanAPIService {
        return retrofit.create(TaiKhoanAPIService::class.java)
    }

    @Provides
    @Singleton
    fun proviceChiTieuApiService(retrofit: Retrofit): ChiTieuAPIService {
        return retrofit.create(ChiTieuAPIService::class.java)
    }

    @Provides
    @Singleton
    fun proviceThuNhapApiService(retrofit: Retrofit): ThuNhapAPIService {
        return retrofit.create(ThuNhapAPIService::class.java)
    }

    @Provides
    @Singleton
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    @Provides
    @Singleton
    fun provideDataStoreManager(
        @ApplicationContext context: Context
    ): DataStoreManager {
        return DataStoreManager(context)
    }
}

