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
import com.example.quanlychitieu.Components.BouncingDotsLoader
import com.example.quanlychitieu.ViewModels.KhoanChiViewModel
import com.example.quanlychitieu.Views.home.components.BottomNavigationBar
import com.example.quanlychitieu.Views.home.components.HeaderMain
import com.example.quanlychitieu.Views.home.components.WeeklyFinanceBarChart
import com.example.quanlychitieu.domain.model.KhoanChiModel
import com.example.quanlychitieu.ui.state.UiState
import com.example.quanlychitieu.ui.theme.BackgroundColor
import com.example.quanlychitieu.ui.theme.Dimens.PaddingBody
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeScreen(
    userId: Int,
    navController: NavController,
    viewModel: KhoanChiViewModel = hiltViewModel()
) {

    val uiState by viewModel.uiState.collectAsState()


    LaunchedEffect(Unit) {
        while (true) {
            viewModel.loadKhoanChi(userId)
            delay(15 * 60 * 1000L)
        }
    }

    val khoanChiList = when (uiState) {
        is UiState.Success -> (uiState as UiState.Success<List<KhoanChiModel>>).data
        else -> emptyList()
    }

    //Refresh dữ liệu
    var isRefreshing by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()

    val refreshState = rememberPullRefreshState(
        refreshing = isRefreshing,
        onRefresh = {
            isRefreshing = true
            coroutineScope.launch {
                viewModel.loadKhoanChi(userId)
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
                    HeaderMain(Modifier.fillMaxSize())
                }
            }
        },
        bottomBar = {
            BottomNavigationBar(navController = navController,Modifier.windowInsetsPadding(WindowInsets.navigationBars))
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
                item { Spacer(modifier = Modifier.height(10.dp)) }

                item { HomeTotalMoney() }

                item { FunctionRow() }

                item {
                    val weeklyData = mapOf(
                        "T2" to 200000,
                        "T3" to -80000,
                        "T4" to 200000,
                        "T5" to 50000,
                        "T6" to -50000,
                        "T7" to -50000,
                        "CN" to 50000,
                    )
                    val weekDates = listOf("07/10", "08/10", "09/10", "10/10", "11/10", "12/10", "13/10")

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
                        WeeklyFinanceBarChart(data = weeklyData, dates = weekDates)
                    }
                }

                item {
                    KhoanChiMoreRow(modifier = Modifier, navController = navController)
                    if (uiState is UiState.Loading) {
                        BouncingDotsLoader()
                    } else {
                        KhoanChiColumn(khoanChiList)
                    }
                }

                item {
                    Spacer(modifier = Modifier.height(200.dp))
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
//    var navController = rememberNavController()
//    HomeScreen(navController = navController)
}