package com.example.quanlychitieu.domain.respository

import com.example.quanlychitieu.domain.model.ChuyenTienModel

interface ChuyenTienRepository {
    suspend fun getLichSuChuyenTienByUser(userId: Int): List<ChuyenTienModel>
}
