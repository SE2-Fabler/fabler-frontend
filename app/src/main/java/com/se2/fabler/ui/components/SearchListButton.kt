package com.se2.fabler.ui.components

import android.content.res.Resources
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.se2.fabler.R
import com.se2.fabler.ui.theme.AppColors

@Composable
fun SearchListButton(
    name: String,
    activatedName: String,
    icon: Int,
    activatedIcon: Int,
    active: Boolean
) {
    val activated = remember { mutableStateOf(active) }

    val buttonColor = if (activated.value) AppColors.PRIMARY_FONT_COLOR else AppColors.PRIMARY_COLOR
    val contentColor = if (activated.value) Color.White else AppColors.PRIMARY_FONT_COLOR
    val buttonText = if (activated.value) activatedName else name
    val buttonIcon = if (activated.value) activatedIcon else icon

    Button(
        onClick = { activated.value = !activated.value },
        colors = ButtonDefaults.buttonColors(
            containerColor = buttonColor,
            contentColor = contentColor,
            disabledContainerColor = Color.Gray,
            disabledContentColor = Color.White
        ),
        shape = RoundedCornerShape(14.dp),
        elevation = ButtonDefaults.buttonElevation(2.dp),
        contentPadding = PaddingValues(top = 0.dp, start = 12.dp, end = 12.dp, bottom = 0.dp),
        modifier = Modifier.height(28.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            if (buttonIcon != Resources.ID_NULL) {
                Icon(
                    painter = painterResource(buttonIcon),
                    contentDescription = null,
                    modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(4.dp)) // Add space between icon and text
            }
            Text(
                text = buttonText,
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewSearchListButton() {
    SearchListButton("Name", "Activated Name",
        icon = R.drawable.baseline_menu_book_36,
        activatedIcon = R.drawable.baseline_menu_book_36,
        active = false
    )
}