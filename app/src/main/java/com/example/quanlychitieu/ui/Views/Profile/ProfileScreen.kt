import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.quanlychitieu.Components.CustomButton
import com.example.quanlychitieu.Components.DotLoading
import com.example.quanlychitieu.ui.ViewModels.NguoiDungViewModel
import com.example.quanlychitieu.ui.Views.Profile.components.AppSettingCard
import com.example.quanlychitieu.ui.Views.Profile.components.ProfileAvartar
import com.example.quanlychitieu.ui.Views.Profile.components.ProfileNameEmail
import com.example.quanlychitieu.ui.components.ThongBaoDialog
import com.example.quanlychitieu.ui.state.UiState
import com.example.quanlychitieu.ui.theme.BackgroundColor
import com.example.quanlychitieu.ui.theme.Dimens.PaddingBody

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    navController: NavController,
    nguoiDungViewModel: NguoiDungViewModel = hiltViewModel(),
    userId: Int
) {
    val getNguoiDungState = nguoiDungViewModel.getByIdState
    val isDarkMode = remember { mutableStateOf(false) }
    val showLogoutDialog = remember { mutableStateOf(false) }

    LaunchedEffect(userId) {
        nguoiDungViewModel.getNguoiDungByID(userId)
    }

    val nguoiDung = when (getNguoiDungState) {
        is UiState.Success -> getNguoiDungState.data.data
        else -> null
    }

    Scaffold(
        containerColor = BackgroundColor,
        topBar = {
            Header(
                navController = navController,
                modifier = Modifier.windowInsetsPadding(WindowInsets.statusBars),
                title = "Hồ sơ cá nhân",
                userId = userId
            )
        },
        contentWindowInsets = WindowInsets(0, 0, 0, 0)
    ) { innerPadding ->

        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            when (getNguoiDungState) {
                is UiState.Success -> {
                    nguoiDung?.let {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .verticalScroll(rememberScrollState())
                                .padding(horizontal = PaddingBody),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            // Ảnh đại diện
                            ProfileAvartar(url = it.url_avt)

                            Spacer(modifier = Modifier.height(16.dp))

                            // Tên + email
                            ProfileNameEmail(name = it.ten, email = it.email)

                            Spacer(modifier = Modifier.height(24.dp))

                            // Cài đặt
                            AppSettingCard(
                                modifier = Modifier,
                                isDarkMode = isDarkMode
                            )

                            Spacer(modifier = Modifier.height(32.dp))

                            // Nút đăng xuất
                            CustomButton(
                                title = "Đăng xuất",
                                onClick = { showLogoutDialog.value = true },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(50.dp)
                            )
                        }
                    }
                }

                is UiState.Loading -> DotLoading()

                is UiState.Error -> Text(
                    text = "Lỗi: ${getNguoiDungState.message}",
                    color = Color.Red,
                    fontSize = 16.sp
                )

                else -> Unit
            }

            // 🔔 Dialog xác nhận đăng xuất
            if (showLogoutDialog.value) {
                ThongBaoDialog(
                    title = "Đăng xuất",
                    message = "Bạn có chắc chắn muốn đăng xuất không?",
                    confirmText = "Đăng xuất",
                    dismissText = "Hủy",
                    confirmButtonColor = Color.Red,
                    onDismiss = { showLogoutDialog.value = false },
                    onConfirm = {
                        showLogoutDialog.value = false
                        nguoiDungViewModel.logout()
                        navController.navigate(Screen.Login.route) {
                            popUpTo(0)
                        }
                    }
                )
            }
        }
    }
}


@Composable
fun ProfileInfoRow(
    title: String,
    value: String,
    isDark: Boolean
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = title,
            fontSize = 16.sp,
            color = if (isDark) Color.White else Color.Black
        )
        Text(
            text = value,
            fontSize = 16.sp,
            color = if (isDark) Color(0xFF9E9E9E) else Color(0xFF616161)
        )
    }
}






@Composable
@Preview
fun ProfileScreenPreview(){
    var navController = rememberNavController()
    ProfileScreen(navController, userId = 1 )
}
