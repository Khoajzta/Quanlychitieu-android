import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController

@Composable
fun TradeScreen(
    navController: NavController
){
    Scaffold(
        containerColor = Color.White,
    ) {innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding)
        ) {
            Text("Trade screen")
        }
    }
}