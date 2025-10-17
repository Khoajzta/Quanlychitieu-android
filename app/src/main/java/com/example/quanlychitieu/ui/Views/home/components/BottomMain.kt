package com.example.quanlychitieu.Views.home.components

import Screen
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBalanceWallet
import androidx.compose.material.icons.filled.Autorenew
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.quanlychitieu.ui.theme.Dimens.PaddingBody
import com.example.quanlychitieu.ui.theme.Dimens.RadiusFull

// Dữ liệu mỗi mục trong bottom bar
data class BottomNavItem(
    val title: String,
    val icon: ImageVector,
    val route: String
)

@Composable
fun BottomNavigationBar(
    navController: NavController? = null,
    modifier: Modifier = Modifier,
    userId: Int // thêm userId
) {
    val items = listOf(
        BottomNavItem("Home", Icons.Default.Home, Screen.Home.route),
        BottomNavItem("Giao dịch", Icons.Default.Autorenew, Screen.Trade.route),
        BottomNavItem("Ngân sách", Icons.Default.AccountBalanceWallet, Screen.NganSach.route),
        BottomNavItem("Cá nhân", Icons.Default.Person, Screen.Profile.route)
    )

    val currentRoute = navController?.currentBackStackEntryAsState()?.value?.destination?.route

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(PaddingBody),
        horizontalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(65.dp)
                .shadow(
                    5.dp,
                    RoundedCornerShape(RadiusFull),
                    ambientColor = Color(0xFF6FBAD6),
                )
                .clip(RoundedCornerShape(RadiusFull)),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .matchParentSize()
                    .blur(18.dp)
                    .background(Brush.horizontalGradient(listOf(Color(0xFF9FD7EE), Color(0xFF6FBAD6))))
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                items.forEach { item ->
                    val isSelected = currentRoute == item.route
                    BottomBarItem(
                        icon = item.icon,
                        title = item.title,
                        selected = isSelected,
                        onClick = {
                            if (navController != null && currentRoute != item.route) {
                                val routeToNavigate = if (item.route == Screen.Trade.route) {
                                    Screen.Trade.createRoute(userId)
                                } else item.route

                                navController.navigate(routeToNavigate) {
                                    popUpTo(navController.graph.startDestinationId)
                                    launchSingleTop = true
                                }
                            }
                        }
                    )
                }
            }
        }
    }
}


@Composable
fun BottomBarItem(
    icon: ImageVector,
    title: String,
    selected: Boolean = false,
    onClick: () -> Unit
) {
//    val color = if (selected) Color(0xFF1C94D5) else Color.White
    val color = Color.White


    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .padding(vertical = 4.dp, horizontal = 6.dp)
    ) {
        Box(
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape)
                .clickable { onClick() },
//                .background(
//                    if (selected) Color.White.copy(alpha = 0.2f)
//                    else Color.Transparent
//                ),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = title,
                tint = color,
                modifier = Modifier.size(30.dp)
            )
        }
//        Text(
//            text = title,
//            color = color,
//            fontSize = 12.sp
//        )
    }
}

@Composable
@Preview(showBackground = true)
fun BottomNavigationBarPreview() {

}
