package com.example.quanlychitieu.data.remote

import com.example.quanlychitieu.data.remote.dto.BaseResponse
import com.example.quanlychitieu.domain.model.TaiKhoanModel
import retrofit2.http.GET
import retrofit2.http.Path

interface TaiKhoanAPIService {

    @GET("api/taikhoan/user/{userId}")
    suspend fun getTaiKhoanNguoiDung(@Path("userId") userId: Int): BaseResponse<List<TaiKhoanModel>>
}