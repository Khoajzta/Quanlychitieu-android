package com.example.quanlychitieu.domain.respository

import com.example.quanlychitieu.data.remote.dto.BaseResponse
import com.example.quanlychitieu.domain.model.ChiTieuModel

interface ChiTieuRespository  {

    suspend fun createChiTieu(chitieu: ChiTieuModel) : BaseResponse<ChiTieuModel>
    suspend fun getChiTieuTheoKhoanChiCuaNguoiDung(id_khoanchi: Int, userId: Int): List<ChiTieuModel>
    suspend fun getChiTieuTheoThangVaNam(userId: Int, thang: Int, nam: Int): List<ChiTieuModel>
}