import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.DropdownMenu
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.quanlychitieu.Views.AddTrade.AddTradeTab
import com.example.quanlychitieu.models.KhoanChiModel
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

    var listKhoanChi = listOf(
        KhoanChiModel(1, "Ăn uống", 3000000, 12, 100, "blue","🍕"),
        KhoanChiModel(2, "Mua sắm", 2000000, 5, 101, "red","😍"),
        KhoanChiModel(3, "Giải trí", 1500000, 3, 102, "green","")
    )

    AddTradeScreen(navController,listKhoanChi)
}
