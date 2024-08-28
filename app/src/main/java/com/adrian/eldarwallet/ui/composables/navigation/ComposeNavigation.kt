package com.adrian.eldarwallet.ui.composables.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.adrian.eldarwallet.ui.composables.generate_qr.GenerateQrScaffold
import com.adrian.eldarwallet.ui.composables.home.HomeScaffold
import com.adrian.eldarwallet.ui.composables.login.LoginScaffold
import com.adrian.eldarwallet.ui.composables.manage_cards.ManageCardsScaffold
import com.adrian.eldarwallet.ui.composables.signup.SignupScaffold
import com.adrian.eldarwallet.ui.composables.start_payment.StartPaymentScaffold

@Composable
fun ComposeNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Destination.LOGIN
    ) {
        composable(Destination.LOGIN) {
            LoginScaffold(navController = navController)
        }

        composable(Destination.SIGN_UP) {
            SignupScaffold(navController = navController)
        }

        composable(Destination.HOME) {
            HomeScaffold(navController = navController)
        }

        composable(Destination.GENERATE_QR) {
            GenerateQrScaffold(navController = navController)
        }

        composable(Destination.MANAGE_CARDS) {
            ManageCardsScaffold(navController = navController)
        }

        composable(Destination.START_PAYMENT) {
            StartPaymentScaffold(navController = navController)
        }
    }
}

object Destination {
    const val LOGIN = "LoginScreen"
    const val SIGN_UP = "SignUpScreen"
    const val HOME = "HomeScreen"
    const val GENERATE_QR = "GenerateQrScreen"
    const val MANAGE_CARDS = "ManageCardsScreen"
    const val START_PAYMENT = "StartPaymentScreen"
}