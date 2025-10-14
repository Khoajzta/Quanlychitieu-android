package com.example.quanlychitieu.domain.respository

import com.example.quanlychitieu.domain.model.NguoiDungModel
import com.example.quanlychitieu.ui.state.UiState

interface AuthRepository {
    suspend fun signInWithGoogle(idToken: String): UiState<NguoiDungModel>
}
