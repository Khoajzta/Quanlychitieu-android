package com.example.quanlychitieu.data.respository

import android.util.Log
import com.example.quanlychitieu.data.remote.TaiKhoanAPIService
import com.example.quanlychitieu.domain.model.TaiKhoanModel
import com.example.quanlychitieu.domain.respository.TaiKhoanRepository
import javax.inject.Inject

class TaiKhoanRepositoryImpl@Inject constructor(
    private val api: TaiKhoanAPIService
): TaiKhoanRepository {
    override suspend fun getTaiKhoanNguoiDung(userId: Int): List<TaiKhoanModel> {
        val response = api.getTaiKhoanNguoiDung(userId)
        Log.d("API_TEST", "Response: $response")
        if (response.success) {
            return response.data
        } else {
            throw Exception("API trả về success = false")
        }
    }
}