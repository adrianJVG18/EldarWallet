package com.adrian.eldarwallet.ui.composables.home

import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.adrian.eldarwallet.R
import com.adrian.eldarwallet.ui.theme.EldarWalletTheme

@Composable
fun RectangleBoxItem(
    label: String,
    onClick: () -> Unit = { },
    horizontalMargin: Int = 0
) {
    Row(
        modifier = Modifier
            .padding(horizontal =  horizontalMargin.dp)
    ) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .clickable {
                    onClick.invoke()
                }
                .border(
                    border = BorderStroke(
                        width = 2.dp,
                        color = MaterialTheme.colorScheme.outline
                    ),
                    shape = RectangleShape
                )
        ) {
            val (text, icon) = createRefs()

            Text(
                text = label,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.constrainAs(text) {
                    top.linkTo(parent.top, margin = 12.dp)
                    bottom.linkTo(parent.bottom, margin = 12.dp)
                    start.linkTo(parent.start, margin = (8 + horizontalMargin).dp)
                }
            )

            Image(
                painter = painterResource(id = R.drawable.arrow_forward_ios_24dp),
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.outline),
                contentDescription = "Forward arrow",
                modifier = Modifier.constrainAs(icon) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    end.linkTo(parent.end, margin = 16.dp)
                }
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
private fun PreviewManageCardsButton() {
    EldarWalletTheme {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            color = MaterialTheme.colorScheme.background
        ) {
            RectangleBoxItem(
                label = "Manage Cards",
                horizontalMargin = 8
            )
        }
    }
}