package com.example.quanlychitieu.data.remote

import com.example.quanlychitieu.data.remote.dto.BaseResponse
import com.example.quanlychitieu.domain.model.ChiTieuModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ChiTieuAPIService {
    @POST("api/chitieu")
    suspend fun postChiTieu(@Body chitieu: ChiTieuModel): Response<BaseResponse<ChiTieuModel>>

    @GET("api/chitieu/khoanchi/{id_khoanchi}/user/{userId}")
    suspend fun getChiTieuTheoKhoanChiCuaNguoiDung(
        @Path("id_khoanchi") id_khoanchi: Int,
        @Path("userId") userId: Int,
    ): BaseResponse<List<ChiTieuModel>>

    @GET("api/chitieu/user/{userId}/by-month")
    suspend fun getChiTieuTheoThangVaNam(
        @Path("userId") userId: Int,
        @Query("thang") thang: Int,
        @Query("nam") nam: Int
    ): BaseResponse<List<ChiTieuModel>>
}