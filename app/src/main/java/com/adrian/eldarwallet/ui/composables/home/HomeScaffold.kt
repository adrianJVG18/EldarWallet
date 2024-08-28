package com.adrian.eldarwallet.ui.composables.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.adrian.eldarwallet.presentation.HomeViewModel
import com.adrian.eldarwallet.ui.composables.navigation.Destination

@Composable
fun HomeScaffold(
    navController: NavController,
    homeViewModel: HomeViewModel = hiltViewModel()
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        HomeScreen(
            onManageCards = { navController.navigate(Destination.MANAGE_CARDS) },
            onGenerateQr = { navController.navigate(Destination.GENERATE_QR) },
            onStartPayment = { navController.navigate(Destination.START_PAYMENT) }
        )
    }
}

