package com.example.quanlychitieu.feature.navigation

import AddTradeScreen
import HomeScreen
import NganSachScreen
import ProfileScreen
import SplashScreen
import TradeScreen
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.quanlychitieu.Utils.listKhoanChiConst.listKhoanChi
import com.example.quanlychitieu.ViewModels.KhoanChiViewModel
import com.example.quanlychitieu.Views.AddKhoanChi.AddKhoanChiScreen
import com.example.quanlychitieu.Views.ListKhoanChi.ListKhoanChiScreen
import com.example.quanlychitieu.Views.login.LoginScreen
import com.example.quanlychitieu.ui.ViewModels.NguoiDungViewModel
import com.example.quanlychitieu.ui.ViewModels.TaiKhoanViewModel


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

                viewModel = hiltViewModel(),
            )
        }

        composable(
            route = Screen.Login.route,
            enterTransition = truotVaoTuPhai(),
            exitTransition = truotRaSangTrai(),
            popEnterTransition = truotVaoTuTrai(),
            popExitTransition = truotRaSangPhai()
        ) {

            val viewModel: NguoiDungViewModel = hiltViewModel()
            LoginScreen(
                navController,
                viewModel = viewModel,
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
                viewModel = hiltViewModel(),
                taikhoanViewModel = hiltViewModel()
            )
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
            popExitTransition = truotRaSangPhai(),
            arguments = listOf(
                navArgument("userId") { type = NavType.IntType }
            )
        ) { backStackEntry ->
            val userId = backStackEntry.arguments?.getInt("userId") ?: 0

            TradeScreen(
                navController = navController,
                userId = userId,
                khoanChiViewModel = hiltViewModel(),
                taiKhoanViewModel = hiltViewModel(),
            )
        }

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
            popExitTransition = truotRaSangPhai(),
            arguments = listOf(
                navArgument("userId") { type = NavType.IntType }
            )
        ) { backStackEntry ->
            val userId = backStackEntry.arguments?.getInt("userId") ?: 0

            AddTradeScreen(
                navController = navController,
                userId = userId,
                khoanChiViewModel = hiltViewModel(),
                taiKhoanViewModel = hiltViewModel(),
            )
        }

        composable(
            route = Screen.ListKhoanChi.route,
            enterTransition = truotVaoTuPhai(),
            exitTransition = truotRaSangTrai(),
            popEnterTransition = truotVaoTuTrai(),
            popExitTransition = truotRaSangPhai()
        ) { ListKhoanChiScreen(navController) }

        composable(
            route = Screen.AddKhoanChi.route,
            enterTransition = truotVaoTuPhai(),
            exitTransition = truotRaSangTrai(),
            popEnterTransition = truotVaoTuTrai(),
            popExitTransition = truotRaSangPhai()
        ) { AddKhoanChiScreen(navController) }
    }
}
