package com.example.quanlychitieu.data.remote.dto

data class ChuyenTienRequest (
    var fromId :Int,
    var toId:Int,
    var amount:Long,
    var id_nguoidung:Int,
    var ghi_chu: String
)