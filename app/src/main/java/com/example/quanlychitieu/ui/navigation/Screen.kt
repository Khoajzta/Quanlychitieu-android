sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object Login : Screen("login")
    object Home : Screen("home/{userId}") {
        fun createRoute(userId: Int) = "home/$userId"
    }
    object Profile : Screen("profile")
    object Trade : Screen("trade/{userId}"){
        fun createRoute(userId: Int) = "trade/$userId"
    }
    object NganSach : Screen("nganSach")
    object AddTrade : Screen("add_trade/{userId}"){
        fun createRoute(userId: Int) = "add_trade/$userId"
    }
    object ListKhoanChi : Screen("listkhoanchi")
    object AddKhoanChi : Screen("addkhoanchi")
}