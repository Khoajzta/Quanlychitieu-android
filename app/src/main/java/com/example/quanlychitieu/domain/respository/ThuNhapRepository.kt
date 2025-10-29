package com.example.quanlychitieu.domain.respository

import com.example.quanlychitieu.data.remote.dto.BaseResponse
import com.example.quanlychitieu.data.remote.dto.BaseResponseMes
import com.example.quanlychitieu.data.remote.dto.StatusResponse
import com.example.quanlychitieu.domain.model.ThongKeThuNhapModel
import com.example.quanlychitieu.domain.model.ThuNhapModel

interface ThuNhapRepository {
    suspend fun getThuNhapTheoThang(userId:Int, thang:Int, nam:Int) : BaseResponseMes<List<ThuNhapModel>>
    suspend fun thongkeTheoNam(userId:Int, nam:Int) : BaseResponseMes<List<ThongKeThuNhapModel>>
    suspend fun createThuNhap(thuNhapModel: ThuNhapModel): BaseResponse<ThuNhapModel>
    suspend fun deleteThuNhap(id:Int):StatusResponse
}