import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.quanlychitieu.Views.home.components.BottomNavigationBar
import com.example.quanlychitieu.Views.home.components.HeaderMain
import com.example.quanlychitieu.Views.home.components.WeeklyFinanceBarChart

import com.example.quanlychitieu.models.KhoanChiModel
import com.example.quanlychitieu.ui.theme.BackgroundColor
import com.example.quanlychitieu.ui.theme.Dimens.PaddingBody

@Composable
fun HomeScreen(
    navController: NavController
) {
    val listState = rememberLazyListState()
    var previousOffset by remember { mutableStateOf(0) }
    var isAppBarVisible by remember { mutableStateOf(true) }

    LaunchedEffect(listState.firstVisibleItemScrollOffset) {
        val currentOffset = listState.firstVisibleItemScrollOffset
        isAppBarVisible = currentOffset <= previousOffset || currentOffset < 10 // Lên hoặc gần top thì hiện lại
        previousOffset = currentOffset
    }

    Scaffold(
        containerColor = BackgroundColor,
        topBar = {
//            AnimatedVisibility(
//                visible = isAppBarVisible,
//                enter = slideInVertically { -it } ,
//                exit = slideOutVertically { -it } + fadeOut()
//            ) {
//                HeaderMain(
//                    Modifier.windowInsetsPadding(WindowInsets.statusBars)
//                )
//            }

            HeaderMain(
                Modifier.windowInsetsPadding(WindowInsets.statusBars)
            )
        },
        bottomBar = {
            BottomNavigationBar(navController = navController,Modifier.windowInsetsPadding(WindowInsets.navigationBars))
        },
        contentWindowInsets = WindowInsets(0, 0, 0, 0)


    ) { innerPadding ->
        LazyColumn(
            state = listState,
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFC7E6F6))
                .padding(
                    top = innerPadding.calculateTopPadding()
                )
        ) {
            item { Spacer(modifier = Modifier.height(10.dp)) }
            item { HomeTotalMoney() }
            item { FunctionRow() }
            item {

            }
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
                val listKhoanChi = listOf(
                    KhoanChiModel(1, "Ăn uống", 3000000, 12, 100, "blue"),
                    KhoanChiModel(2, "Mua sắm", 2000000, 5, 101, "red"),
                    KhoanChiModel(3, "Giải trí", 1500000, 3, 102, "green"),
                    KhoanChiModel(4, "Du lịch", 2500000, 2, 103, "orange"),
                    KhoanChiModel(5, "Giáo dục", 1000000, 1, 104, "purple")
                )
                val listSoTienDaDung = listOf(300000, 500000, 200000, 200000, 200000)

                KhoanChiColumn(
                    listKhoanChi,
                    listSoTienDaDung
                )
            }

            item {
                Spacer(modifier = Modifier.height(110.dp))
            }
        }
    }
}


@Composable
@Preview
fun PreviewMainScreen() {
//    var navController = NavHostController
//    MainScreen(navController = navController)
}