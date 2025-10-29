package com.example.quanlychitieu.domain.respository

import com.example.quanlychitieu.data.remote.dto.BaseResponse
import com.example.quanlychitieu.data.remote.dto.BaseResponseMes
import com.example.quanlychitieu.data.remote.dto.StatusResponse
import com.example.quanlychitieu.domain.model.ChiTieuModel
import com.example.quanlychitieu.domain.model.ThongKeChiTieuModel

interface ChiTieuRespository  {

    suspend fun createChiTieu(chitieu: ChiTieuModel) : BaseResponse<ChiTieuModel>
    suspend fun getChiTieuTheoKhoanChiCuaNguoiDung(id_khoanchi: Int, userId: Int): List<ChiTieuModel>
    suspend fun getChiTieuTheoThangVaNam(userId: Int, thang: Int, nam: Int): List<ChiTieuModel>
    suspend fun deleteChiTieu(id: Int): StatusResponse
    suspend fun thongKeTheoNam(userId: Int, nam: Int): BaseResponseMes<List<ThongKeChiTieuModel>>
}
