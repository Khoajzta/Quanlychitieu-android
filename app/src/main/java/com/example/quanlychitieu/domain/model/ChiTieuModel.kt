package com.example.quanlychitieu.domain.model

data class ChiTieuModel(
    val id: Int,
    val id_nguoidung: Int,
    val id_khoanchi: Int,
    val id_taikhoan: Int,
    val so_tien: Int,
    val ngay_tao: String,
    val ghi_chu: String,
)