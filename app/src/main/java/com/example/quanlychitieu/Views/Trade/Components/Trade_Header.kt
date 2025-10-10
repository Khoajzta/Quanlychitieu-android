import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.quanlychitieu.ui.theme.BackgroundColor
import com.example.quanlychitieu.ui.theme.Dimens.PaddingBody
import com.example.quanlychitieu.ui.theme.Dimens.RadiusFull
import com.example.quanlychitieu.ui.theme.PrimaryColor

@Composable
fun TradeHeader(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(PaddingBody),
        verticalAlignment = Alignment.CenterVertically
    ) {
        ButtonBack(navController, iconColor = Color.Black)

        Spacer(modifier = Modifier.width(10.dp))

        Text(
            text = "Giao dịch",
            fontSize = 17.sp,
            color = Color.Black,
            modifier = Modifier.padding(vertical = 5.dp)
        )

        Spacer(modifier = Modifier.weight(1f))

        IconButton(onClick = { /* TODO: xử lý điều hướng về home */ }) {
            Icon(
                imageVector = Icons.Outlined.Home,
                contentDescription = "Home",
                tint = Color.Black,
                modifier = Modifier.size(22.dp)
            )
        }
    }
}


@Composable
@Preview()
fun TradeHeaderPreview(){
    var navController = rememberNavController()
    TradeHeader(navController)
}