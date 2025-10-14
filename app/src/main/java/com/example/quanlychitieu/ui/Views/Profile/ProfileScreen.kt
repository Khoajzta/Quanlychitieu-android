import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.quanlychitieu.Components.CustomButton
import com.example.quanlychitieu.Components.TopBar
import com.example.quanlychitieu.ui.ViewModels.NguoiDungViewModel
import com.example.quanlychitieu.ui.theme.BackgroundColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    navController: NavController,
    viewModel: NguoiDungViewModel = hiltViewModel()
){
    Scaffold(
        containerColor = BackgroundColor,
        topBar = {
            Header(navController, Modifier.windowInsetsPadding(WindowInsets.statusBars), title = "Thông tin cá nhân")
        },
        contentWindowInsets = WindowInsets(0, 0, 0, 0)
    ) {innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding)
        ) {
            CustomButton(
                title = "Đăng xuất",
                onClick = {
                    viewModel.logout()
                    navController.navigate(Screen.Login.route) {
                        popUpTo(0)  // Xoá hết backstack
                    }
                },
            )
        }
    }
}

@Composable
@Preview
fun ProfileScreenPreview(){
    var navController = rememberNavController()
    ProfileScreen(navController)
}
