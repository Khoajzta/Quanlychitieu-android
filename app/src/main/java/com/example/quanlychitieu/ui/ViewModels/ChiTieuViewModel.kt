package com.example.quanlychitieu.ui.ViewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quanlychitieu.data.remote.dto.BaseResponse
import com.example.quanlychitieu.domain.model.ChiTieuModel
import com.example.quanlychitieu.domain.model.KhoanChiModel
import com.example.quanlychitieu.domain.respository.ChiTieuRespository
import com.example.quanlychitieu.ui.state.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChiTieuViewModel @Inject constructor(
    private val repository: ChiTieuRespository
): ViewModel() {

    private val _uiState = MutableStateFlow<UiState<List<ChiTieuModel>>>(UiState.Loading)
    val uiState: StateFlow<UiState<List<ChiTieuModel>>> = _uiState
    private val _uiStateTheoThang = MutableStateFlow<UiState<List<ChiTieuModel>>>(UiState.Loading)
    val uiStateTheoThang: StateFlow<UiState<List<ChiTieuModel>>> = _uiStateTheoThang
    var createChiTieuState by mutableStateOf<UiState<BaseResponse<ChiTieuModel>>>(UiState.Loading)
        private set

    fun getChiTieuTheoKhoanChiCuaUser(id_khoanchi: Int, userId: Int) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            try {
                val result = repository.getChiTieuTheoKhoanChiCuaNguoiDung(id_khoanchi = id_khoanchi, userId = userId)
                _uiState.value = UiState.Success(result)
            } catch (e: Exception) {
                _uiState.value = UiState.Error(e.localizedMessage ?: "Lỗi không xác định")
            }
        }
    }

    fun getChiTieuTheoThangVaNam(userId :Int, thang:Int, nam: Int) {
        viewModelScope.launch {
            _uiStateTheoThang.value = UiState.Loading
            try {
                val result = repository.getChiTieuTheoThangVaNam(userId = userId, thang = thang, nam = nam)
                _uiStateTheoThang.value = UiState.Success(result)
            } catch (e: Exception) {
                _uiStateTheoThang.value = UiState.Error(e.localizedMessage ?: "Lỗi không xác định")
            }
        }
    }

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