package com.example.android.wearable.composestarter.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.Text


@Composable
fun DurationText(durationMillis: Long) {
    val formattedDuration = formatDuration(durationMillis)
    Text(
        text = formattedDuration,
        modifier = Modifier.fillMaxWidth(),
        textAlign = TextAlign.Center,
        color = MaterialTheme.colors.primary,
        fontSize = 30.sp,
    )
}

private fun formatDuration(durationMillis: Long): String {
    val hours = durationMillis / (1000 * 60 * 60)
    val minutes = (durationMillis % (1000 * 60 * 60)) / (1000 * 60)
    val seconds = (durationMillis % (1000 * 60)) / 1000

    return String.format("%02d:%02d:%02d", hours, minutes, seconds)
}

@Preview
@Composable
fun PreviewDurationText() {
    DurationText(durationMillis = 123456789L) // Example duration in milliseconds
}
