package com.example.quanlychitieu.data.respository

import android.util.Log
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

    override suspend fun getChiTieuTheoKhoanChiCuaNguoiDung(
        id_khoanchi: Int,
        userId: Int
    ): List<ChiTieuModel> {
        val response = api.getChiTieuTheoKhoanChiCuaNguoiDung(id_khoanchi = id_khoanchi, userId = userId)
        Log.d("API_TEST", "Response: $response")
        if (response.success) {
            return response.data
        } else {
            throw Exception("API trả về success = false")
        }
    }

    override suspend fun getChiTieuTheoThangVaNam(
        userId: Int,
        thang: Int,
        nam: Int
    ): List<ChiTieuModel> {
        val response = api.getChiTieuTheoThangVaNam(userId = userId, thang = thang, nam = nam)
        Log.d("API_TEST", "Response: $response")
        if (response.success) {
            return response.data
        } else {
            throw Exception("API trả về success = false")
        }
    }
}