package com.example.android.wearable.composestarter.presentation.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.wear.compose.material.Icon
import androidx.wear.compose.material.Text
import com.google.android.horologist.compose.layout.ScreenScaffold
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.wear.compose.material.Button
import androidx.wear.compose.navigation.rememberSwipeDismissableNavController
import androidx.wear.compose.ui.tooling.preview.WearPreviewDevices
import androidx.wear.compose.ui.tooling.preview.WearPreviewFontScales
import com.example.android.wearable.composestarter.R


@Composable
fun HomeScreen( nav: NavHostController){
    val scrollState = rememberScrollState()

    ScreenScaffold(scrollState = scrollState) {
        Box(
            modifier = Modifier.fillMaxSize().offset(y=12.dp),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = R.drawable.climbing),
                contentDescription = null,
                modifier= Modifier.width(48.dp).offset(y= (-48).dp)

            )
            Text("Climb Tracker")
            Button(onClick ={nav.navigate("current")}, modifier = Modifier.offset(y=48.dp)){
                Text("New")
            }
        }
    }
}


@WearPreviewDevices
@WearPreviewFontScales
@Composable
fun HomeScreenPreview() {
    HomeScreen(nav = rememberSwipeDismissableNavController())
}
