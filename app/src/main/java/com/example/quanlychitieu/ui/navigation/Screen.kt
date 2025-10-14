sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object Login : Screen("login")
    object Home : Screen("home")
    object Profile : Screen("profile")
    object Trade : Screen("trade")
    object NganSach : Screen("nganSach")
    object AddTrade : Screen("add_trade")
    object ListKhoanChi : Screen("listkhoanchi")
    object AddKhoanChi : Screen("addkhoanchi")
}