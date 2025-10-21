package com.example.quanlychitieu.data.remote

import com.example.quanlychitieu.data.remote.dto.BaseResponse
import com.example.quanlychitieu.data.remote.dto.BaseResponseMes
import com.example.quanlychitieu.data.remote.dto.StatusResponse
import com.example.quanlychitieu.domain.model.ThuNhapModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ThuNhapAPIService {
    @GET("api/thunhap/user/{id}/by-month")
    suspend fun getThuNhapTheoThang(
        @Path("id") userId: Int,
        @Query("thang") thang: Int,
        @Query("nam") nam: Int
    ): Response<BaseResponseMes<List<ThuNhapModel>>>

    @POST("api/thunhap")
    suspend fun createThuNhap(@Body thunhap: ThuNhapModel): Response<BaseResponse<ThuNhapModel>>

    @DELETE("api/thunhap/{id}")
    suspend fun deleteThuNhap(@Path("id") id :Int): Response<StatusResponse>

}