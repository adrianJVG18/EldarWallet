package com.adrian.eldarwallet.ui.composables.manage_cards

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.adrian.eldarwallet.R
import com.adrian.eldarwallet.ui.composables.utils.DefaultEmptyState

@Composable
fun ManageCardsEmptyState(
    onAddCard: (() -> Unit)? = null
) {
    ConstraintLayout {

        DefaultEmptyState(message = "You don't have associated cards,\nyet")

        onAddCard?.let {
            val (fabAddButton) = createRefs()

            FloatingActionButton(
                onClick = { it.invoke() },
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
    }
}