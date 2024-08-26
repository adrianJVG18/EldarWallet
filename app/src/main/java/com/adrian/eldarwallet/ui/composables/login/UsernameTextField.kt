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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.adrian.eldarwallet.ui.theme.EldarWalletTheme

@Composable
fun UsernameTextField(
    input: String,
    onTextChanged: (String) -> Unit,
    onKeyboardNext: () -> Unit,
) {
    Row {
        Spacer(modifier = Modifier.width(8.dp))
        OutlinedTextField(
            value = input,
            onValueChange = onTextChanged,
            modifier = Modifier
                .height(64.dp)
                .weight(1f),
            label = { Text(text = "Username") },
            singleLine = true,
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = { onKeyboardNext() }
            ),
            placeholder = {
                Text(text = "Type your Username")
            }
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
fun PreviewEmptyUsernameTextField() {
    EldarWalletTheme {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            color = MaterialTheme.colorScheme.background
        ) {
            Column {
                Spacer(modifier = Modifier.height(16.dp))
                UsernameTextField(input = "", onTextChanged = {}, onKeyboardNext = {})
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
fun PreviewFilledUsernameTextField() {
    EldarWalletTheme {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            color = MaterialTheme.colorScheme.background
        ) {
            Column {
                Spacer(modifier = Modifier.height(16.dp))
                UsernameTextField(input = "SomeUsername", onTextChanged = {}, onKeyboardNext = {})
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}
