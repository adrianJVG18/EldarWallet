package com.adrian.eldarwallet.ui.composables.utils

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun HorizontallyCenteredRow(
    startBias: Float = 1f,
    endBias: Float = 1f,
    content: @Composable () -> Unit
) {
    Row {
        Spacer(modifier = Modifier.weight(startBias))
        content.invoke()
        Spacer(modifier = Modifier.weight(endBias))
    }
}