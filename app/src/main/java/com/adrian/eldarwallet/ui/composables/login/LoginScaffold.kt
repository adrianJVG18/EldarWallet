package com.adrian.eldarwallet.ui.composables.login

import android.content.res.Configuration
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
import com.adrian.eldarwallet.ui.composables.utils.HorizontallyCenteredRow
import com.adrian.eldarwallet.ui.composables.utils.VerticallyCenteredColumn
import com.adrian.eldarwallet.ui.theme.EldarWalletTheme

/**
 * The purpose of the "Screen level" it's know how to actually "render"
 * that particular piece of Screen.
 */
@Composable
fun LoginScreen(
    isLoading: Boolean = false,
    usernameInput: String = "",
    passwordInput: String = "",
    onUsernameChanged: (String) -> Unit = { },
    onPasswordChanged: (String) -> Unit = { },
    onAttemptLogin: () -> Unit = { },
) {
    val focusManager = LocalFocusManager.current

    VerticallyCenteredColumn(topBias = 0.75f) {
        UsernameTextField(
            input = usernameInput,
            onTextChanged = { onUsernameChanged.invoke(it) },
            onKeyboardNext = { focusManager.moveFocus(FocusDirection.Down)  }
        )
        Spacer(modifier = Modifier.height(8.dp))
        PasswordTextField(
            input = passwordInput,
            onTextChanged = { onPasswordChanged.invoke(it) },
            onKeyboardDone = { onAttemptLogin.invoke() }
        )
        Spacer(modifier = Modifier.height(8.dp))

        HorizontallyCenteredRow {
            Column {
                Button(onClick = { onAttemptLogin.invoke() }) {
                    Text(text = "Sign In")
                }
                Button(onClick = { /* TODO go to Sign up Form */ }) {
                    Text(text = "Sign Up")
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

    }
}


@Preview(name = "Light theme", showBackground = true)
@Preview(
    name = "Dark theme",
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
private fun PreviewLoginScaffold() {
    EldarWalletTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            LoginScreen()
        }
    }
}

@Preview(name = "Light theme", showBackground = true)
@Preview(
    name = "Dark theme",
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
private fun PreviewLoginScaffold_Loading() {
    EldarWalletTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            LoginScreen(isLoading = true)
        }
    }
}