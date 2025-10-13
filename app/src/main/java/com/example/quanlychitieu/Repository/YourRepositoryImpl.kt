package com.example.quanlychitieu.Repository

import android.content.Context
import com.example.quanlychitieu.di.TestRepository
import javax.inject.Inject

class YourRepositoryImpl @Inject constructor(
    private val context: Context
) : TestRepository {
    override fun getData(): String = "Hello từ Repository"
}
