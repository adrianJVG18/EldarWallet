package com.adrian.eldarwallet.ui.composables.home

import android.content.res.Configuration
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.adrian.eldarwallet.presentation.model.Balance
import com.adrian.eldarwallet.ui.theme.EldarWalletTheme

@Composable
fun HomeScreen(
    onManageCards: () -> Unit = {},
    onGenerateQr: () -> Unit = {},
    onStartPayment: () -> Unit = {}
) {
    LazyColumn {
        // these are "demo" values
        item {
            Spacer(modifier = Modifier.height(24.dp))
        }
        item {
            BalanceButton(
                balance = Balance(amount = 1900850.85),
                horizontalMargin = 8,
                onClick = {  }
            )
        }
        item {
            Spacer(modifier = Modifier.height(16.dp))
        }
        item {
            RectangleBoxItem(
                label = "Manage Cards",
                horizontalMargin = 8,
                onClick = { onManageCards.invoke() }
            )
        }
        item {
            Spacer(modifier = Modifier.height(4.dp))
        }
        item {
            RectangleBoxItem(
                label = "Generate QR",
                horizontalMargin = 8,
                onClick = { onGenerateQr.invoke() }
            )
        }
        item {
            Spacer(modifier = Modifier.height(4.dp))
        }
        item {
            RectangleBoxItem(
                label = "Pay",
                horizontalMargin = 8,
                onClick = { onStartPayment.invoke() }
            )
        }

    }
}

@Preview(name = "Light theme", showBackground = true)
@Preview(
    name = "Dark theme",
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
private fun PreviewHomeScreen() {
    EldarWalletTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            HomeScreen()
        }
    }
}