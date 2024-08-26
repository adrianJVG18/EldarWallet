package com.adrian.eldarwallet.ui.activity

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.adrian.eldarwallet.presentation.LoginViewModel
import com.adrian.eldarwallet.ui.composables.login.LoginScaffold
import com.adrian.eldarwallet.ui.composables.login.LoginScreen
import com.adrian.eldarwallet.ui.theme.EldarWalletTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : ComponentActivity() {

    private val loginViewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EldarWalletTheme {
                LoginScaffold(loginViewModel = loginViewModel)
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
private fun PreviewLoginActivity() {
    EldarWalletTheme {
        LoginScreen()
    }
}

@Preview(name = "Light theme", showBackground = true)
@Preview(
    name = "Dark theme",
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
private fun PreviewLoginActivityOnLoading() {
    EldarWalletTheme {
        LoginScreen(isLoading = true)
    }
}
