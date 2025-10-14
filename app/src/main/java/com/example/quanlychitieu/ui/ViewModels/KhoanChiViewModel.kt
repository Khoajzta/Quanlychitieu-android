package com.example.quanlychitieu.ViewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quanlychitieu.domain.model.KhoanChiModel
import com.example.quanlychitieu.ui.state.UiState
import com.example.quanlychitieu.domain.respository.KhoanChiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class KhoanChiViewModel @Inject constructor(
    private val repo: KhoanChiRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState<List<KhoanChiModel>>>(UiState.Loading)
    val uiState: StateFlow<UiState<List<KhoanChiModel>>> = _uiState


    fun loadKhoanChi(userId: Int) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            try {
                val result = repo.getKhoanChi(userId)
                _uiState.value = UiState.Success(result)
            } catch (e: Exception) {
                _uiState.value = UiState.Error(e.localizedMessage ?: "Lỗi không xác định")
            }
        }
    }

}
