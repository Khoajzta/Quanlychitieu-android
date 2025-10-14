package com.example.quanlychitieu.data.remote.dto

data class BaseResponse<T>(
    val success: Boolean,
    val data: T
)