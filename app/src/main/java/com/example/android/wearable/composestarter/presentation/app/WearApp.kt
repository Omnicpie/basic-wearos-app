package com.example.android.wearable.composestarter.presentation.app

import SideMenuScreen
import androidx.compose.runtime.Composable
import androidx.wear.compose.navigation.SwipeDismissableNavHost
import androidx.wear.compose.navigation.composable
import androidx.wear.compose.navigation.rememberSwipeDismissableNavController
import com.example.android.wearable.composestarter.presentation.screens.HomeScreen
import com.example.android.wearable.composestarter.presentation.screens.ListScreen
import com.example.android.wearable.composestarter.presentation.theme.WearAppTheme
import com.google.android.horologist.compose.layout.AppScaffold


@Composable
fun WearApp() {
    val navController = rememberSwipeDismissableNavController()


    WearAppTheme {
        AppScaffold {
            SwipeDismissableNavHost(navController = navController, startDestination = "menu") {
                composable("menu") {
                    HomeScreen(
                        onShowList = { navController.navigate("side") }
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
