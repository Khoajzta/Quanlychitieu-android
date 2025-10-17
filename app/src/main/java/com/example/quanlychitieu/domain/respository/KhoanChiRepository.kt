package com.example.quanlychitieu.domain.respository

import com.example.quanlychitieu.data.remote.dto.BaseResponse
import com.example.quanlychitieu.data.remote.dto.BaseResponseMes
import com.example.quanlychitieu.data.remote.dto.StatusResponse
import com.example.quanlychitieu.domain.model.KhoanChiModel

interface KhoanChiRepository {
    suspend fun getKhoanChi(userId: Int): List<KhoanChiModel>
    suspend fun getKhoanChiById(id_khoanchi: Int): KhoanChiModel
    suspend fun createKhoanChi(khoanchi : KhoanChiModel) : BaseResponse<KhoanChiModel>
    suspend fun updateKhoanChi(id_khoanchi: Int, khoanchi: KhoanChiModel): BaseResponseMes<KhoanChiModel>
    suspend fun deleteKhoanChi(id_khoanchi: Int): StatusResponse
}