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
    object ListKhoanChi : Screen("listkhoanchi/{userId}"){
        fun createRoute(userId: Int) = "listkhoanchi/$userId"
    }
    object KhoanChiDetail : Screen("khoanchi_detail/{id_khoanChi}/{userId}"){
        fun createRoute(id_khoanChi: Int , userId: Int) = "khoanchi_detail/$id_khoanChi/$userId"
    }
    object AddKhoanChi : Screen("addkhoanchi/{userId}"){
        fun createRoute(userId: Int) = "addkhoanchi/$userId"
    }

    object UpdateKhoanChi : Screen("updatekhoanchi/{userId}/{id_khoanchi}"){
        fun createRoute(userId: Int, id_khoanchi: Int) = "updatekhoanchi/$userId/$id_khoanchi"
    }
}