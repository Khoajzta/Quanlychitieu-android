package com.example.quanlychitieu.ViewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quanlychitieu.data.remote.dto.BaseResponse
import com.example.quanlychitieu.data.remote.dto.BaseResponseMes
import com.example.quanlychitieu.data.remote.dto.StatusResponse
import com.example.quanlychitieu.domain.model.KhoanChiModel
import com.example.quanlychitieu.domain.model.NguoiDungModel
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

    var createKhoanChiState by mutableStateOf<UiState<BaseResponse<KhoanChiModel>>>(UiState.Loading)
        private set
    var getKhoanChiByIdState by mutableStateOf<UiState<KhoanChiModel>>(UiState.Loading)
        private set
    var updateKhoanChiState by mutableStateOf<UiState<BaseResponseMes<KhoanChiModel>>>(UiState.Loading)
        private set

    var deleteKhoanChiState by mutableStateOf<UiState<StatusResponse>>(UiState.Loading)
        private set


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

    fun getKhoanChiById(id_khoanchi: Int) {
        viewModelScope.launch {
            getKhoanChiByIdState = UiState.Loading
            try {
                val result = repo.getKhoanChiById(id_khoanchi)
                getKhoanChiByIdState = UiState.Success(result)
            } catch (e: Exception) {
                e.printStackTrace()
                getKhoanChiByIdState = UiState.Error(e.localizedMessage ?: "Lỗi không xác định")
            }
        }
    }


    fun createKhoanChi(khoanchi: KhoanChiModel) {
        viewModelScope.launch {
            createKhoanChiState = UiState.Loading
            try {
                val result = repo.createKhoanChi(khoanchi)
                createKhoanChiState = UiState.Success(result)
            } catch (e: Exception) {
                createKhoanChiState = UiState.Error(e.message ?: "Unknown error")
            }
        }
    }

    fun updateKhoanChi(id_khoanchi: Int, khoanchi: KhoanChiModel) {
        viewModelScope.launch {
            updateKhoanChiState = UiState.Loading
            try {
                val result = repo.updateKhoanChi(id_khoanchi, khoanchi)
                if (result.success) {
                    updateKhoanChiState = UiState.Success(result)
                } else {
                    updateKhoanChiState = UiState.Error(result.message)
                }
            } catch (e: Exception) {
                updateKhoanChiState = UiState.Error(e.message ?: "Lỗi không xác định")
            }
        }
    }

    fun deleteKhoanChi(id_khoanchi: Int) {
        viewModelScope.launch {
            deleteKhoanChiState = UiState.Loading
            try {
                val result = repo.deleteKhoanChi(id_khoanchi)
                if (result.success) {
                    deleteKhoanChiState = UiState.Success(result)
                } else {
                    deleteKhoanChiState = UiState.Error(result.message)
                }
            } catch (e: Exception) {
                deleteKhoanChiState = UiState.Error(e.message ?: "Lỗi không xác định")
            }
        }
    }

    fun resetCreateKhoanChiState() {
        createKhoanChiState = UiState.Loading
    }

    fun resetUpdateState() {
        updateKhoanChiState = UiState.Loading
    }

    fun resetDeleteState() {
        deleteKhoanChiState = UiState.Loading
    }

}
