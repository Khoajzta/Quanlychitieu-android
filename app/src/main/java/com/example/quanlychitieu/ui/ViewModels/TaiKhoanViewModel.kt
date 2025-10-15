package com.example.quanlychitieu.ui.ViewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quanlychitieu.domain.model.KhoanChiModel
import com.example.quanlychitieu.domain.model.TaiKhoanModel
import com.example.quanlychitieu.domain.respository.TaiKhoanRepository
import com.example.quanlychitieu.ui.state.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaiKhoanViewModel @Inject constructor(
    private val repo: TaiKhoanRepository
): ViewModel(){
    private val _uiState = MutableStateFlow<UiState<List<TaiKhoanModel>>>(UiState.Loading)
    val uiState: StateFlow<UiState<List<TaiKhoanModel>>> = _uiState

    fun loadTaiKhoans(userId: Int) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            try {
                val result = repo.getTaiKhoanNguoiDung(userId)
                _uiState.value = UiState.Success(result)
            } catch (e: Exception) {
                _uiState.value = UiState.Error(e.localizedMessage ?: "Lỗi không xác định")
            }
        }
    }
}