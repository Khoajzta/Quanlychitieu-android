import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.quanlychitieu.Views.AddTrade.AddTradeTab
import com.example.quanlychitieu.domain.model.KhoanChiModel
import com.example.quanlychitieu.ui.theme.BackgroundColor

@Composable
fun AddTradeScreen(
    navController: NavController,
    listKhoanChi: List<KhoanChiModel>,
) {
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
                .padding(innerPadding)
        ) {
            AddTradeTab(listKhoanChi)
        }
    }
}

@Composable
@Preview
fun AddTradeScreenPreview (){
    var navController = rememberNavController()

    val listKhoanChi = listOf(
        KhoanChiModel(
            id = 1,
            ten_khoanchi ="Tiền ăn",
            id_nguoidung = 1,
            mausac = "yellow",
            ngay_batdau = "16-02-2025",
            ngay_ketthuc = "16-02-2025",
            so_tien_du_kien = 3000000,
            tong_tien_da_chi = 200000,
            emoji = "🤣"
        ),
        KhoanChiModel(
            id = 1,
            ten_khoanchi ="Tiền ăn",
            id_nguoidung = 1,
            mausac = "yellow",
            ngay_batdau = "16-02-2025",
            ngay_ketthuc = "16-02-2025",
            so_tien_du_kien = 3000000,
            tong_tien_da_chi = 200000,
            emoji = "🤣"
        ),
        KhoanChiModel(
            id = 1,
            ten_khoanchi ="Tiền ăn",
            id_nguoidung = 1,
            mausac = "yellow",
            ngay_batdau = "16-02-2025",
            ngay_ketthuc = "16-02-2025",
            so_tien_du_kien = 3000000,
            tong_tien_da_chi = 200000,
            emoji = "🤣"
        ),
        KhoanChiModel(
            id = 1,
            ten_khoanchi ="Tiền ăn",
            id_nguoidung = 1,
            mausac = "yellow",
            ngay_batdau = "16-02-2025",
            ngay_ketthuc = "16-02-2025",
            so_tien_du_kien = 3000000,
            tong_tien_da_chi = 200000,
            emoji = "🤣"
        ),
        KhoanChiModel(
            id = 1,
            ten_khoanchi ="Tiền ăn",
            id_nguoidung = 1,
            mausac = "yellow",
            ngay_batdau = "16-02-2025",
            ngay_ketthuc = "16-02-2025",
            so_tien_du_kien = 3000000,
            tong_tien_da_chi = 200000,
            emoji = "🤣"
        ),

        )

    AddTradeScreen(navController,listKhoanChi)
}
