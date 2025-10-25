package com.example.quanlychitieu.domain.respository

import com.example.quanlychitieu.data.remote.dto.BaseResponseMes
import com.example.quanlychitieu.data.remote.dto.ChuyenTienRequest
import com.example.quanlychitieu.data.remote.dto.StatusResponse
import com.example.quanlychitieu.domain.model.TaiKhoanModel

interface TaiKhoanRepository {
    suspend fun getTaiKhoanNguoiDung(userId: Int) : List<TaiKhoanModel>
    suspend fun createaTaiKhoan(taikhoan: TaiKhoanModel): BaseResponseMes<TaiKhoanModel>
    suspend fun updateTaiKhoan(taikhoan: TaiKhoanModel, id: Int): BaseResponseMes<TaiKhoanModel>
    suspend fun chuyenTien(chuyenTienRequest: ChuyenTienRequest) : StatusResponse
    suspend fun deleteTaiKhoan(id: Int): StatusResponse
}