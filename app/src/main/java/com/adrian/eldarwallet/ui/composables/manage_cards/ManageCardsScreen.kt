package com.adrian.eldarwallet.ui.composables.manage_cards

import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintLayoutScope
import com.adrian.eldarwallet.R
import com.adrian.eldarwallet.presentation.model.CardItem
import com.adrian.eldarwallet.ui.composables.utils.DefaultEmptyState
import com.adrian.eldarwallet.ui.theme.EldarWalletTheme

@Composable
fun ManageCardsScreen(
    isLoading: Boolean = false,
    cards: List<CardItem> = emptyList(),
    onAddCard: (() -> Unit)? = null
) {
    if (cards.isEmpty()) {
        ConstraintLayout {
            DefaultEmptyState(message = "You don't have associated cards,\nyet")
            onAddCard?.let {
                FloatingAddButton(it)
            }
        }
    } else {
        ConstraintLayout {
            LazyColumn {
                cards.forEach {
                    item {
                        TenderCardItem(cardItem = it)
                    }
                }
            }
            onAddCard?.let {
                FloatingAddButton(it)
            }
        }
    }
}

@Composable
private fun ConstraintLayoutScope.FloatingAddButton(
    onAddCard: () -> Unit = { }
) {
    val (fabAddButton) = createRefs()

    FloatingActionButton(
        onClick = { onAddCard.invoke() },
        modifier = Modifier.constrainAs(fabAddButton) {
            bottom.linkTo(parent.bottom, margin = 16.dp)
            end.linkTo(parent.end, margin = 16.dp)
        }
    ) {
        Icon(
            painter = painterResource(id = R.drawable.add_24dp),
            contentDescription = "Addition icon",
            modifier = Modifier
                .width(32.dp)
                .height(32.dp)
        )
    }

}

@Preview(name = "Light theme", showBackground = true)
@Preview(
    name = "Dark theme",
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun PreviewManageCards() {
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
                onAddCard = {}
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
fun PreviewDefaultEmptyState() {
    EldarWalletTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            ManageCardsScreen(
                cards = emptyList(),
                onAddCard = {}
            )
        }
    }
}