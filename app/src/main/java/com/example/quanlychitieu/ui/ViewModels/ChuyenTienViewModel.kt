package com.example.quanlychitieu.ui.ViewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quanlychitieu.domain.model.ChiTieuModel
import com.example.quanlychitieu.domain.model.ChuyenTienModel
import com.example.quanlychitieu.domain.respository.ChuyenTienRepository
import com.example.quanlychitieu.ui.state.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChuyenTienViewModel@Inject constructor(
    private val repository: ChuyenTienRepository
): ViewModel() {

    private val _uiState = MutableStateFlow<UiState<List<ChuyenTienModel>>>(UiState.Loading)
    val uiState: StateFlow<UiState<List<ChuyenTienModel>>> = _uiState

    fun getLichSuChuyenTienByUser(userId: Int){
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            try {
                val result = repository.getLichSuChuyenTienByUser(userId)
                _uiState.value = UiState.Success(result)
                Log.d("Lịch sử chuyển tiền", "Response: $result")
            }catch (e: Exception) {
                _uiState.value = UiState.Error(e.localizedMessage)
            }
        }
    }
}