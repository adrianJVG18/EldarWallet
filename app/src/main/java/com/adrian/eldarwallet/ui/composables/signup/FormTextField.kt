package com.adrian.eldarwallet.ui.composables.signup

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp

@Composable
fun FormTextField(
    input: String,
    label: String,
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
            label = { Text(text = label) },
            singleLine = true,
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = { onKeyboardNext() }
            )
        )
        Spacer(modifier = Modifier.width(8.dp))
    }

}