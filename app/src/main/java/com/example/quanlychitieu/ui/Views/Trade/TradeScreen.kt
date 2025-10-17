import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
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
import androidx.navigation.compose.rememberNavController
import com.example.quanlychitieu.Utils.listKhoanChiConst.listKhoanChi
import com.example.quanlychitieu.Utils.thuNhapListSample
import com.example.quanlychitieu.ViewModels.KhoanChiViewModel
import com.example.quanlychitieu.Views.Trade.Components.TradeButtonAdd
import com.example.quanlychitieu.domain.model.KhoanChiModel
import com.example.quanlychitieu.domain.model.TaiKhoanModel
import com.example.quanlychitieu.domain.model.ThuNhapModel
import com.example.quanlychitieu.ui.ViewModels.TaiKhoanViewModel
import com.example.quanlychitieu.ui.state.UiState
import com.example.quanlychitieu.ui.theme.BackgroundColor
import kotlinx.coroutines.delay

@Composable
fun TradeScreen(
    navController: NavController,
    userId :Int,
    khoanChiViewModel: KhoanChiViewModel = hiltViewModel(),
    taiKhoanViewModel: TaiKhoanViewModel = hiltViewModel()
) {

    val KhoanChiuiState by khoanChiViewModel.uiState.collectAsState()
    val taiKhoanUiState by taiKhoanViewModel.uiState.collectAsState()


    LaunchedEffect(userId) {
        if (userId > 0) {
            while (true) {
                khoanChiViewModel.loadKhoanChi(userId)
                taiKhoanViewModel.loadTaiKhoans(userId)
                delay(15 * 60 * 1000L)
            }
        }
    }

    val khoanChiList = when (KhoanChiuiState) {
        is UiState.Success -> (KhoanChiuiState as UiState.Success<List<KhoanChiModel>>).data
        else -> emptyList()
    }

    val taikhoanList = when (taiKhoanUiState) {
        is UiState.Success -> (taiKhoanUiState as UiState.Success<List<TaiKhoanModel>>).data
        else -> emptyList()
    }

    ChiTieuPage(
        listKhoanChi = listKhoanChi,
    )

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
            // ✅ Nội dung không có padding bottom
            TradeTabPage(khoanChiList, thuNhapListSample)

            TradeButtonAdd(
                modifier = Modifier
                    .padding(bottom = 10.dp)
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .padding(innerPadding),
                onClick = {
                    navController.navigate(Screen.AddTrade.createRoute(userId))
                }
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


