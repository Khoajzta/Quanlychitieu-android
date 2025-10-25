import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.quanlychitieu.Components.CustomButton
import com.example.quanlychitieu.Components.DotLoading
import com.example.quanlychitieu.domain.model.TaiKhoanModel
import com.example.quanlychitieu.ui.ViewModels.TaiKhoanViewModel
import com.example.quanlychitieu.ui.Views.NganSach.components.ChucNangRow
import com.example.quanlychitieu.ui.components.CardTaiKhoanChinh
import com.example.quanlychitieu.ui.components.CardTaiKhoanSwipeToDelete
import com.example.quanlychitieu.ui.components.CardTaikhoanPhu
import com.example.quanlychitieu.ui.components.CustomSnackbar
import com.example.quanlychitieu.ui.components.SnackbarType
import com.example.quanlychitieu.ui.state.UiState
import com.example.quanlychitieu.ui.theme.BackgroundColor
import com.example.quanlychitieu.ui.theme.Dimens.PaddingBody
import com.example.quanlychitieu.ui.theme.Dimens.SpaceMedium
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun NganSachScreen(
    navController: NavController,
    userId: Int,
    taiKhoanViewModel: TaiKhoanViewModel = hiltViewModel()
) {
    // 📌 State từ ViewModel
    val taikhoanUiState by taiKhoanViewModel.uiState.collectAsState()
    val deleteTaiKhoanState by taiKhoanViewModel.deleteKhoanState.collectAsState()
    val updateTaiKhoanState by taiKhoanViewModel.updateTaiKhoanState.collectAsState()

    // 📌 State quản lý snackbar
    var snackbarVisible by remember { mutableStateOf(false) }
    var snackbarType by remember { mutableStateOf(SnackbarType.SUCCESS) }
    var snackbarMessage by remember { mutableStateOf("") }

    // 📌 Refresh state
    val refreshState = rememberPullRefreshState(
        refreshing = taikhoanUiState is UiState.Loading,
        onRefresh = { taiKhoanViewModel.loadTaiKhoans(userId) }
    )

    // 📌 Load danh sách tài khoản khi vào màn hình
    LaunchedEffect(userId) {
        taiKhoanViewModel.loadTaiKhoans(userId)
    }

    // 📌 Lấy danh sách tài khoản
    val listTaiKhoan = when (taikhoanUiState) {
        is UiState.Success -> (taikhoanUiState as UiState.Success<List<TaiKhoanModel>>).data
        else -> emptyList()
    }
    val taiKhoanChinh = listTaiKhoan.firstOrNull { it.loai_taikhoan == 1 }

    // 📌 Xử lý khi update tài khoản
    LaunchedEffect(updateTaiKhoanState) {
        when (updateTaiKhoanState) {
            is UiState.Success -> {
                snackbarType = SnackbarType.SUCCESS
                snackbarMessage = "Nạp tiền thành công!"
                snackbarVisible = true
            }
            is UiState.Error -> {
                snackbarType = SnackbarType.ERROR
                snackbarMessage = (updateTaiKhoanState as UiState.Error).message
                snackbarVisible = true
            }
            else -> {}
        }
    }

    // 📌 Xử lý khi xóa tài khoản
    LaunchedEffect(deleteTaiKhoanState) {
        when (deleteTaiKhoanState) {
            is UiState.Success -> {
                snackbarType = SnackbarType.SUCCESS
                snackbarMessage = "Xóa tài khoản thành công"
                snackbarVisible = true
                taiKhoanViewModel.loadTaiKhoans(userId)
            }
            is UiState.Error -> {
                snackbarType = SnackbarType.ERROR
                snackbarMessage = (deleteTaiKhoanState as UiState.Error).message
                snackbarVisible = true
            }
            else -> {}
        }
    }

    // 📌 Tự động ẩn snackbar sau 3s
    LaunchedEffect(snackbarVisible) {
        if (snackbarVisible) {
            delay(3000)
            snackbarVisible = false
        }
    }

    Scaffold(
        containerColor = BackgroundColor,
        topBar = {
            Header(
                navController = navController,
                modifier = Modifier.windowInsetsPadding(WindowInsets.statusBars),
                title = "Ngân sách",
                userId = userId
            )
        },
        contentWindowInsets = WindowInsets(0, 0, 0, 0)
    ) { innerPadding ->

        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .pullRefresh(refreshState),
            contentAlignment = Alignment.Center
        ) {
            // 📌 Danh sách tài khoản
            if (taikhoanUiState is UiState.Success && listTaiKhoan.isNotEmpty()) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = PaddingBody),
                    verticalArrangement = Arrangement.spacedBy(SpaceMedium),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    taiKhoanChinh?.let {
                        item { CardTaiKhoanChinh(taikhoan = it) }
                    }

                    item {
                        ChucNangRow(
                            modifier = Modifier,
                            navController = navController,
                            userId = userId
                        )
                    }

                    items(listTaiKhoan.filter { it.loai_taikhoan == 0 }) { taiKhoan ->
                        CardTaiKhoanSwipeToDelete(
                            taikhoan = taiKhoan,
                            onDelete = { taiKhoanViewModel.deleteTaiKhoan(it.id) }
                        )
                    }

                    item { Spacer(modifier = Modifier.height(50.dp)) }
                }
            } else if (taikhoanUiState is UiState.Loading) {
                DotLoading(modifier = Modifier.align(Alignment.Center))
            }

            // 📌 Hiệu ứng refresh
            PullRefreshIndicator(
                refreshing = taikhoanUiState is UiState.Loading,
                state = refreshState,
                modifier = Modifier.align(Alignment.TopCenter)
            )

            // 📌 Snackbar
            AnimatedVisibility(
                visible = snackbarVisible,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 16.dp),
                enter = slideInVertically { it } + fadeIn(),
                exit = slideOutVertically { it } + fadeOut()
            ) {
                CustomSnackbar(
                    message = snackbarMessage,
                    type = snackbarType
                )
            }
        }
    }
}


@Composable
@Preview
fun NganSachScreenPreview(){
    var navController = rememberNavController()
    NganSachScreen(navController,1)
}