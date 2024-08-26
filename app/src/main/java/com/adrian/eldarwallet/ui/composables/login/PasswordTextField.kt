package com.adrian.eldarwallet.ui.composables.login

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.adrian.eldarwallet.R
import com.adrian.eldarwallet.ui.theme.EldarWalletTheme

@Composable
fun PasswordTextField(
    input: String,
    onTextChanged: (String) -> Unit,
    onKeyboardDone: () -> Unit,
    passwordVisibility: Boolean = false
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    var isPasswordVisible by remember { mutableStateOf(passwordVisibility) }

    val icon = if (isPasswordVisible)
        painterResource(id = R.drawable.visibility_24dp)
    else
        painterResource(id = R.drawable.visibility_off_24dp)

    Row {
        Spacer(modifier = Modifier.width(8.dp))
        OutlinedTextField(
            value = input,
            onValueChange = onTextChanged,
            modifier = Modifier
                .height(64.dp)
                .weight(1f),
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done,
                keyboardType = KeyboardType.Password
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    onKeyboardDone().also {
                        keyboardController?.hide()
                    }
                }
            ),
            placeholder = {
                Text(text = "Type your Password", color = Color.Gray)
            },
            label = { Text(text = "Password") },
            trailingIcon = {
                IconButton(onClick = {
                    isPasswordVisible = !isPasswordVisible
                }) {
                    Icon(
                        painter = icon,
                        contentDescription = "Visibility Icon"
                    )
                }
            },
            visualTransformation = if (isPasswordVisible) VisualTransformation.None
            else PasswordVisualTransformation()
        )
        Spacer(modifier = Modifier.width(8.dp))
    }

}

@Preview(name = "Light theme", showBackground = true)
@Preview(
    name = "Dark theme", showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun PreviewEmptyPasswordTextField() {
    EldarWalletTheme {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            color = MaterialTheme.colorScheme.background
        ) {
            Column {
                Spacer(modifier = Modifier.height(16.dp))
                PasswordTextField(input = "", onTextChanged = {}, onKeyboardDone = {})
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

@Preview(name = "Light theme", showBackground = true)
@Preview(
    name = "Dark theme", showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
private fun PreviewFilledHiddenPasswordTextField() {
    EldarWalletTheme {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            color = MaterialTheme.colorScheme.background
        ) {
            Column {
                Spacer(modifier = Modifier.height(16.dp))
                PasswordTextField(input = "SomePassword", onTextChanged = {}, onKeyboardDone = {})
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

@Preview(name = "Light theme", showBackground = true)
@Preview(
    name = "Dark theme", showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
private fun PreviewFilledVisisblePasswordTextField() {
    EldarWalletTheme {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            color = MaterialTheme.colorScheme.background
        ) {
            Column {
                Spacer(modifier = Modifier.height(16.dp))
                PasswordTextField(
                    input = "SomePassword",
                    onTextChanged = {},
                    onKeyboardDone = {},
                    passwordVisibility = true
                )
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

