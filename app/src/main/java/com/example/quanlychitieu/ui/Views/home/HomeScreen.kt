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
import com.example.quanlychitieu.ui.Views.home.components.CardTaiKhoanRow
import com.example.quanlychitieu.ui.Views.home.components.HomeChiTieuColumn
import com.example.quanlychitieu.ui.Views.home.components.HomeThuNhapColumn
import com.example.quanlychitieu.ui.state.UiState
import com.example.quanlychitieu.ui.theme.BackgroundColor
import com.example.quanlychitieu.ui.theme.Dimens.PaddingBody
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeScreen(
    userId: Int,
    navController: NavController,
    khoanchiViewModel: KhoanChiViewModel = hiltViewModel(),
    taikhoanViewModel : TaiKhoanViewModel = hiltViewModel(),
    nguoidungViewModel: NguoiDungViewModel = hiltViewModel(),
    thunhapViewModel: ThuNhapViewModel = hiltViewModel(),
    chitieuViewModel: ChiTieuViewModel = hiltViewModel()
) {

    val KhoanChiuiState by khoanchiViewModel.uiState.collectAsState()
    val taiKhoanUiState by taikhoanViewModel.uiState.collectAsState()
    val thunhapUiState by thunhapViewModel.uiState.collectAsState()
    val chitieuUiState by chitieuViewModel.uiStateTheoThang.collectAsState()

    val getNguoiDungByIdState = nguoidungViewModel.getByIdState

    val currentDate = LocalDate.now()
    val currentMonth = currentDate.monthValue
    val currentYear = currentDate.year

    LaunchedEffect(userId) {
        if (userId > 0) {
            while (true) {
                khoanchiViewModel.loadKhoanChi(userId)
                taikhoanViewModel.loadTaiKhoans(userId)
                nguoidungViewModel.getNguoiDungByID(userId)
                thunhapViewModel.getThuNhapTheoThang(userId,currentMonth, currentYear)
                chitieuViewModel.getChiTieuTheoThangVaNam(userId,currentMonth,currentYear)
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

    val thunhaplist = when(thunhapUiState){
        is UiState.Success -> (thunhapUiState as UiState.Success<List<ThuNhapModel>>).data
        else -> {emptyList()}
    }

    val chitieulist = when(chitieuUiState){
        is UiState.Success -> (chitieuUiState as UiState.Success<List<ChiTieuModel>>).data
        else -> {emptyList()}
    }

    val tongThuNhap = thunhaplist.sumOf { it.so_tien }
    val tongChiTieu = chitieulist.sumOf { it.so_tien }

    val tongSoTienDuKien = khoanChiList.sumOf { it.so_tien_du_kien }

    val (data, dates) = tinhTongTheoTuanVaNgay(chitieulist, thunhaplist)


    //Refresh dữ liệu
    var isRefreshing by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()

    val refreshState = rememberPullRefreshState(
        refreshing = isRefreshing,
        onRefresh = {
            isRefreshing = true
            coroutineScope.launch {
                khoanchiViewModel.loadKhoanChi(userId)
                taikhoanViewModel.loadTaiKhoans(userId)
                isRefreshing = false
            }
        }
    )
    //==============================================================================
    // ẩn header
    val listState = rememberLazyListState()
    var previousOffset by remember { mutableStateOf(0) }
    var targetHeight by remember { mutableStateOf(100.dp) }

    LaunchedEffect(listState.firstVisibleItemScrollOffset) {
        val currentOffset = listState.firstVisibleItemScrollOffset
        if (currentOffset > previousOffset + 5) {
            targetHeight = 0.dp
        } else if (currentOffset < previousOffset - 5) {
            targetHeight = 100.dp
        }
        previousOffset = currentOffset
    }

    val animatedHeight by animateDpAsState(targetValue = targetHeight)
    //==============================================================================

    Scaffold(
        containerColor = BackgroundColor,
        topBar = {
            Box(
                modifier = Modifier
                    .height(animatedHeight)
                    .fillMaxWidth()
                    .windowInsetsPadding(WindowInsets.statusBars)
            ) {
                if (animatedHeight > 0.dp) {
                    when(getNguoiDungByIdState){
                        is UiState.Success -> {
                            HeaderMain(Modifier.fillMaxSize(), user = getNguoiDungByIdState.data.data!!)
                        }
                        is UiState.Error -> {
                            Text("lỗi ${getNguoiDungByIdState.message}")
                        }
                        else -> {

                        }
                    }

                }
            }
        },
        bottomBar = {
            BottomNavigationBar(
                navController = navController,Modifier.windowInsetsPadding(WindowInsets.navigationBars),
                userId
            )
        },
        contentWindowInsets = WindowInsets(0, 0, 0, 0)


    ) { innerPadding ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .pullRefresh(refreshState)
        ){
            LazyColumn(
                state = listState,
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFFC7E6F6))
                    .padding(
                        top = innerPadding.calculateTopPadding()
                    ),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                if(KhoanChiuiState is UiState.Success && taiKhoanUiState is UiState.Success){
                    item { Spacer(modifier = Modifier.height(10.dp)) }

                    item { 
                        CardTaiKhoanRow(
                            listTaiKhoan = taikhoanList,
                            tongTienDuKien = tongSoTienDuKien,
                            tongThuNhap = tongThuNhap,
                            tongChiTieu = tongChiTieu
                        )
                    }

                    item { FunctionRow() }

                    item {

                        Column(
                            modifier = Modifier
                                .padding(PaddingBody)
                        ) {
                            Text(
                                "Thống kê trong tuần",
                                fontWeight = FontWeight.Bold,
                                color = Color.Black,
                                fontSize = 15.sp
                            )
                            Spacer(modifier = Modifier.height(10.dp))

                            WeeklyFinanceBarChart(
                                data = data,
                                dates = dates
                            )

                        }
                    }

                    item {
                        KhoanChiMoreRow(
                            modifier = Modifier,
                            navController = navController,
                            userId = userId
                        )
                        KhoanChiColumn(khoanChiList)
                    }
                    item {
                        Spacer(modifier = Modifier.height(10.dp))
                    }

                    item {
                        HomeThuNhapColumn(listThuNhap = thunhaplist)
                    }

                    item {
                        Spacer(modifier = Modifier.height(10.dp))
                    }

                    item {
                        HomeChiTieuColumn(listChiTieu = chitieulist)
                    }

                    item {
                        Spacer(modifier = Modifier.height(200.dp))
                    }
                }else{
                    item {
                        DotLoading()
                    }
                }

            }

            PullRefreshIndicator(
                refreshing = isRefreshing,
                state = refreshState,
                modifier = Modifier.padding(top = 20.dp).align(Alignment.TopCenter)
            )
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
        taikhoanViewModel = hiltViewModel(),
        nguoidungViewModel = hiltViewModel()
    )
}