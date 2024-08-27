package com.adrian.eldarwallet.ui.composables.login

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.adrian.commons.model.Response
import com.adrian.eldarwallet.presentation.LoginViewModel
import com.adrian.eldarwallet.presentation.model.AuthUser
import com.adrian.eldarwallet.ui.composables.navigation.Destination

/**
 * Creates the Scaffold of the Login, Providing the ViewModel that updates their States.
 *
 * The Main reason to have a "Scaffold level" and a "Screen level" it's
 * to being able to pass the whole ViewModel to Composable functions and
 * (Scaffold level) and still being able to create Previews of the Different
 * expected states (Screen level).
 */
@Composable
fun LoginScaffold(
    navController: NavController,
    loginViewModel: LoginViewModel = hiltViewModel()
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        val signInState by loginViewModel.signInState.collectAsState()

        when (signInState) {
            is Response.Success -> {
                // Go to Home Screen
            }
            is Response.Failure -> {
                LoginScreen(
                    usernameInput = loginViewModel.typedUsername,
                    passwordInput = loginViewModel.typedPassword,
                    onUsernameChanged = { loginViewModel.updateTypedUsername(it) },
                    onPasswordChanged = { loginViewModel.updateTypedPassword(it) },
                    onAttemptLogin = { loginViewModel.attemptLogin() },
                    onSignUpNavigate = { navController.navigate(Destination.SIGN_UP) },
                    requestError = (signInState as Response.Failure<AuthUser>).message
                )
            }
            is Response.Loading -> {
                LoginScreen(
                    isLoading = (signInState as Response.Loading<AuthUser>).isLoading,
                    usernameInput = loginViewModel.typedUsername,
                    passwordInput = loginViewModel.typedPassword,
                    onUsernameChanged = { loginViewModel.updateTypedUsername(it) },
                    onPasswordChanged = { loginViewModel.updateTypedPassword(it) },
                    onSignUpNavigate = { navController.navigate(Destination.SIGN_UP) },
                    onAttemptLogin = { loginViewModel.attemptLogin() }
                )
            }
        }

    }
}
