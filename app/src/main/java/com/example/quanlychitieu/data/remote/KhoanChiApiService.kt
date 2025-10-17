package com.example.quanlychitieu.data.remote

import com.example.quanlychitieu.domain.model.KhoanChiModel
import com.example.quanlychitieu.data.remote.dto.BaseResponse
import com.example.quanlychitieu.data.remote.dto.BaseResponseMes
import com.example.quanlychitieu.data.remote.dto.StatusResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface KhoanChiApiService{
    @GET("api/khoanchi/user/{userId}")
    suspend fun getKhoanChiByUser(@Path("userId") userId: Int): BaseResponse<List<KhoanChiModel>>

    @GET("api/khoanchi/{id_khoanchi}")
    suspend fun getKhoanChiByID(@Path("id_khoanchi") id_khoanchi: Int): BaseResponse<KhoanChiModel>

    @POST("api/khoanchi")
    suspend fun createKhoanChi(@Body khoanChi: KhoanChiModel): Response<BaseResponse<KhoanChiModel>>

    @PUT("api/khoanchi/{id_khoanchi}")
    suspend fun updateKhoanChi(
        @Path("id_khoanchi") id_khoanchi: Int,
        @Body khoanChi: KhoanChiModel
    ): Response<BaseResponseMes<KhoanChiModel>>

    @DELETE("api/khoanchi/{id_khoanchi}")
    suspend fun deleteKhoanChi(
        @Path("id_khoanchi") id_khoanchi: Int
    ): Response<StatusResponse>

}