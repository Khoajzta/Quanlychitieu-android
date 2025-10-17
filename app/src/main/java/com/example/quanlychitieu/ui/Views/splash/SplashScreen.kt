import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.quanlychitieu.Components.CustomButton
import com.example.quanlychitieu.Components.DotLoading
import com.example.quanlychitieu.R
import com.example.quanlychitieu.ui.ViewModels.NguoiDungViewModel
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    navController: NavController,
    onNavigateToLogin: () -> Unit,
    viewModel: NguoiDungViewModel = hiltViewModel()
) {
    val userId by viewModel.getUserId().collectAsState(initial = null)
    val isFirstLaunch by viewModel.isFirstLaunch().collectAsState(initial = null)

    Log.d("userId", userId.toString())

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(R.drawable.bg_splash),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize(),
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color.Black.copy(alpha = 0.6f),
                            Color.Transparent,
                            Color.Black.copy(alpha = 0.8f)
                        )
                    )
                )
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp, vertical = 50.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Spacer(modifier = Modifier.height(40.dp))

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "Kiểm soát chi tiêu",
                    color = Color.White,
                    fontSize = 34.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = "Hướng tới tương lai tài chính vững chắc",
                    color = Color.White.copy(alpha = 0.85f),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium
                )
            }

            when (isFirstLaunch) {
                null -> {
                    // Chưa load xong DataStore
                    DotLoading()
                }
                true -> {
                    // Lần đầu mở app
                    CustomButton(
                        title = "Bắt đầu",
                        onClick = {
                            viewModel.setFirstLaunch(false)
                            onNavigateToLogin()
                        }
                    )
                }
                false -> {
                    // Đã mở app trước đây
                    LaunchedEffect(userId) {
                        delay(1000)
                        if (userId != null) {
                            navController.navigate(Screen.Home.createRoute(userId!!)) {
                                popUpTo(0)
                            }
                        } else {
                            onNavigateToLogin()
                        }
                    }
                    DotLoading()
                }
            }
        }
    }
}



@Preview
@Composable
fun PreviewSplashScreen() {
//    SplashScreen(
//        onNavigateToHome = {},
//        onNavigateToLogin = {}
//    )
}
