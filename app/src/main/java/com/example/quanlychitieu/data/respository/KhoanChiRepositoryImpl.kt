package com.example.quanlychitieu.data.respository

import android.util.Log
import com.example.quanlychitieu.data.remote.KhoanChiApiService
import com.example.quanlychitieu.domain.model.KhoanChiModel
import com.example.quanlychitieu.domain.respository.KhoanChiRepository
import javax.inject.Inject

class KhoanChiRepositoryImpl @Inject constructor(
    private val api: KhoanChiApiService
) : KhoanChiRepository {

    override suspend fun getKhoanChi(userId: Int): List<KhoanChiModel> {
        val response = api.getKhoanChiByUser(userId)
        Log.d("API_TEST", "Response: $response")
        if (response.success) {
            return response.data
        } else {
            throw Exception("API trả về success = false")
        }
    }
}