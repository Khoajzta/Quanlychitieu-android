import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.quanlychitieu.Components.BouncingDotsLoader
import com.example.quanlychitieu.Utils.listKhoanChiConst.listKhoanChi
import com.example.quanlychitieu.ViewModels.KhoanChiViewModel
import com.example.quanlychitieu.Views.AddTrade.AddTradeTab
import com.example.quanlychitieu.domain.model.KhoanChiModel
import com.example.quanlychitieu.domain.model.TaiKhoanModel
import com.example.quanlychitieu.ui.ViewModels.TaiKhoanViewModel
import com.example.quanlychitieu.ui.state.UiState
import com.example.quanlychitieu.ui.theme.BackgroundColor
import kotlinx.coroutines.delay

@Composable
fun AddTradeScreen(
    navController: NavController,
    userId :Int,
    khoanChiViewModel: KhoanChiViewModel = hiltViewModel(),
    taiKhoanViewModel: TaiKhoanViewModel = hiltViewModel()
) {

    val KhoanChiuiState by khoanChiViewModel.uiState.collectAsState()
    val taiKhoanUiState by taiKhoanViewModel.uiState.collectAsState()

    Log.d("userId", userId.toString())

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

    val taiKhoanChinh: TaiKhoanModel? = taikhoanList.firstOrNull { it.loai_taikhoan == 1 }


    Scaffold(
        containerColor = BackgroundColor,
        topBar = {
            Header(
                navController,
                Modifier.windowInsetsPadding(WindowInsets.statusBars),
                title = "Thêm giao dịch"
            )
        },
        contentWindowInsets = WindowInsets(0, 0, 0, 0)
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if(KhoanChiuiState is UiState.Success && taiKhoanUiState is UiState.Success){
                AddTradeTab(khoanChiList, taikhoanchinh = taiKhoanChinh!!, userId)
            }else{
                BouncingDotsLoader()
            }

        }
    }
}

@Composable
@Preview
fun AddTradeScreenPreview (){
//    var navController = rememberNavController()
//
//    AddTradeScreen(navController,listKhoanChi)
}
