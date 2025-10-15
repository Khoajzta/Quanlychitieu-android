package com.example.quanlychitieu.data.respository

import com.example.quanlychitieu.data.remote.ChiTieuAPIService
import com.example.quanlychitieu.data.remote.dto.BaseResponse
import com.example.quanlychitieu.domain.model.ChiTieuModel
import com.example.quanlychitieu.domain.respository.ChiTieuRespository
import javax.inject.Inject

class ChiTieuRepositoryImpl @Inject constructor(
    private val api: ChiTieuAPIService
): ChiTieuRespository {
    override suspend fun createChiTieu(chitieu: ChiTieuModel): BaseResponse<ChiTieuModel> {
        return try {
            val response = api.postChiTieu(chitieu)
            if (response.isSuccessful) {
                response.body() ?: throw Exception("Empty response body")
            } else {
                throw Exception("API Error: ${response.errorBody()?.string()}")
            }
        } catch (e: Exception) {
            throw Exception("Network/API Error: ${e.message}")
        }
    }
}