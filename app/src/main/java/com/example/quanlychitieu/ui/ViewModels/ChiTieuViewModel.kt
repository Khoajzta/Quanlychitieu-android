package com.example.quanlychitieu.ui.ViewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quanlychitieu.data.remote.dto.BaseResponse
import com.example.quanlychitieu.domain.model.ChiTieuModel
import com.example.quanlychitieu.domain.respository.ChiTieuRespository
import com.example.quanlychitieu.ui.state.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChiTieuViewModel @Inject constructor(
    private val repository: ChiTieuRespository
): ViewModel() {
    var createChiTieuState by mutableStateOf<UiState<BaseResponse<ChiTieuModel>>>(UiState.Loading)
        private set

    fun createChiTieu(chitieu: ChiTieuModel) {
        viewModelScope.launch {
            createChiTieuState = UiState.Loading
            try {
                val result = repository.createChiTieu(chitieu)
                createChiTieuState = UiState.Success(result)
            } catch (e: Exception) {
                createChiTieuState = UiState.Error(e.message ?: "Unknown error")
            }
        }
    }
}