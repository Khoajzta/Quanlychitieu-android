import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.quanlychitieu.Components.CardKhoanChi
import com.example.quanlychitieu.models.KhoanChiModel
import com.example.quanlychitieu.ui.theme.Dimens.PaddingBody


@Composable
fun KhoanChiColumn(
    listKhoanChi: List<KhoanChiModel>,
    listSoTienDaDung: List<Int>, // danh sách số tiền đã dùng tương ứng
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = PaddingBody),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        for (i in listKhoanChi.indices) {
            CardKhoanChi(
                expenseItem = listKhoanChi[i],
                amountSpent = listSoTienDaDung.getOrElse(i) { 0 }, // lấy số tiền đã dùng hoặc 0 nếu không có
                modifier = Modifier
            )
        }
    }
}

@Preview
@Composable
fun PreviewCardKhoanChi() {

    var listKhoanChi = listOf(
        KhoanChiModel(1, "Ăn uống", 3000000, 12, 100, "blue","🍕"),
        KhoanChiModel(2, "Mua sắm", 2000000, 5, 101, "red","🍕"),
        KhoanChiModel(3, "Giải trí", 1500000, 3, 102, "green","🍕")
    )

    var listSoTienDaDung = listOf(300000, 500000, 200000)
    KhoanChiColumn(
        listKhoanChi,
        listSoTienDaDung
    )

}