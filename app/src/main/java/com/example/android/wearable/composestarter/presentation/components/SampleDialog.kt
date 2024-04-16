package com.example.android.wearable.composestarter.presentation.components

import androidx.compose.runtime.Composable
import androidx.wear.compose.material.Text
import androidx.wear.compose.material.dialog.Dialog
import com.google.android.horologist.annotations.ExperimentalHorologistApi
import com.google.android.horologist.compose.layout.rememberResponsiveColumnState
import com.google.android.horologist.compose.material.AlertContent


@OptIn(ExperimentalHorologistApi::class)
@Composable
fun SampleDialog(
    showDialog: Boolean,
    onDismiss: () -> Unit,
    onCancel: () -> Unit,
    onOk: () -> Unit
) {
    val state = rememberResponsiveColumnState()

    Dialog(
        showDialog = showDialog,
        onDismissRequest = onDismiss,
        scrollState = state.state
    ) {
        SampleDialogContent(onCancel, onDismiss, onOk)
    }
}

@OptIn(ExperimentalHorologistApi::class)
@Composable
fun SampleDialogContent(
    onCancel: () -> Unit,
    onDismiss: () -> Unit,
    onOk: () -> Unit
) {
    AlertContent(
        icon = {},
        title = "Title",
        onCancel = {
            onCancel()
            onDismiss()
        },
        onOk = {
            onOk()
            onDismiss()
        }
    ) {
        item {
            Text(text = "An unknown error occurred during the request.")
        }
    }
}
