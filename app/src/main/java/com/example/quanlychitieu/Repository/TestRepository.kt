package com.example.quanlychitieu.di

import javax.inject.Inject

class TestRepository @Inject constructor() {
    fun getMessage(): String = "Hilt is working!"
}
