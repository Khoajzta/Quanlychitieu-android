package com.example.quanlychitieu.data.remote

import com.example.quanlychitieu.data.remote.dto.BaseResponse
import com.example.quanlychitieu.domain.model.ChiTieuModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ChiTieuAPIService {
    @POST("api/chitieu")
    suspend fun postChiTieu(@Body chitieu: ChiTieuModel): Response<BaseResponse<ChiTieuModel>>
}