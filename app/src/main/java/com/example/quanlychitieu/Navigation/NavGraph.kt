package com.example.quanlychitieu.feature.navigation

import AddTradeScreen
import HomeScreen
import NganSachScreen
import ProfileScreen
import SplashScreen
import TradeScreen
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.quanlychitieu.Views.ListKhoanChi.ListKhoanChiScreen
import com.example.quanlychitieu.Views.login.LoginScreen
import com.example.quanlychitieu.models.KhoanChiModel


@Composable
fun AppNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(Screen.Splash.route) {
            SplashScreen(
                onNavigateToLogin = {
                    navController.navigate(Screen.Login.route) {
                        popUpTo(Screen.Splash.route) { inclusive = true }
                    }
                },
                onNavigateToHome = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Splash.route) { inclusive = true }
                    }
                }
            )
        }

        composable(Screen.Login.route) {
            LoginScreen(
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
            popExitTransition = truotRaSangPhai()
        ) {
            HomeScreen(navController)
        }

        composable(
            route = Screen.Profile.route,
            enterTransition = truotVaoTuPhai(),
            exitTransition = truotRaSangTrai(),
            popEnterTransition = truotVaoTuTrai(),
            popExitTransition = truotRaSangPhai()
        ) { ProfileScreen(navController) }

        composable(
            route = Screen.Trade.route,
            enterTransition = truotVaoTuPhai(),
            exitTransition = truotRaSangTrai(),
            popEnterTransition = truotVaoTuTrai(),
            popExitTransition = truotRaSangPhai()
        ) { TradeScreen(navController) }

        composable(
            route = Screen.NganSach.route,
            enterTransition = truotVaoTuPhai(),
            exitTransition = truotRaSangTrai(),
            popEnterTransition = truotVaoTuTrai(),
            popExitTransition = truotRaSangPhai()
        ) { NganSachScreen(navController) }

        composable(
            route = Screen.AddTrade.route,
            enterTransition = truotVaoTuPhai(),
            exitTransition = truotRaSangTrai(),
            popEnterTransition = truotVaoTuTrai(),
            popExitTransition = truotRaSangPhai()
        ) {
            var listKhoanChi = listOf(
                KhoanChiModel(1, "Ăn uống", 3000000, 12, 100, "blue","🍕"),
                KhoanChiModel(2, "Mua sắm", 2000000, 5, 101, "red","🤣"),
                KhoanChiModel(3, "Giải trí", 1500000, 3, 102, "green","❤️")
            )
            AddTradeScreen(navController,listKhoanChi)
        }

        composable(
            route = Screen.ListKhoanChi.route,
            enterTransition = truotVaoTuPhai(),
            exitTransition = truotRaSangTrai(),
            popEnterTransition = truotVaoTuTrai(),
            popExitTransition = truotRaSangPhai()
        ) { ListKhoanChiScreen(navController) }
    }
}
