package com.adrian.eldarwallet.ui.composables.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.adrian.eldarwallet.ui.composables.login.LoginScaffold
import com.adrian.eldarwallet.ui.composables.signup.SignupScaffold

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
    }
}

object Destination {
    const val LOGIN = "LoginScreen"
    const val SIGN_UP = "SignUpScreen"
}