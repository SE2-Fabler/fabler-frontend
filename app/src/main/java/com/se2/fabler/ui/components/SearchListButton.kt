package com.se2.fabler.ui.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.MenuBook
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.se2.fabler.ui.theme.AppColors

@Composable
fun SearchListButton(
    name: String,
    activatedName: String,
    icon: ImageVector,
    activatedIcon: ImageVector,
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
        shape = RoundedCornerShape(20.dp),
        elevation = ButtonDefaults.buttonElevation(2.dp),
        contentPadding = PaddingValues(top = 0.dp, start = 12.dp, end = 12.dp, bottom = 0.dp),
        modifier = Modifier.height(40.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = buttonIcon,
                contentDescription = null,
                modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = buttonText,
                style = MaterialTheme.typography.titleMedium,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewSearchListButton() {
    SearchListButton("Name", "Activated Name",
        icon = Icons.AutoMirrored.Filled.MenuBook,
        activatedIcon = Icons.AutoMirrored.Filled.MenuBook,
        active = false
    )
}
