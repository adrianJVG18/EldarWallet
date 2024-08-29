package com.adrian.eldarwallet.ui.composables.utils

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintLayoutScope
import com.adrian.eldarwallet.R
import com.adrian.eldarwallet.ui.theme.EldarWalletTheme

@Composable
fun ConstraintLayoutScope.DefaultEmptyState(
    message: String,
) {
    val (icon, label) = createRefs()

    Image(
        painter = painterResource(id = R.drawable.search_off_24dp),
        colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.outline),
        contentDescription = "Forward arrow",
        modifier = Modifier
            .width(125.dp)
            .height(125.dp)
            .constrainAs(icon) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                bottom.linkTo(parent.bottom)
            }
    )

    Text(
        text = message,
        fontSize = 22.sp,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center,
        modifier = Modifier.constrainAs(label) {
            top.linkTo(icon.bottom, margin = 16.dp)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }
    )
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
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            color = MaterialTheme.colorScheme.background
        ) {
            ConstraintLayout {
                DefaultEmptyState(
                    message = "You don't have associated cards,\nyet"
                )
            }
        }
    }
}