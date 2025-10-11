sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object Login : Screen("login")
    object Home : Screen("home")
    object Profile : Screen("profile")
    object NganSach : Screen("nganSach")
    object Trade : Screen("trade")
    object AddTrade : Screen("add_trade")
}