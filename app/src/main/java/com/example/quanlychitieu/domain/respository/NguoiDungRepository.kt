package com.example.quanlychitieu.domain.respository

import com.example.quanlychitieu.data.remote.dto.BaseResponse
import com.example.quanlychitieu.data.remote.dto.CheckEmailResponse
import com.example.quanlychitieu.domain.model.NguoiDungModel

interface NguoiDungRepository {
    suspend fun createNguoiDung(nguoidung: NguoiDungModel) : BaseResponse<NguoiDungModel>
    suspend fun checkEmailNguoidung(email :String): CheckEmailResponse<NguoiDungModel>
}