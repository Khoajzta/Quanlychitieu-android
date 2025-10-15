package com.example.quanlychitieu.domain.respository

import com.example.quanlychitieu.domain.model.TaiKhoanModel

interface TaiKhoanRepository {

    suspend fun getTaiKhoanNguoiDung(userId: Int) : List<TaiKhoanModel>
}