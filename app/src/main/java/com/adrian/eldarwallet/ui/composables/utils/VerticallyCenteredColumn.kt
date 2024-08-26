package com.adrian.eldarwallet.ui.composables.utils

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun VerticallyCenteredColumn(
    topBias: Float = 1f,
    bottomBias: Float = 1f,
    content: @Composable () -> Unit
) {
    Column {
        Spacer(modifier = Modifier.weight(topBias))
        content.invoke()
        Spacer(modifier = Modifier.weight(bottomBias))
    }
}