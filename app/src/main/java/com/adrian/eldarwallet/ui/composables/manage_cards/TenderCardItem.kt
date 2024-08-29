package com.adrian.eldarwallet.ui.composables.manage_cards

import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.adrian.eldarwallet.R
import com.adrian.eldarwallet.presentation.model.CardItem
import com.adrian.eldarwallet.ui.theme.EldarWalletTheme

@Composable
fun TenderCardItem(
    cardItem: CardItem,
    onCardClick: (CardItem) -> Unit = { },
    onDeleteClick: (CardItem) -> Unit = { }
) {
    ConstraintLayout(
        modifier = Modifier
            .padding(all = 8.dp)
            .border(
                border = BorderStroke(
                    width = 2.dp,
                    color = MaterialTheme.colorScheme.outline
                ),
                shape = RoundedCornerShape(16.dp)
            )
            .fillMaxWidth()
            .wrapContentHeight()
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .clickable {
                onCardClick.invoke(cardItem)
            }
    ) {
        val (bank, cardNumber, expiry, deleteIcon) = createRefs()

        Text(
            text = when (cardItem.number[0]) {
                '3' -> "American Express"
                '4' -> "Visa"
                '5' -> "MasterCard"
                else -> "Other"
            },
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.constrainAs(bank) {
                top.linkTo(parent.top, margin = 12.dp)
                start.linkTo(parent.start, margin = 16.dp)
            }
        )
        Text(
            text = cardItem.number,
            fontSize = 20.sp,
            modifier = Modifier.constrainAs(cardNumber) {
                top.linkTo(bank.bottom, margin = 2.dp)
                start.linkTo(parent.start, margin = 16.dp)
            }
        )
        Text(
            text = cardItem.expiry,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.outline,
            modifier = Modifier.constrainAs(expiry) {
                top.linkTo(cardNumber.bottom, margin = 2.dp)
                end.linkTo(cardNumber.end)
                bottom.linkTo(parent.bottom, margin = 12.dp)
            }
        )

        Image(
            painter = painterResource(id = R.drawable.delete_24dp),
            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.error),
            contentDescription = "Forward arrow",
            modifier = Modifier
                .width(36.dp)
                .height(72.dp)
                .clickable {
                    onDeleteClick.invoke(cardItem)
                }
                .constrainAs(deleteIcon) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    end.linkTo(parent.end, margin = 16.dp)
                }
        )
    }
}

@Preview(name = "Light theme", showBackground = true)
@Preview(
    name = "Dark theme",
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
private fun PreviewAvailableCardsCard_Visa() {
    EldarWalletTheme {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            color = MaterialTheme.colorScheme.background
        ) {
            Column {
                TenderCardItem(cardItem = CardItem(
                    id = 1,
                    number = "4111 2222 3333 4444",
                    cvv = 123,
                    expiry = "2027/08"
                ))
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
private fun PreviewAvailableCardsCard_Mastercard() {
    EldarWalletTheme {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            color = MaterialTheme.colorScheme.background
        ) {
            Column {
                TenderCardItem(cardItem = CardItem(
                    id = 1,
                    number = "5111 2222 3333 4444",
                    cvv = 123,
                    expiry = "2027/08"
                ))
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
private fun PreviewAvailableCardsCard_Amex() {
    EldarWalletTheme {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            color = MaterialTheme.colorScheme.background
        ) {
            Column {
                TenderCardItem(cardItem = CardItem(
                    id = 1,
                    number = "3111 2222 3333 4444",
                    cvv = 123,
                    expiry = "2027/08"
                ))
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
private fun PreviewAvailableCardsCard_Other() {
    EldarWalletTheme {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            color = MaterialTheme.colorScheme.background
        ) {
            Column {
                TenderCardItem(cardItem = CardItem(
                    id = 1,
                    number = "1111 2222 3333 4444",
                    cvv = 123,
                    expiry = "2027/08"
                ))
            }
        }
    }
}