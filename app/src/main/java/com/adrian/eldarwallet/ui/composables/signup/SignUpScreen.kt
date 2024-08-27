package com.adrian.eldarwallet.ui.composables.signup

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.adrian.eldarwallet.ui.composables.login.PasswordTextField
import com.adrian.eldarwallet.ui.composables.utils.HorizontallyCenteredRow
import com.adrian.eldarwallet.ui.theme.EldarWalletTheme

@Composable
fun SignUpScreen(
    isLoading: Boolean = false,
    nameInput: String = "",
    lastNameInput: String = "",
    usernameInput: String = "",
    passwordInput: String = "",
    onNameChanged: (String) -> Unit = { },
    onLastNameChanged: (String) -> Unit = { },
    onUsernameChanged: (String) -> Unit = { },
    onPasswordChanged: (String) -> Unit = { },
    signUpCondition: () -> Boolean = { true },
    onAttemptSignUp: () -> Unit = { },
    requestError: String = ""
) {
    val focusManager = LocalFocusManager.current

    Column {
        Spacer(modifier = Modifier.height(16.dp))
        HorizontallyCenteredRow {
            Text(text = "Register a New User")
        }
        Spacer(modifier = Modifier.height(8.dp))
        FormTextField(
            input = nameInput,
            label = "Name",
            onTextChanged = { onNameChanged.invoke(it) },
            onKeyboardNext = { focusManager.moveFocus(FocusDirection.Down) }
        )
        Spacer(modifier = Modifier.height(8.dp))
        FormTextField(
            input = lastNameInput,
            label = "Last Name",
            onTextChanged = { onLastNameChanged.invoke(it) },
            onKeyboardNext = { focusManager.moveFocus(FocusDirection.Down) }
        )
        Spacer(modifier = Modifier.height(8.dp))
        FormTextField(
            input = usernameInput,
            label = "Username",
            onTextChanged = { onUsernameChanged.invoke(it) },
            onKeyboardNext = { focusManager.moveFocus(FocusDirection.Down) }
        )
        Spacer(modifier = Modifier.height(8.dp))
        PasswordTextField(
            input = passwordInput,
            onTextChanged = { onPasswordChanged.invoke(it) },
            onKeyboardDone = { onAttemptSignUp.invoke() })

        Spacer(modifier = Modifier.height(8.dp))
        if (requestError.isNotBlank()) {
            Spacer(modifier = Modifier.height(16.dp))
            HorizontallyCenteredRow {
                Text(
                    text = requestError,
                    color = MaterialTheme.colorScheme.error,
                    fontSize = 18.sp,
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.errorContainer)
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
        }
        HorizontallyCenteredRow {
            Button(
                enabled = signUpCondition.invoke(),
                modifier = Modifier.align(Alignment.CenterHorizontally),
                onClick = { onAttemptSignUp.invoke() }) {
                    Text(text = "Sign In")
                }
        }

        if (isLoading) {
            Spacer(modifier = Modifier.height(16.dp))
            CircularProgressIndicator(
                modifier = Modifier
                    .width(48.dp)
                    .align(Alignment.CenterHorizontally),
                color = MaterialTheme.colorScheme.secondary,
                trackColor = MaterialTheme.colorScheme.surfaceVariant,
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
private fun PreviewSignupScreen() {
    EldarWalletTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            SignUpScreen()
        }
    }
}

@Preview(name = "Light theme", showBackground = true)
@Preview(
    name = "Dark theme",
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
private fun PreviewSignupScreen_RequestError() {
    EldarWalletTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            SignUpScreen(requestError = "All fields are Mandatory")
        }
    }
}

@Preview(name = "Light theme", showBackground = true)
@Preview(
    name = "Dark theme",
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
private fun PreviewSignupScreen_ShowLoading() {
    EldarWalletTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            SignUpScreen(isLoading = true)
        }
    }
}