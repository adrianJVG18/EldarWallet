package com.adrian.eldarwallet.ui.composables.login

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.adrian.eldarwallet.presentation.LoginViewModel

/**
 * Creates the Scaffold of the Login, Providing the ViewModel that updates their States.
 *
 * The Main reason to have a "Scaffold level" and a "Screen level" it's
 * to being able to pass the whole ViewModel to Composable functions and
 * (Scaffold level) and still being able to create Previews of the Different
 * expected states (Screen level).
 */
@Composable
fun LoginScaffold(loginViewModel: LoginViewModel) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        LoginScreen(
            isLoading = loginViewModel.isLoading ,
            usernameInput = loginViewModel.typedUsername,
            passwordInput = loginViewModel.typedPassword,
            onUsernameChanged = { loginViewModel.updateTypedUsername(it) },
            onPasswordChanged = { loginViewModel.updateTypedPassword(it) },
            onAttemptLogin = { loginViewModel.attemptLogin() }
        )
    }
}
