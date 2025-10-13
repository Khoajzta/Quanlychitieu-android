import android.content.Context
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.quanlychitieu.Const.listKhoanChiConst.listKhoanChi
import com.example.quanlychitieu.Views.Trade.Components.TradeButtonAdd
import com.example.quanlychitieu.models.ThuNhapModel
import com.example.quanlychitieu.ui.theme.BackgroundColor

@Composable
fun TradeScreen(
    navController: NavController
) {

    val listSoTienDaDung = listOf(300000, 500000, 200000, 200000, 200000)

    val listThuNhap = listOf(
        ThuNhapModel(
            maThuNhap = 0,
            tenThuNhap = "tiền cơm",
            soTien = 1000000,
            maThang = 1,
            ngayThuNhap = "23/09/2025"
        ),
        ThuNhapModel(
            maThuNhap = 0,
            tenThuNhap = "tiền cơm",
            soTien = 1000000,
            maThang = 1,
            ngayThuNhap = "23/09/2025"
        ),
        ThuNhapModel(
            maThuNhap = 0,
            tenThuNhap = "tiền cơm",
            soTien = 1000000,
            maThang = 1,
            ngayThuNhap = "23/09/2025"
        ),
        ThuNhapModel(
            maThuNhap = 0,
            tenThuNhap = "tiền cơm",
            soTien = 1000000,
            maThang = 1,
            ngayThuNhap = "23/09/2025"
        ),
        ThuNhapModel(
            maThuNhap = 0,
            tenThuNhap = "tiền cơm",
            soTien = 1000000,
            maThang = 1,
            ngayThuNhap = "23/09/2025"
        ),
        ThuNhapModel(
            maThuNhap = 0,
            tenThuNhap = "tiền cơm",
            soTien = 1000000,
            maThang = 1,
            ngayThuNhap = "23/09/2025"
        ),
    )

    ChiTieuPage(
        listKhoanChi = listKhoanChi,
    )

    Scaffold(
        containerColor = BackgroundColor,
        topBar = {
            Header(
                navController,
                Modifier.windowInsetsPadding(WindowInsets.statusBars),
                title = "Giao dịch"
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
            TradeTabPage(listKhoanChi, listThuNhap)

            TradeButtonAdd(
                modifier = Modifier
                    .padding(bottom = 10.dp)
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .padding(innerPadding),
                onClick = {
                    navController.navigate(Screen.AddTrade.route)
                }
            )
        }
    }
}

@Composable
@Preview
fun TradeScreenPreview() {
    var navController = rememberNavController()
    TradeScreen(navController)
}


