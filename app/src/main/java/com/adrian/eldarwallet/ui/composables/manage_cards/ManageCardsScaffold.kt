package com.adrian.eldarwallet.ui.composables.manage_cards

import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.adrian.commons.model.Response
import com.adrian.eldarwallet.presentation.CardsViewModel
import com.adrian.eldarwallet.presentation.model.CardItem
import com.adrian.eldarwallet.ui.composables.navigation.Destination
import com.adrian.eldarwallet.ui.theme.EldarWalletTheme

@Composable
fun ManageCardsScaffold(
    cardsViewModel: CardsViewModel = hiltViewModel(),
    navController: NavController
) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .safeDrawingPadding(),
        color = MaterialTheme.colorScheme.background
    ) {
        val cardsState by cardsViewModel.cards.collectAsState()

        if (cardsViewModel.isInitialState()) {
            cardsViewModel.fetchCards()
        }

        when (cardsState) {
            is Response.Loading -> {
                ManageCardsScreen(
                    isLoading = (cardsState as Response.Loading<List<CardItem>>).isLoading,
                    onAddCard = { navController.navigate(Destination.ADD_CARD) }
                )
            }
            is Response.Failure -> {

            }
            is Response.Success -> {
                ManageCardsScreen(
                    cards = (cardsState as Response.Success).data,
                    onAddCard = { navController.navigate(Destination.ADD_CARD) }
                )
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
fun PreviewManageCards_WithCards() {
    EldarWalletTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            ManageCardsScreen(
                cards = listOf(
                    CardItem(id = 1, number = "1111 2222 3333 4444", cvv = 123, expiry = "2027/04"),
                    CardItem(id = 1, number = "3456 2222 3333 4444", cvv = 223, expiry = "2027/04"),
                    CardItem(id = 1, number = "4151 2222 3333 4444", cvv = 123, expiry = "2028/04"),
                    CardItem(id = 1, number = "5111 2222 3333 4444", cvv = 123, expiry = "2027/04")
                ),
                onAddCard = { }
            )
        }
    }
}