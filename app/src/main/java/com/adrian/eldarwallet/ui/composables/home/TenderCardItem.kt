package com.adrian.eldarwallet.ui.composables.home

import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import com.adrian.eldarwallet.R
import com.adrian.eldarwallet.presentation.model.CardItem
import com.adrian.eldarwallet.ui.theme.EldarWalletTheme

@Composable
fun TenderCardItem(cardItem: CardItem) {
    Row(
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
    ) {
        Spacer(modifier = Modifier.width(16.dp))

        Column {
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = when (cardItem.number[0]) {
                    '3' -> "American Express"
                    '4' -> "Visa"
                    '5' -> "MasterCard"
                    else -> "Other"
                },
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = cardItem.number,
                fontSize = 20.sp,
            )
            Text(
                text = cardItem.expiry,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.outline
            )
            Spacer(modifier = Modifier.height(12.dp))
        }
        Spacer(modifier = Modifier.weight(1f))

        Column {
            Spacer(modifier = Modifier.height(40.dp))
            Image(
                painter = painterResource(id = R.drawable.arrow_forward_ios_24dp),
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.outline),
                contentDescription = "Forward arrow",
            )
        }

        Spacer(modifier = Modifier.width(16.dp))
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
                    number = "1111 2222 3333 4444",
                    cvv = 123,
                    expiry = "2027/08"
                ))
            }
        }
    }
}