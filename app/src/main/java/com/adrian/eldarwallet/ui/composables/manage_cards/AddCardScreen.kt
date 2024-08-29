package com.adrian.eldarwallet.ui.composables.manage_cards

import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.adrian.eldarwallet.ui.theme.EldarWalletTheme

@Composable
fun AddCardScreen(
    cardNumberInput: String = "",
    cvvInput: String = "",
    expiryInput: String = "",
    onUpdateCardNumber: (String) -> Unit = { },
    onUpdateCvv: (String) -> Unit = { },
    onUpdateExpiry: (String) -> Unit = { },
    validCardValidation: () -> Boolean = { true },
    onCreateCard: () -> Unit = { }
) {
    ConstraintLayout {
        val (cardNumber, cvv, expiry, createButton) = createRefs()
        val focusManager = LocalFocusManager.current

        // Card Number Field
        OutlinedTextField(
            value = cardNumberInput,
            onValueChange = { onUpdateCardNumber.invoke(it) },
            label = { Text(text = "Card Number") },
            singleLine = true,
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = { focusManager.moveFocus(FocusDirection.Down) }
            ),
            modifier = Modifier
                .wrapContentHeight()
                .constrainAs(cardNumber) {
                    top.linkTo(parent.top, margin = 8.dp)
                    start.linkTo(parent.start, margin = 16.dp)
                    end.linkTo(parent.end, margin = 16.dp)
                    width = Dimension.fillToConstraints
                }
        )

        // Expiration date Field
        OutlinedTextField(
            value = expiryInput,
            onValueChange = {onUpdateExpiry.invoke(it)},
            label = { Text(text = "Expiration date") },
            singleLine = true,
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = { focusManager.moveFocus(FocusDirection.Down) }
            ),
            modifier = Modifier
                .wrapContentHeight()
                .width(200.dp)
                .constrainAs(expiry) {
                    top.linkTo(cardNumber.bottom, margin = 8.dp)
                    start.linkTo(parent.start, margin = 16.dp)
                }
        )

        // CVV field
        OutlinedTextField(
            value = cvvInput,
            onValueChange = {onUpdateCvv.invoke(it)},
            label = { Text(text = "CVV") },
            singleLine = true,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = { focusManager.moveFocus(FocusDirection.Down) }
            ),
            modifier = Modifier
                .wrapContentHeight()
                .width(100.dp)
                .constrainAs(cvv) {
                    top.linkTo(expiry.bottom, margin = 8.dp)
                    start.linkTo(parent.start, margin = 16.dp)
                }
        )

        Button(
            enabled = validCardValidation.invoke(),
            onClick = { onCreateCard.invoke() },
            modifier = Modifier
                .constrainAs(createButton) {
                    bottom.linkTo(parent.bottom, margin = 16.dp)
                    start.linkTo(parent.start, margin = 16.dp)
                    end.linkTo(parent.end, margin = 16.dp)
                }
                .wrapContentSize()
        ) {
            Text(
                text = "Add Card",
                fontSize = 20.sp
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
fun PreviewAddCardScreen() {
    EldarWalletTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            AddCardScreen()
        }
    }
}

