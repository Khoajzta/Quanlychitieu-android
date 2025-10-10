import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.quanlychitieu.models.KhoanChiModel
import com.example.quanlychitieu.ui.theme.Dimens.PaddingBody
import com.example.quanlychitieu.ui.theme.Dimens.RadiusLarge
import com.example.quanlychitieu.ui.theme.Dimens.RadiusXL

@Composable
fun CardKhoanChi(
    item: KhoanChiModel,
    soTienDaDung: Int, // thêm tham số này
    modifier: Modifier = Modifier
) {
    val percentUsed = remember(item.soTienDuKien, soTienDaDung) {
        (soTienDaDung.toFloat() / item.soTienDuKien.toFloat()).coerceIn(0f, 1f)
    }

    var gradientColor =
        when(item.mausac) {
            "red" -> listOf(Color(0xFFE57373),Color(0xFFF06292).copy(alpha = 0.35f))
            "blue" -> listOf(Color(0xFF64B5F6),Color(0xFF4FC3F7).copy(alpha = 0.35f))
            "green" -> listOf(Color(0xFF81C784),Color(0xFF4DB6AC).copy(alpha = 0.35f))
            "yellow" -> listOf(Color(0xFFFFB74D),Color(0xFFFF8A65).copy(alpha = 0.35f))
            else -> listOf(Color(0xFFA0C7E1),Color(0xFFB461CC).copy(alpha = 0.35f))
        }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(120.dp)
            .clip(RoundedCornerShape(RadiusXL))
            .background(Brush.horizontalGradient(colors = gradientColor))
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start
        ) {

            Box(
                modifier = Modifier
                    .width(130.dp)
                    .clip(RoundedCornerShape(RadiusLarge))
                    .background(color = Color.White),
                contentAlignment = Alignment.Center
            ){
                Text(
                    text = item.tenKhoanChi,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    lineHeight = 20.sp,
                    modifier = Modifier.padding(10.dp)
                )
            }

            
            Spacer(modifier = Modifier.height(10.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth(),

                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Dự kiến: ${formatCurrency(item.soTienDuKien)}",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.White.copy(alpha = 0.9f)
                )


                Text(
                    text = "Đã dùng: ${formatCurrency(soTienDaDung)}",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.White.copy(alpha = 0.9f)
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(10.dp)
                    .clip(RoundedCornerShape(50))
                    .background(Color.White.copy(alpha = 0.2f))
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(percentUsed)
                        .fillMaxHeight()
                        .background(
                            Brush.horizontalGradient(
                                colors = listOf(
                                    Color(0xFFFFC371),
                                    Color(0xFFFF5F6D)
                                )
                            )
                        )
                )
            }
        }
    }
}

@Composable
fun KhoanChiColumn(
    listKhoanChi: List<KhoanChiModel>,
    listSoTienDaDung: List<Int>, // danh sách số tiền đã dùng tương ứng
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(PaddingBody),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        for (i in listKhoanChi.indices) {
            CardKhoanChi(
                item = listKhoanChi[i],
                soTienDaDung = listSoTienDaDung.getOrElse(i) { 0 }, // lấy số tiền đã dùng hoặc 0 nếu không có
                modifier = Modifier
            )
        }
    }
}

@Preview
@Composable
fun PreviewCardKhoanChi() {

    var listKhoanChi = listOf(
        KhoanChiModel(1, "Ăn uống", 3000000, 12, 100, "blue"),
        KhoanChiModel(2, "Mua sắm", 2000000, 5, 101, "red"),
        KhoanChiModel(3, "Giải trí", 1500000, 3, 102, "green")
    )

    var listSoTienDaDung = listOf(300000, 500000, 200000)
    KhoanChiColumn(
        listKhoanChi,
        listSoTienDaDung
    )

}