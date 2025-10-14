package com.example.quanlychitieu.data.respository

import android.util.Log
import com.example.quanlychitieu.data.remote.NguoiDungAPIService
import com.example.quanlychitieu.data.remote.dto.BaseResponse
import com.example.quanlychitieu.data.remote.dto.CheckEmailResponse
import com.example.quanlychitieu.domain.model.NguoiDungModel
import com.example.quanlychitieu.domain.respository.NguoiDungRepository
import javax.inject.Inject

class NguoiDungRepositoryImpl @Inject constructor(
    private val api: NguoiDungAPIService
) : NguoiDungRepository {

    override suspend fun createNguoiDung(nguoiDung: NguoiDungModel): BaseResponse<NguoiDungModel> {
        return try {
            val response = api.postNguoiDung(nguoiDung)
            if (response.isSuccessful) {
                response.body() ?: throw Exception("Empty response body")
            } else {
                throw Exception("API Error: ${response.errorBody()?.string()}")
            }
        } catch (e: Exception) {
            throw Exception("Network/API Error: ${e.message}")
        }
    }

    override suspend fun checkEmailNguoidung(email: String): CheckEmailResponse<NguoiDungModel> {
        return try {
            val response = api.checkEmailNguoiDung(email)

            if (response.success) {
                response
            } else {
                throw Exception(response.message)
            }
        } catch (e: Exception) {
            throw Exception("Network/API Error: ${e.message}")
        }
    }

}
