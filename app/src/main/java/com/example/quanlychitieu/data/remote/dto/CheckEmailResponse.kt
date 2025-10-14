package com.example.quanlychitieu.data.remote.dto

data class CheckEmailResponse<T> (
    val success: Boolean,
    val exists : Boolean,
    val data: T,
    val message: String
)
