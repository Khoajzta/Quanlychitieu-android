package com.example.quanlychitieu.ui.ViewModels


import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quanlychitieu.data.remote.dto.BaseResponse
import com.example.quanlychitieu.data.remote.dto.StatusResponse
import com.example.quanlychitieu.domain.model.KhoanChiModel
import com.example.quanlychitieu.domain.model.ThuNhapModel
import com.example.quanlychitieu.domain.respository.ThuNhapRepository
import com.example.quanlychitieu.ui.state.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ThuNhapViewModel @Inject constructor(
    private val repository: ThuNhapRepository
) : ViewModel() {

    var thuNhapState by mutableStateOf<UiState<List<ThuNhapModel>>>(UiState.Loading)
        private set

    var thuNhapCreateState by mutableStateOf<UiState<BaseResponse<ThuNhapModel>>>(UiState.Loading)
        private set

    var deleteThuNhapState by mutableStateOf<UiState<StatusResponse>>(UiState.Loading)
        private set

    fun getThuNhapTheoThang(userId: Int, thang: Int, nam: Int) {
        viewModelScope.launch {
            thuNhapState = UiState.Loading
            try {
                val result = repository.getThuNhapTheoThang(userId, thang, nam)
                if (result.success) {
                    // ⚡ Quan trọng: Cập nhật state tại đây
                    thuNhapState = UiState.Success(result.data)
                    Log.d("THU_NHAP_VIEWMODEL", "Dữ liệu nhận được: ${result.data}")
                } else {
                    thuNhapState = UiState.Error( "Lỗi không xác định")
                    Log.e("THU_NHAP_VIEWMODEL", "Lỗi từ API: ${result}")
                }
            } catch (e: Exception) {
                thuNhapState = UiState.Error(e.message ?: "Lỗi kết nối")
                Log.e("THU_NHAP_VIEWMODEL", "Exception: ${e.message}")
            }
        }
    }

    fun createThuNhap(thunhap: ThuNhapModel) {
        viewModelScope.launch {
            thuNhapCreateState = UiState.Loading
            try {
                val result = repository.createThuNhap(thunhap)
                thuNhapCreateState = UiState.Success(result)
            } catch (e: Exception) {
                thuNhapCreateState = UiState.Error(e.message ?: "Unknown error")
            }
        }
    }

    fun deleteThuNhap(id: Int) {
        viewModelScope.launch {
            deleteThuNhapState = UiState.Loading
            try {
                val result = repository.deleteThuNhap(id)
                if (result.success) {
                    deleteThuNhapState = UiState.Success(result)
                } else {
                    deleteThuNhapState = UiState.Error(result.message)
                }
            } catch (e: Exception) {
                deleteThuNhapState = UiState.Error(e.message ?: "Lỗi không xác định")
            }
        }
    }
}

