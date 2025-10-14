package com.example.quanlychitieu.data.remote

import com.example.quanlychitieu.domain.model.KhoanChiModel
import com.example.quanlychitieu.data.remote.dto.BaseResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface KhoanChiApiService{
    @GET("api/khoanchi/user/{userId}")
    suspend fun getKhoanChiByUser(@Path("userId") userId: Int): BaseResponse<List<KhoanChiModel>>


}