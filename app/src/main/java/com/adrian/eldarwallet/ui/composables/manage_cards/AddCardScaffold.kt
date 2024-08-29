package com.adrian.eldarwallet.ui.composables.manage_cards

import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.adrian.eldarwallet.presentation.CardsViewModel
import com.adrian.eldarwallet.ui.theme.EldarWalletTheme

@Composable
fun AddCardScaffold (
    cardsViewModel: CardsViewModel = hiltViewModel(),
    navController: NavController
){
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .safeDrawingPadding(),
        color = MaterialTheme.colorScheme.background
    ) {
        with(cardsViewModel) {
            AddCardScreen(
                cardNumberInput = typedCardNumber,
                cvvInput = typedCvv,
                expiryInput = typedExpirationDate,
                onUpdateCardNumber = { updateTypedCardNumber(it) },
                onUpdateCvv = { updateTypedCvv(it) },
                onUpdateExpiry = { updateTypedExpirationDate(it) },
                validCardValidation = { isCardDataValid() },
                onCreateCard = { addCard() }
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
fun PreviewAddCardScaffold() {
    EldarWalletTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            AddCardScreen()
        }
    }
}