package com.example.android.wearable.composestarter.presentation.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.ButtonDefaults
import androidx.wear.compose.material.ExperimentalWearMaterialApi
import androidx.wear.compose.material.Text
import androidx.wear.compose.material.rememberSwipeableState
import androidx.wear.compose.ui.tooling.preview.WearPreviewDevices
import androidx.wear.compose.ui.tooling.preview.WearPreviewFontScales
import com.example.android.wearable.composestarter.presentation.components.DurationText
import com.google.android.horologist.annotations.ExperimentalHorologistApi
import com.google.android.horologist.compose.layout.ScreenScaffold
import com.google.android.horologist.compose.material.Button
import com.google.android.horologist.compose.rotaryinput.rotaryWithScroll
import java.util.Timer
import java.util.TimerTask


@OptIn(ExperimentalHorologistApi::class)
@Composable
fun HomeScreen( onShowList: () -> Unit) {
    val scrollState = rememberScrollState()

    var secondsElapsed by remember { mutableIntStateOf(0) }
    var isRunning by remember { mutableStateOf(false) }
    var timer: Timer? by remember { mutableStateOf(null) }

    val duration = secondsElapsed * 1000L

    var displayIcon = Icons.Filled.PlayArrow
    if(isRunning) displayIcon = Icons.Default.Clear

    ScreenScaffold(scrollState = scrollState) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .rotaryWithScroll(scrollState)

                .padding(32.dp),
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
                        imageVector = displayIcon,
                        "desc",
                        colors = ButtonDefaults.secondaryButtonColors(),
                        onClick ={
                            if (isRunning) {timer?.cancel() }else{
                                secondsElapsed = 0
                                startTimer({timer= it}, secondsElapsed, { secondsElapsed = it })
                            }
                            isRunning = !isRunning
                        },
                        modifier = Modifier.offset(y = 16.dp, x = (-32).dp)
                    )
                    Button(
                        imageVector = Icons.Filled.Settings,
                        "desc",
                        colors = ButtonDefaults.secondaryButtonColors(),
                        modifier = Modifier.offset(y = 16.dp, x = 32.dp),
                        onClick = onShowList,
                    )

                }
            }

        }
        Column(

        ){
            Text(text = "Banana")
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
fun HomeScreenPreview() {
    HomeScreen( onShowList = {})
}
