package com.example.quanlychitieu.domain.model

data class ChuyenTienModel(
    val id: Int,
    val id_nguoidung: Int,
    val id_taikhoan_nguon: Int,
    val id_taikhoan_dich: Int,
    val so_tien: Long,
    val ngay_chuyen: String,
    val ghi_chu: String,
)