

package com.example.quanlychitieu.ViewModels

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TestViewModel @Inject constructor(
    // inject repository hoặc dependency vào đây nếu có
) : ViewModel() {

}
