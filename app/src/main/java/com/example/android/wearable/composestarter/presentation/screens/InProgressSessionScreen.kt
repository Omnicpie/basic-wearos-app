package com.example.android.wearable.composestarter.presentation.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.wear.compose.material.ButtonDefaults
import androidx.wear.compose.material.Card
import androidx.wear.compose.material.Text
import androidx.wear.compose.navigation.rememberSwipeDismissableNavController
import androidx.wear.compose.ui.tooling.preview.WearPreviewDevices
import androidx.wear.compose.ui.tooling.preview.WearPreviewFontScales
import com.example.android.wearable.composestarter.presentation.components.DurationText
import com.google.android.horologist.annotations.ExperimentalHorologistApi
import com.google.android.horologist.compose.layout.ScalingLazyColumn
import com.google.android.horologist.compose.layout.ScalingLazyColumnDefaults
import com.google.android.horologist.compose.layout.ScreenScaffold
import com.google.android.horologist.compose.layout.rememberResponsiveColumnState
import com.google.android.horologist.compose.material.Button
import java.util.Timer
import java.util.TimerTask


@OptIn(ExperimentalHorologistApi::class)
@Composable
fun InProgressSessionScreen( nav: NavHostController ) {
    val columnState = rememberResponsiveColumnState(
        contentPadding = ScalingLazyColumnDefaults.padding(
            first= ScalingLazyColumnDefaults.ItemType.Text,
            last = ScalingLazyColumnDefaults.ItemType.Chip
        )
    )

    var secondsElapsed by rememberSaveable { mutableIntStateOf(0) }
    var isRunning by remember { mutableStateOf(false) }
    var timer: Timer? by remember { mutableStateOf(null) }

    val duration = secondsElapsed * 1000L

    var text = "Resume Workout"
    if(isRunning) text = "Pause Workout"

    ScreenScaffold(scrollState = columnState) {
        ScalingLazyColumn(
            columnState = columnState,
        ){
            item {
                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(top = 32.dp, bottom = 80.dp)
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        DurationText(durationMillis = duration)
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.BottomCenter
                        ) {
                            Button(
                                imageVector = Icons.Filled.Add,
                                "Add Ascent",
                                colors = ButtonDefaults.iconButtonColors(),
                                onClick = {nav.navigate("list")},
                                modifier = Modifier.offset(y = 32.dp)
                            )
                        }
                    }
                }
            }
            item {
                Card(
                    onClick = {
                        timer?.cancel()
                        nav.navigate("side")
                    }
                ) {
                    Text("Finish Workout");
                }
            }
            item {
                Card(onClick = {
                    if (isRunning) {
                        timer?.cancel()
                    } else {
                        startTimer({ timer = it }, secondsElapsed, { secondsElapsed = it })
                    }
                    isRunning = !isRunning
                }) {
                    Text(text=text);
                }
            }
        }
    }
}


fun startTimer(setTimer:(Timer) -> Unit, initialValue: Int, onUpdate: (Int) -> Unit) {
    val timer = Timer()
    setTimer(timer)
    timer.scheduleAtFixedRate(object : TimerTask() {
        private var value = initialValue
        override fun run() {
            value++
            onUpdate(value)
        }
    }, 1000, 1000) // Run every second (1000 milliseconds)
}




@WearPreviewDevices
@WearPreviewFontScales
@Composable
fun InProgressSessionScreenPreview() {
    InProgressSessionScreen( nav = rememberSwipeDismissableNavController())
}
