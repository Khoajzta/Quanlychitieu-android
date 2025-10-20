package com.example.quanlychitieu.domain.respository

import com.example.quanlychitieu.data.remote.dto.BaseResponse
import com.example.quanlychitieu.data.remote.dto.StatusResponse
import com.example.quanlychitieu.domain.model.ThuNhapModel

interface ThuNhapRepository {
    suspend fun getThuNhapTheoThang(userId:Int, thang:Int, nam:Int) : BaseResponse<List<ThuNhapModel>>
    suspend fun createThuNhap(thuNhapModel: ThuNhapModel): BaseResponse<ThuNhapModel>
    suspend fun deleteThuNhap(id:Int):StatusResponse
}