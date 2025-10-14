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
import com.example.quanlychitieu.Components.BouncingDotsLoader
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
    val isFirstLaunch by viewModel.isFirstLaunch().collectAsState(initial = true)

    Log.d("userId", userId.toString())
    Log.d("isFirstLaunch", isFirstLaunch.toString())

    LaunchedEffect(Unit) {
        if (!isFirstLaunch) {
           delay(2000)

            if (userId != null) {
                navController.navigate(Screen.Home.createRoute(userId!!)) {
                    popUpTo(0)
                }
            } else {
                onNavigateToLogin()
            }
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        // Background image
        Image(
            painter = painterResource(R.drawable.bg_splash),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize(),
        )

        // Gradient overlay
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color.Black.copy(alpha = 0.6f),
                            Color.Transparent,
                            Color.Black.copy(alpha = 0.8f)
                        ),
                        startY = 0f,
                        endY = Float.POSITIVE_INFINITY
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

            if (isFirstLaunch) {
                // 👉 Khi lần đầu mở app, hiện nút bắt đầu
                Button(
                    onClick = onNavigateToLogin,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White.copy(alpha = 0.15f),
                        contentColor = Color.White
                    ),
                    shape = RoundedCornerShape(50),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(55.dp)
                ) {
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Bắt đầu",
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Icon(
                            imageVector = Icons.Default.ArrowForwardIos,
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }
            } else {
                // 👉 Các lần sau thì chỉ hiện loading 2 giây rồi auto điều hướng
                BouncingDotsLoader()

                if(userId != null){
                    navController.navigate(Screen.Home.createRoute(userId!!)) {
                        popUpTo(0)
                    }
                }else{
                    navController.navigate(Screen.Login.route)
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
