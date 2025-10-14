package com.example.quanlychitieu.ui.ViewModels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quanlychitieu.data.local.DataStoreManager
import com.example.quanlychitieu.data.remote.dto.BaseResponse
import com.example.quanlychitieu.data.remote.dto.CheckEmailResponse
import com.example.quanlychitieu.domain.model.NguoiDungModel
import com.example.quanlychitieu.domain.respository.AuthRepository
import com.example.quanlychitieu.domain.respository.NguoiDungRepository
import com.example.quanlychitieu.ui.state.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NguoiDungViewModel @Inject constructor(
    private val repository: NguoiDungRepository,
    private val authRepository: AuthRepository,
    private val dataStoreManager: DataStoreManager
) : ViewModel() {

    var createNguoiDungState by mutableStateOf<UiState<BaseResponse<NguoiDungModel>>>(UiState.Loading)
        private set

    var loginState by mutableStateOf<UiState<NguoiDungModel>>(UiState.Loading)
        private set

    var checkEmailState by mutableStateOf<UiState<CheckEmailResponse<NguoiDungModel>>>(UiState.Loading)
        private set

    // 🌀 Trạng thái loading toàn cục
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private fun setLoading(loading: Boolean) {
        _isLoading.value = loading
    }

    // 👉 Đăng nhập với Google
    fun loginWithGoogle(idToken: String) {
        viewModelScope.launch {
            try {
                setLoading(true) // bật loading khi bắt đầu
                loginState = UiState.Loading

                val result = authRepository.signInWithGoogle(idToken)
                loginState = result

                if (result is UiState.Success) {
                    val user = result.data
                    // 👉 Lưu user_id và email sau khi đăng nhập thành công
                    dataStoreManager.saveUserId(user.id)

                    Log.d(
                        "LOGIN_SUCCESS",
                        "Tên: ${result.data.ten}, Email: ${result.data.email}, Token: ${result.data.token}"
                    )

                    saveUserId(user.id)
                }
            } catch (e: Exception) {
                loginState = UiState.Error(e.message ?: "Lỗi đăng nhập Google")
                Log.e("LOGIN_ERROR", e.message ?: "Unknown error")
            } finally {
                setLoading(false) // tắt loading dù thành công hay thất bại
            }
        }
    }

    fun getUserId() = dataStoreManager.getUserId()
    fun isFirstLaunch() = dataStoreManager.isFirstLaunch()

    fun setFirstLaunch(isFirst: Boolean) {
        viewModelScope.launch {
            dataStoreManager.setFirstLaunch(isFirst)
        }
    }

    fun saveUserId(userId: Int) {
        viewModelScope.launch {
            dataStoreManager.saveUserId(userId)
            Log.d("SAVE_USER_ID", "Đã lưu userId = $userId")
        }
    }

    fun logout() {
        viewModelScope.launch {
            dataStoreManager.clearUserId()
        }
    }

    fun createNguoiDung(nguoiDung: NguoiDungModel) {
        viewModelScope.launch {
            createNguoiDungState = UiState.Loading
            try {
                val result = repository.createNguoiDung(nguoiDung)
                createNguoiDungState = UiState.Success(result)
            } catch (e: Exception) {
                createNguoiDungState = UiState.Error(e.message ?: "Unknown error")
            }
        }
    }

    fun handleLoginAndCheckUser(
        nguoiDung: NguoiDungModel,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        viewModelScope.launch {
            setLoading(true)
            try {
                val checkResult = repository.checkEmailNguoidung(nguoiDung.email)

                if (checkResult.exists) {
                    Log.d("CHECK_EMAIL", "Email ${checkResult.data.id} tồn tại")
                    saveUserId(checkResult.data.id)
                    onSuccess()
                } else {
                    repository.createNguoiDung(nguoiDung)
                    onSuccess()
                }
            } catch (e: Exception) {
                onError(e.message ?: "Lỗi không xác định")
            } finally {
                setLoading(false)
            }
        }
    }

    fun checkEmailNguoiDung(email: String) {
        viewModelScope.launch {
            checkEmailState = UiState.Loading
            try {
                val result = repository.checkEmailNguoidung(email)
                checkEmailState = UiState.Success(result)
                Log.d("CHECK_EMAIL", "Email $email tồn tại: ${result.exists}")
            } catch (e: Exception) {
                checkEmailState = UiState.Error(e.message ?: "Unknown error")
                Log.e("CHECK_EMAIL_ERROR", e.message ?: "Unknown error")
            }
        }
    }
}
