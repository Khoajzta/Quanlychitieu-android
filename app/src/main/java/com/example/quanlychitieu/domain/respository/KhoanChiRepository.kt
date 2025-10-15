package com.example.quanlychitieu.domain.respository

import com.example.quanlychitieu.data.remote.dto.BaseResponse
import com.example.quanlychitieu.domain.model.KhoanChiModel

interface KhoanChiRepository {
    suspend fun getKhoanChi(userId: Int): List<KhoanChiModel>
}