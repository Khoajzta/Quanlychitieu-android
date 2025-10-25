package com.example.quanlychitieu.feature.navigation

import AddTradeScreen
import HomeScreen
import NganSachScreen
import ProfileScreen
import Screen
import SplashScreen
import TradeScreen
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.quanlychitieu.Views.AddKhoanChi.AddKhoanChiScreen
import com.example.quanlychitieu.Views.ListKhoanChi.ListKhoanChiScreen
import com.example.quanlychitieu.Views.login.LoginScreen
import com.example.quanlychitieu.ui.Views.AddTaiKhoan.AddTaiKhoanScreen
import com.example.quanlychitieu.ui.Views.ChuyenTien.ChuyenTienScreen
import com.example.quanlychitieu.ui.Views.KhoanChiDetail.KhoanChiDetailScreen
import com.example.quanlychitieu.ui.Views.UpdateKhoanChi.UpdateKhoanChiScreen


@Composable
fun AppNavGraph(navController: NavHostController) {


    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ) {
        composable(
            route = Screen.Splash.route,
            enterTransition = truotVaoTuPhai(),
            exitTransition = truotRaSangTrai(),
            popEnterTransition = truotVaoTuTrai(),
            popExitTransition = truotRaSangPhai()
        ) {
            SplashScreen(
                navController,

                onNavigateToLogin = {
                    navController.navigate(Screen.Login.route) {
                        popUpTo(Screen.Splash.route) { inclusive = true }
                    }
                },
            )
        }

        composable(
            route = Screen.Login.route,
            enterTransition = truotVaoTuPhai(),
            exitTransition = truotRaSangTrai(),
            popEnterTransition = truotVaoTuTrai(),
            popExitTransition = truotRaSangPhai()
        ) {
            LoginScreen(
                navController,
                onLoginSuccess = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Login.route) { inclusive = true }
                    }
                }
            )
        }

        composable(
            route = Screen.Home.route,
            enterTransition = truotVaoTuPhai(),
            exitTransition = truotRaSangTrai(),
            popEnterTransition = truotVaoTuTrai(),
            popExitTransition = truotRaSangPhai(),
            arguments = listOf(
                navArgument("userId") { type = NavType.IntType }
            )
        ) { backStackEntry ->
            val userId = backStackEntry.arguments?.getInt("userId") ?: 0

            HomeScreen(
                userId = userId,
                navController = navController,
            )
        }


        composable(
            route = Screen.Profile.route,
            enterTransition = truotVaoTuPhai(),
            exitTransition = truotRaSangTrai(),
            popEnterTransition = truotVaoTuTrai(),
            popExitTransition = truotRaSangPhai(),
            arguments = listOf(
                navArgument("userId") { type = NavType.IntType }
            )
        ) { backStackEntry ->
            val userId = backStackEntry.arguments?.getInt("userId") ?: 0

            ProfileScreen(
                navController = navController,
                userId = userId,
            )
        }


        composable(
            route = Screen.Trade.route,
            enterTransition = truotVaoTuPhai(),
            exitTransition = truotRaSangTrai(),
            popEnterTransition = truotVaoTuTrai(),
            popExitTransition = truotRaSangPhai(),
            arguments = listOf(
                navArgument("userId") { type = NavType.IntType }
            )
        ) { backStackEntry ->
            val userId = backStackEntry.arguments?.getInt("userId") ?: 0

            TradeScreen(
                navController = navController,
                userId = userId,
            )
        }


        composable(
            route = Screen.NganSach.route,
            enterTransition = truotVaoTuPhai(),
            exitTransition = truotRaSangTrai(),
            popEnterTransition = truotVaoTuTrai(),
            popExitTransition = truotRaSangPhai(),
            arguments = listOf(
                navArgument("userId") { type = NavType.IntType }
            )
        ) { backStackEntry ->
            val userId = backStackEntry.arguments?.getInt("userId") ?: 0

            NganSachScreen(
                navController = navController,
                userId = userId,
            )
        }


        composable(
            route = Screen.AddTrade.route,
            enterTransition = truotVaoTuPhai(),
            exitTransition = truotRaSangTrai(),
            popEnterTransition = truotVaoTuTrai(),
            popExitTransition = truotRaSangPhai(),
            arguments = listOf(
                navArgument("userId") { type = NavType.IntType }
            )
        ) { backStackEntry ->
            val userId = backStackEntry.arguments?.getInt("userId") ?: 0

            AddTradeScreen(
                navController = navController,
                userId = userId,
            )
        }

        composable(
            route = Screen.ListKhoanChi.route,
            enterTransition = truotVaoTuPhai(),
            exitTransition = truotRaSangTrai(),
            popEnterTransition = truotVaoTuTrai(),
            popExitTransition = truotRaSangPhai(),
            arguments = listOf(
                navArgument("userId") { type = NavType.IntType }
            )
        ) { backStackEntry ->
            val userId = backStackEntry.arguments?.getInt("userId") ?: 0
            ListKhoanChiScreen(
                navController = navController,
                userId = userId,
            )
        }

        composable(
            route = Screen.KhoanChiDetail.route,
            enterTransition = truotVaoTuPhai(),
            exitTransition = truotRaSangTrai(),
            popEnterTransition = truotVaoTuTrai(),
            popExitTransition = truotRaSangPhai(),
            arguments = listOf(
                navArgument("id_khoanChi") { type = NavType.IntType },
                navArgument("userId") { type = NavType.IntType }
            )
        ) { backStackEntry ->
            val id_khoanChi = backStackEntry.arguments?.getInt("id_khoanChi") ?: 0
            val userId = backStackEntry.arguments?.getInt("userId") ?: 0
            KhoanChiDetailScreen(
                navController = navController,
                id_khoanChi = id_khoanChi,
                userId = userId,
            )
        }



        composable(
            route = Screen.AddKhoanChi.route,
            enterTransition = truotVaoTuPhai(),
            exitTransition = truotRaSangTrai(),
            popEnterTransition = truotVaoTuTrai(),
            popExitTransition = truotRaSangPhai(),
            arguments = listOf(
                navArgument("userId") { type = NavType.IntType }
            )
        ) { backStackEntry ->
            val userId = backStackEntry.arguments?.getInt("userId") ?: 0
            AddKhoanChiScreen(navController, userId)
        }

        composable(
            route = Screen.UpdateKhoanChi.route,
            enterTransition = truotVaoTuPhai(),
            exitTransition = truotRaSangTrai(),
            popEnterTransition = truotVaoTuTrai(),
            popExitTransition = truotRaSangPhai(),
            arguments = listOf(
                navArgument("userId") { type = NavType.IntType },
                navArgument("id_khoanchi") { type = NavType.IntType },
            )
        ) { backStackEntry ->
            val userId = backStackEntry.arguments?.getInt("userId") ?: 0
            val id_khoanchi = backStackEntry.arguments?.getInt("id_khoanchi") ?: 0
            UpdateKhoanChiScreen(navController, id_khoanChi = id_khoanchi, userId = userId )
        }

        composable(
            route = Screen.ChuyenTien.route,
            enterTransition = truotVaoTuPhai(),
            exitTransition = truotRaSangTrai(),
            popEnterTransition = truotVaoTuTrai(),
            popExitTransition = truotRaSangPhai(),
            arguments = listOf(
                navArgument("userId") { type = NavType.IntType }
            )
        ) { backStackEntry ->
            val userId = backStackEntry.arguments?.getInt("userId") ?: 0

            ChuyenTienScreen(
                navController = navController,
                userId = userId,
            )
        }
        composable(
            route = Screen.AddTaiKhoan.route,
            enterTransition = truotVaoTuPhai(),
            exitTransition = truotRaSangTrai(),
            popEnterTransition = truotVaoTuTrai(),
            popExitTransition = truotRaSangPhai(),
            arguments = listOf(
                navArgument("userId") { type = NavType.IntType }
            )
        ) { backStackEntry ->
            val userId = backStackEntry.arguments?.getInt("userId") ?: 0

            AddTaiKhoanScreen(
                navController = navController,
                userId = userId,
            )
        }

    }
}
