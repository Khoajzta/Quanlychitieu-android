package com.example.quanlychitieu.models

data class KhoanChiModel(
    val id: Int,
    val ten_khoanchi: String,
    val id_nguoidung: Int,
    val so_tien_du_kien: Int,
    val ngay_batdau: String,
    val ngay_ketthuc: String,
    val mausac: String,
    val emoji: String,
    val so_luong_chi_tieu: Int = 0,
    val tong_tien_da_chi: Int = 0,

)