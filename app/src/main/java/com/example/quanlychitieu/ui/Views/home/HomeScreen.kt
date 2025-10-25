import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import com.example.quanlychitieu.Utils.tinhTongTheoTuanVaNgay
import com.example.quanlychitieu.ViewModels.KhoanChiViewModel
import com.example.quanlychitieu.Views.home.components.BottomNavigationBar
import com.example.quanlychitieu.Views.home.components.HeaderMain
import com.example.quanlychitieu.Views.home.components.WeeklyFinanceBarChart
import com.example.quanlychitieu.domain.model.ChiTieuModel
import com.example.quanlychitieu.domain.model.KhoanChiModel
import com.example.quanlychitieu.domain.model.TaiKhoanModel
import com.example.quanlychitieu.domain.model.ThuNhapModel
import com.example.quanlychitieu.ui.ViewModels.ChiTieuViewModel
import com.example.quanlychitieu.ui.ViewModels.NguoiDungViewModel
import com.example.quanlychitieu.ui.ViewModels.TaiKhoanViewModel
import com.example.quanlychitieu.ui.ViewModels.ThuNhapViewModel
import com.example.quanlychitieu.ui.Views.home.components.BarChartColumn
import com.example.quanlychitieu.ui.Views.home.components.CardTaiKhoanRow
import com.example.quanlychitieu.ui.Views.home.components.HomeChiTieuColumn
import com.example.quanlychitieu.ui.Views.home.components.HomeThuNhapColumn
import com.example.quanlychitieu.ui.state.UiState
import com.example.quanlychitieu.ui.theme.BackgroundColor
import com.example.quanlychitieu.ui.theme.Dimens.PaddingBody
import com.example.quanlychitieu.ui.theme.Dimens.SpaceMedium
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeScreen(
    userId: Int,
    navController: NavController,
    khoanChiViewModel: KhoanChiViewModel = hiltViewModel(),
    taiKhoanViewModel: TaiKhoanViewModel = hiltViewModel(),
    nguoidungViewModel: NguoiDungViewModel = hiltViewModel(),
    thuNhapViewModel: ThuNhapViewModel = hiltViewModel(),
    chiTieuViewModel: ChiTieuViewModel = hiltViewModel()
) {
    //========================= STATES ==========================================
    val khoanChiState by khoanChiViewModel.uiState.collectAsState()
    val taiKhoanState by taiKhoanViewModel.uiState.collectAsState()
    val thuNhapState by thuNhapViewModel.uiState.collectAsState()
    val chiTieuState by chiTieuViewModel.uiStateTheoThang.collectAsState()
    val nguoiDungState = nguoidungViewModel.getByIdState

    val currentDate = LocalDate.now()
    val currentMonth = currentDate.monthValue
    val currentYear = currentDate.year

    //========================= TẢI DỮ LIỆU =====================================
    LaunchedEffect(userId) {
        if (userId > 0) {
            suspend fun loadAll() {
                khoanChiViewModel.loadKhoanChi(userId)
                taiKhoanViewModel.loadTaiKhoans(userId)
                nguoidungViewModel.getNguoiDungByID(userId)
                thuNhapViewModel.getThuNhapTheoThang(userId, currentMonth, currentYear)
                chiTieuViewModel.getChiTieuTheoThangVaNam(userId, currentMonth, currentYear)
            }

            loadAll() // Lần đầu
            while (true) {
                delay(15 * 60 * 1000L)
                loadAll()
            }
        }
    }

    //========================= CHUYỂN UI STATE SANG LIST =======================
    val khoanChiList = (khoanChiState as? UiState.Success)?.data ?: emptyList()
    val taiKhoanList = (taiKhoanState as? UiState.Success)?.data ?: emptyList()
    val thuNhapList = ((thuNhapState as? UiState.Success)?.data ?: emptyList())
        .sortedByDescending { it.ngay_tao }
        .take(5)

    val chiTieuList = ((chiTieuState as? UiState.Success)?.data ?: emptyList())
        .sortedByDescending { it.ngay_tao }
        .take(5)


    val tongThuNhap = thuNhapList.sumOf { it.so_tien }
    val tongChiTieu = chiTieuList.sumOf { it.so_tien }
    val tongTienDuKien = khoanChiList.sumOf { it.so_tien_du_kien }
    val (data, dates) = tinhTongTheoTuanVaNgay(chiTieuList, thuNhapList)

    //========================= REFRESH =========================================
    var isRefreshing by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()

    val refreshState = rememberPullRefreshState(
        refreshing = isRefreshing,
        onRefresh = {
            coroutineScope.launch {
                isRefreshing = true
                khoanChiViewModel.loadKhoanChi(userId)
                taiKhoanViewModel.loadTaiKhoans(userId)
                delay(500)
                isRefreshing = false
            }
        }
    )

    //========================= HEADER ẨN HIỆN ==================================
    val listState = rememberLazyListState()
    var previousOffset by remember { mutableStateOf(0) }
    var targetHeight by remember { mutableStateOf(100.dp) }

    LaunchedEffect(listState.firstVisibleItemScrollOffset) {
        val currentOffset = listState.firstVisibleItemScrollOffset
        if (currentOffset > previousOffset + 5) targetHeight = 0.dp
        else if (currentOffset < previousOffset - 5) targetHeight = 100.dp
        previousOffset = currentOffset
    }

    val animatedHeight by animateDpAsState(targetHeight, label = "")

    //========================= GIAO DIỆN =======================================
    Scaffold(
        containerColor = BackgroundColor,
        topBar = {
            AnimatedVisibility(visible = animatedHeight > 0.dp) {
                Box(
                    Modifier
                        .height(animatedHeight)
                        .fillMaxWidth()
                        .windowInsetsPadding(WindowInsets.statusBars)
                ) {
                    when (nguoiDungState) {
                        is UiState.Success ->
                            HeaderMain(Modifier.fillMaxSize(), user = nguoiDungState.data.data!!)
                        is UiState.Error ->
                            Text("Lỗi: ${nguoiDungState.message}")
                        else -> {}
                    }
                }
            }
        },
        bottomBar = {
            BottomNavigationBar(
                navController = navController,
                modifier = Modifier.windowInsetsPadding(WindowInsets.navigationBars),
                userId = userId
            )
        },
        contentWindowInsets = WindowInsets(0, 0, 0, 0)
    ) { innerPadding ->

        Box(
            Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .pullRefresh(refreshState)
                .padding(PaddingBody),
            contentAlignment = Alignment.Center
        ) {
            val allLoaded =
                khoanChiState is UiState.Success &&
                        taiKhoanState is UiState.Success &&
                        thuNhapState is UiState.Success &&
                        chiTieuState is UiState.Success

            if (allLoaded) {
                LazyColumn(
                    state = listState,
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color(0xFFC7E6F6)),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(SpaceMedium)
                ) {
                    // Tổng quan tài khoản
                    item {
                        CardTaiKhoanRow(
                            listTaiKhoan = taiKhoanList,
                            tongTienDuKien = tongTienDuKien,
                            tongThuNhap = tongThuNhap,
                            tongChiTieu = tongChiTieu
                        )
                    }

                    item { FunctionRow() }

                    // Biểu đồ thống kê
                    if (thuNhapList.isNotEmpty() || chiTieuList.isNotEmpty()) {
                        item {
                            BarChartColumn(data = data, dates = dates)
                        }
                    }

                    // Khoản chi
                    item {
                        if (khoanChiList.isEmpty()) {
                            CustomButton(
                                title = "Thêm khoản chi",
                                icon = Icons.Default.AddCircle,
                                onClick = { navController.navigate(Screen.AddKhoanChi.createRoute(userId)) },
                                modifier = Modifier.fillMaxWidth()
                            )
                        } else {
                            KhoanChiMoreRow(navController = navController, userId = userId)
                            KhoanChiColumn(khoanChiList)
                        }
                    }

                    // Thu nhập
                    if (thuNhapList.isNotEmpty()) {
                        item { HomeThuNhapColumn(thuNhapList) }
                    }

                    // Chi tiêu
                    if (chiTieuList.isNotEmpty()) {
                        item { HomeChiTieuColumn(chiTieuList) }
                    }

                    item { Spacer(Modifier.height(200.dp)) }
                }

                PullRefreshIndicator(
                    refreshing = isRefreshing,
                    state = refreshState,
                    modifier = Modifier
                        .padding(top = 40.dp)
                        .align(Alignment.TopCenter)
                )
            } else {
                DotLoading(Modifier.align(Alignment.Center))
            }
        }
    }
}


@Composable
@Preview
fun PreviewMainScreen() {
    var navController = rememberNavController()
    HomeScreen(
        navController = navController,
        userId = 21,
    )
}