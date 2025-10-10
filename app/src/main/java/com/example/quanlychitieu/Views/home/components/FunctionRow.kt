import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.quanlychitieu.ui.theme.Dimens.PaddingBody
import com.example.quanlychitieu.ui.theme.Dimens.PaddingMedium
import com.example.quanlychitieu.ui.theme.Dimens.RadiusFull

@Composable
fun CardFunction(
    modifier: Modifier = Modifier,
    title: String,
    gradientColors: List<Color>
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(RadiusFull))
            .background(
                Brush.linearGradient(
                    colors = gradientColors,
                    start = Offset(0f, 0f),
                    end = Offset(300f, 300f)
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            title,
            color = Color.White,
            softWrap = true,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Center,
            fontSize = 12.sp,
            modifier = Modifier.padding(10.dp)
        )
    }
}

@Composable
fun FunctionRow() {
    LazyRow(
        modifier = Modifier.padding(PaddingBody),
        horizontalArrangement = Arrangement.spacedBy(PaddingMedium),
    ) {
        item {
            CardFunction(
                title = "Thống kê trong năm",
                gradientColors = listOf(
                    Color(0xFF4C9AFF),
                    Color(0xFF6BB8FF)
                )
            )
        }
        item {
            CardFunction(
                title = "Xem lịch sử chi tiêu",
                gradientColors = listOf(
                    Color(0xFF9B8CFF),
                    Color(0xFFC6A8FF)
                )
            )
        }
        item {
            CardFunction(
                title = "Thêm khoản chi khoản thu",
                gradientColors = listOf(
                    Color(0xFFFFA96B),
                    Color(0xFFFFC18C)
                )
            )
        }
        item {
            CardFunction(
                title = "Quản lý danh mục",
                gradientColors = listOf(
                    Color(0xFF4CD3C2),
                    Color(0xFF6BE8D8)
                )
            )
        }
    }
}



@Composable
@Preview()
fun PreviewFunctionRow() {
    FunctionRow()
//    CardFunction(modifier = Modifier, title = "Thống kê trong năm")
}