import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.quanlychitieu.ui.theme.Dimens.PaddingBody
import com.example.quanlychitieu.ui.theme.PrimaryColor
import com.google.android.recaptcha.internal.zzhp

@Composable
fun KhoanChiMoreRow(
    modifier: Modifier = Modifier,
    navController: NavController,
    userId :Int
){
    Row(
        modifier = modifier
            .wrapContentHeight()
            .padding(horizontal = PaddingBody)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Các khoản hay chi tiêu",
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            fontSize = 15.sp
        )

        TextButton(
            onClick = {
                navController.navigate(Screen.ListKhoanChi.createRoute(userId))
            }
        ) {
            Text(
                text = "Xem thêm",
                fontWeight = FontWeight.Bold,
                color = PrimaryColor,
                fontSize = 15.sp
            )
        }
    }
}

@Composable
@Preview
fun KhoanChiMoreRowPreview(){
    var navController = rememberNavController()
    KhoanChiMoreRow(modifier = Modifier, navController=navController, 1)
}