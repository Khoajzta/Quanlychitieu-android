package com.example.quanlychitieu.data.remote

import com.example.quanlychitieu.data.remote.dto.BaseResponse
import com.example.quanlychitieu.domain.model.ChiTieuModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ChiTieuAPIService {
    @POST("api/chitieu")
    suspend fun postChiTieu(@Body chitieu: ChiTieuModel): Response<BaseResponse<ChiTieuModel>>

    @GET("api/chitieu/khoanchi/{id_khoanchi}/user/{userId}")
    suspend fun getChiTieuTheoKhoanChiCuaNguoiDung(
        @Path("id_khoanchi") id_khoanchi: Int,
        @Path("userId") userId: Int,
    ): BaseResponse<List<ChiTieuModel>>
}