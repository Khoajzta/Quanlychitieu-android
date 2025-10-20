import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.quanlychitieu.Components.CardKhoanChi
import com.example.quanlychitieu.Components.CardThuNhap
import com.example.quanlychitieu.Components.CardThuNhapSwipeToDelete
import com.example.quanlychitieu.Components.DotLoading
import com.example.quanlychitieu.Utils.listKhoanChiConst.listKhoanChi
import com.example.quanlychitieu.Utils.thuNhapListSample
import com.example.quanlychitieu.domain.model.KhoanChiModel
import com.example.quanlychitieu.domain.model.ThuNhapModel
import com.example.quanlychitieu.ui.ViewModels.ThuNhapViewModel
import com.example.quanlychitieu.ui.state.UiState

import com.example.quanlychitieu.ui.theme.Dimens.PaddingBody
import com.example.quanlychitieu.ui.theme.Dimens.SpaceMedium
import kotlinx.coroutines.delay
import java.time.LocalDate

@Composable
fun TradeTabPage(
    navController: NavController,
    listKhoanChi: List<KhoanChiModel>,
    listThuNhap: List<ThuNhapModel>,
    userId: Int
) {
    val tabs = listOf("Chi tiêu", "Thu nhập")
    var selectedTabIndex by remember { mutableStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxHeight()
    ) {
        Box(
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .fillMaxWidth()
                .background(
                    color = Color.White.copy(alpha = 0.2f),
                    shape = RoundedCornerShape(25)
                )
                .padding(4.dp)
        ) {
            Row(
                modifier = Modifier.wrapContentWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                tabs.forEachIndexed { index, title ->
                    val isSelected = selectedTabIndex == index
                    val backgroundColor =
                        if (isSelected) Color.White else Color.Transparent
                    val textColor =
                        if (isSelected) Color(0xFF1C94D5) else Color.Black.copy(alpha = 0.8f)

                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .clip(RoundedCornerShape(25))
                            .background(backgroundColor)
                            .clickable { selectedTabIndex = index }
                            .padding(vertical = 8.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = title,
                            color = textColor,
                            fontSize = 15.sp,
                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        AnimatedContent(
            targetState = selectedTabIndex,
            transitionSpec = {
                if (targetState > initialState) {
                    slideInHorizontally { it } + fadeIn() togetherWith slideOutHorizontally { -it } + fadeOut()
                } else {
                    slideInHorizontally { -it } + fadeIn() togetherWith slideOutHorizontally { it } + fadeOut()
                }
            },
            label = ""
        ) { index ->
            when (index) {
                0 -> ChiTieuPage(navController = navController,listKhoanChi = listKhoanChi, userId = userId)
                1 -> ThuNhapPage(userId = userId)
            }
        }

    }
}

// Giả lập nội dung trang Chi tiêu
@Composable
fun ChiTieuPage(
    navController: NavController,
    listKhoanChi: List<KhoanChiModel>,
    userId:Int
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize().padding(horizontal = PaddingBody),
        verticalArrangement = Arrangement.spacedBy(SpaceMedium)
    ) {
        itemsIndexed(listKhoanChi) { index, item ->
            CardKhoanChi(item, modifier = Modifier, onDetailClick = {navController.navigate(
                Screen.KhoanChiDetail.createRoute(
                    id_khoanChi = item.id,
                    userId = userId
                )
            )})
        }
    }
}



@Composable
fun ThuNhapPage(
    userId: Int,
    thuNhapViewModel: ThuNhapViewModel = hiltViewModel()
) {
    val thuNhapState = thuNhapViewModel.thuNhapState
    val deleteThuNhapState = thuNhapViewModel.deleteThuNhapState

    val currentDate = LocalDate.now()
    val currentMonth = currentDate.monthValue
    val currentYear = currentDate.year

    // Tải danh sách thu nhập ban đầu
    LaunchedEffect(userId) {
        thuNhapViewModel.getThuNhapTheoThang(
            userId = userId, thang = currentMonth, nam = currentYear
        )
    }

    // Khi xóa thành công -> load lại danh sách
    LaunchedEffect(deleteThuNhapState) {
        if (deleteThuNhapState is UiState.Success) {
            thuNhapViewModel.getThuNhapTheoThang(
                userId = userId, thang = currentMonth, nam = currentYear
            )
        }
    }

    val listThuNhap = when (thuNhapState) {
        is UiState.Success -> (thuNhapState as UiState.Success<List<ThuNhapModel>>).data
        else -> emptyList()
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = PaddingBody),
        verticalArrangement = Arrangement.spacedBy(SpaceMedium),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (listThuNhap.isEmpty()) {
            item { Text("Chưa có thu nhập nào trong tháng", color = Color.Black) }
        } else {
            items(listThuNhap, key = { it.id }) { item ->
                CardThuNhapSwipeToDelete(
                    thuNhap = item,
                    onDelete = { thuNhap ->
                        thuNhapViewModel.deleteThuNhap(thuNhap.id)
                    }
                )
            }
        }
    }
}




@Composable
@Preview
fun TradeTabPagePreview(){
//    TradeTabPage(listKhoanChi, thuNhapListSample)
//    ThuNhapPage(listThuNhap)
}