import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.navigation.NavController
import com.example.quanlychitieu.ui.theme.Dimens.PaddingBody
import com.example.quanlychitieu.ui.theme.Dimens.PaddingMedium
import com.example.quanlychitieu.ui.theme.Dimens.RadiusFull

@Composable
fun CardFunction(
    modifier: Modifier = Modifier,
    title: String,
    gradientColors: List<Color>,
    onClick: () -> Unit = {}
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
            )
            .clickable { onClick() },
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
fun FunctionRow(
    navController: NavController,
    userId: Int
) {
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(PaddingMedium),
    ) {
        item {
            CardFunction(
                title = "Thống kê trong năm",
                gradientColors = listOf(
                    Color(0xFF4C9AFF),
                    Color(0xFF6BB8FF)
                ),
                onClick = {
                    navController.navigate(Screen.ThongKeNam.createRoute(userId = userId))
                }
            )
        }
    }
}



@Composable
@Preview()
fun PreviewFunctionRow() {

}