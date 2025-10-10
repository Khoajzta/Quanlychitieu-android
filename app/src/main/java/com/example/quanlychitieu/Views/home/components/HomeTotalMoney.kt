import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.quanlychitieu.ui.theme.Dimens.RadiusXL

@Composable
fun HomeTotalMoney() {
    Box(
        modifier = Modifier
            .padding(horizontal = 12.dp)
            .height(200.dp)
            .fillMaxWidth()
            .shadow(12.dp, RoundedCornerShape(RadiusXL))
            .clip(RoundedCornerShape(RadiusXL))
            .background(Brush.horizontalGradient(
                colors = listOf(Color(0xFF73B5E1),Color(0xFF753a88))
            ))
    ){
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp)
        ) {
            Text(
                "Tổng số dư",
                lineHeight = 15.sp,
                color = Color.White.copy(0.6f)
            )

            Spacer(modifier = Modifier.height(8.dp))
            Text(
                "3.000.000đ",
                fontSize = 30.sp,
                lineHeight = 15.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )



            Text(
                "Khoản chi dự kiến",
                lineHeight = 15.sp,
                color = Color.White.copy(0.6f)
            )

            Spacer(modifier = Modifier.height(8.dp))
            Text(
                "2.000.000đ",
                fontSize = 20.sp,
                lineHeight = 15.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }


        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .align(Alignment.BottomCenter)
                .background(Brush.horizontalGradient(
                    colors = listOf(Color(0xFFA0C7E1),Color(0xFFB461CC))
                ))
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 15.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    modifier = Modifier.fillMaxHeight().weight(1f),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text("Tổng thu nhập:", fontSize = 12.sp, lineHeight = 20.sp, color = Color.White.copy(0.6f))
                    Text("100.000đ", fontSize = 17.sp, lineHeight = 15.sp, color = Color.White)
                }
                Spacer(modifier = Modifier.width(30.dp))
                Column(
                    modifier = Modifier.fillMaxHeight().weight(1f),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text("Tổng đã chi:", fontSize = 12.sp, lineHeight = 20.sp, color = Color.White.copy(0.6f))
                    Text("100.000đ", fontSize = 17.sp, lineHeight = 15.sp, color = Color.White)
                }
            }
        }
    }
}

@Composable
@Preview
fun PreviewHomeTotalMoney() {
    HomeTotalMoney()
}