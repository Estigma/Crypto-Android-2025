package com.example.coinapp.views

import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.coinapp.navigation.BottomNavigationItem
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

//@Composable
//fun MainScreen() {
//    val navController = rememberNavController()
//    Scaffold (
//        bottomBar = { BottomTabBar(navController) }
//    ){
//        NavigationGraph(navController)
//    }
//}
//
//@Composable
//fun NavigationBar(navController: NavHostController) {
//    NavHost(navController, startDestination = BottomNavigationItem.Home.route) {
//        composable(BottomNavigationItem.Home.route) {AssetsList()}
//        composable(BottomNavigationItem.Favourites.route) {Favourities()}
//        composable(BottomNavigationItem.Settings.route) {Settings()}
//}


@Composable
fun BottomTabBar(navController: NavController){
    val items = listOf(
        BottomNavigationItem.Home,
        BottomNavigationItem.Favourites,
        BottomNavigationItem.Settings
    )

    BottomAppBar {
        val navBackStack by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStack?.destination?.route

        items.forEach{ baritem ->
            val selected = baritem.route == currentRoute

            NavigationBarItem(
                selected = selected,
                label = { Text(baritem.title) },
                onClick = {
                    navController.navigate(baritem.route) {
                        navController.graph.startDestinationRoute.let { route ->
                            if (route != null) {
                                popUpTo(route) {
                                    saveState = true
                                }
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = {
                    Icon(
                        imageVector = if (selected) baritem.selectedIcon else baritem.unselectedIcon,
                        contentDescription = null
                    )
                }
            )
        }
    }
}