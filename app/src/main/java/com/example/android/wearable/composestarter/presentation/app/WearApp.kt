package com.example.android.wearable.composestarter.presentation.app

import SideMenuScreen
import androidx.compose.runtime.Composable
import androidx.wear.compose.navigation.SwipeDismissableNavHost
import androidx.wear.compose.navigation.composable
import androidx.wear.compose.navigation.rememberSwipeDismissableNavController
import com.example.android.wearable.composestarter.presentation.screens.HomeScreen
import com.example.android.wearable.composestarter.presentation.screens.InProgressSessionScreen
import com.example.android.wearable.composestarter.presentation.screens.ListScreen
import com.example.android.wearable.composestarter.presentation.theme.WearAppTheme
import com.google.android.horologist.compose.layout.AppScaffold


@Composable
fun WearApp() {
    val navController = rememberSwipeDismissableNavController()


    WearAppTheme {
        AppScaffold {
            SwipeDismissableNavHost(navController = navController, startDestination = "home") {
                composable("home"){
                    HomeScreen(
                        nav = navController
                    )
                }
                composable("current") {
                    InProgressSessionScreen(
                        nav = navController
                    )
                }
                composable("side") {
                    SideMenuScreen()
                }
                composable("list") {
                    ListScreen()
                }
            }
        }
    }
}
