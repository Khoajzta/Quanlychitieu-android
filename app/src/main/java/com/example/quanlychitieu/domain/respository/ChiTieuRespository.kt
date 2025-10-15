package com.example.quanlychitieu.domain.respository

import com.example.quanlychitieu.data.remote.dto.BaseResponse
import com.example.quanlychitieu.domain.model.ChiTieuModel

interface ChiTieuRespository  {

    suspend fun createChiTieu(chitieu: ChiTieuModel) : BaseResponse<ChiTieuModel>
}