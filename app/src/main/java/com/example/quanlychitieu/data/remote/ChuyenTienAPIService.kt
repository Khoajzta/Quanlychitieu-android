package com.example.quanlychitieu.data.remote

import com.example.quanlychitieu.data.remote.dto.BaseResponse
import com.example.quanlychitieu.domain.model.ChuyenTienModel
import com.example.quanlychitieu.domain.model.KhoanChiModel
import retrofit2.http.GET
import retrofit2.http.Path

interface ChuyenTienAPIService {

    @GET("api/chuyentien/user/{userId}")
    suspend fun getLichSuChuyenTienByUser(@Path("userId") userId: Int): BaseResponse<List<ChuyenTienModel>>
}