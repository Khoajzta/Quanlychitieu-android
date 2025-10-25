import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.quanlychitieu.Components.CustomButton
import com.example.quanlychitieu.ViewModels.KhoanChiViewModel
import com.example.quanlychitieu.Views.Trade.Components.TradeButtonAdd
import com.example.quanlychitieu.domain.model.KhoanChiModel
import com.example.quanlychitieu.ui.state.UiState
import com.example.quanlychitieu.ui.theme.BackgroundColor
import com.example.quanlychitieu.ui.theme.Dimens.PaddingBody
import kotlinx.coroutines.delay

@Composable
fun TradeScreen(
    navController: NavController,
    userId :Int,
    khoanChiViewModel: KhoanChiViewModel = hiltViewModel(),
) {
    val KhoanChiuiState by khoanChiViewModel.uiState.collectAsState()

    LaunchedEffect(userId) {
        if (userId > 0) {
            while (true) {
                khoanChiViewModel.loadKhoanChi(userId)
                delay(15 * 60 * 1000L)
            }
        }
    }

    val khoanChiList = when (KhoanChiuiState) {
        is UiState.Success -> (KhoanChiuiState as UiState.Success<List<KhoanChiModel>>).data
        else -> emptyList()
    }


    Scaffold(
        containerColor = BackgroundColor,
        topBar = {
            Header(
                navController,
                Modifier.windowInsetsPadding(WindowInsets.statusBars),
                title = "Giao dịch",
                userId
            )
        },
        contentWindowInsets = WindowInsets(0, 0, 0, 0)
    ) { innerPadding ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            TradeTabPage(
                navController = navController ,
                listKhoanChi= khoanChiList,
                userId = userId
            )

            CustomButton(
                modifier = Modifier
                    .padding(horizontal = PaddingBody)
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .navigationBarsPadding()
                    .padding(bottom = PaddingBody),
                title = "Thêm giao dịch",
                onClick = {
                    navController.navigate(Screen.AddTrade.createRoute(userId))
                },
                icon = Icons.Default.AddCircle
            )
        }
    }
}

@Composable
@Preview
fun TradeScreenPreview() {
//    var navController = rememberNavController()
//    TradeScreen(navController)
}


