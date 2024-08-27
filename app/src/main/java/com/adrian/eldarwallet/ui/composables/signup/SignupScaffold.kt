package com.adrian.eldarwallet.ui.composables.signup

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.adrian.commons.model.Response
import com.adrian.eldarwallet.presentation.SignupViewModel
import com.adrian.eldarwallet.presentation.model.AuthUser
import com.adrian.eldarwallet.ui.composables.navigation.Destination

@Composable
fun SignupScaffold(
    navController: NavController,
    signupViewModel: SignupViewModel = hiltViewModel()
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        val signUpState by signupViewModel.signUpState.collectAsState()

        when(signUpState) {
            is Response.Success -> {
                signupViewModel.cleanSignupForm()
                navController.popBackStack(Destination.LOGIN, false)
            }

            is Response.Failure -> {
                SignUpScreen(
                    nameInput = signupViewModel.typedName,
                    lastNameInput = signupViewModel.typedLastName,
                    usernameInput = signupViewModel.typedUsername,
                    passwordInput = signupViewModel.typedPassword,
                    onNameChanged = { signupViewModel.updateTypedName(it) },
                    onLastNameChanged = { signupViewModel.updateTypedLastName(it) },
                    onUsernameChanged = { signupViewModel.updateTypedUsername(it) },
                    onPasswordChanged = { signupViewModel.updateTypedPassword(it) },
                    onAttemptSignUp = { signupViewModel.attemptSigningUp() },
                    signUpCondition = { signupViewModel.validateSignUpConditions() },
                    requestError = (signUpState as Response.Failure<AuthUser>).message
                )
            }

            is Response.Loading -> {
                SignUpScreen(
                    isLoading = (signUpState as Response.Loading<AuthUser>).isLoading,
                    nameInput = signupViewModel.typedName,
                    lastNameInput = signupViewModel.typedLastName,
                    usernameInput = signupViewModel.typedUsername,
                    passwordInput = signupViewModel.typedPassword,
                    onNameChanged = { signupViewModel.updateTypedName(it) },
                    onLastNameChanged = { signupViewModel.updateTypedLastName(it) },
                    onUsernameChanged = { signupViewModel.updateTypedUsername(it) },
                    onPasswordChanged = { signupViewModel.updateTypedPassword(it) },
                    signUpCondition = { signupViewModel.validateSignUpConditions() },
                    onAttemptSignUp = { signupViewModel.attemptSigningUp() },
                )
            }
        }
    }
}