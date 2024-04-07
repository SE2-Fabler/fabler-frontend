package com.se2.fabler.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import com.se2.fabler.ui.theme.AppColors.Companion.PRIMARY_FONT_COLOR
import com.se2.fabler.ui.theme.AppColors.Companion.SECONDARY_COLOR


@Composable
fun BackAndTextHeader(text: String, onBackClick: () -> Unit) {
    Surface(
        Modifier
            .fillMaxWidth()
            .background(SECONDARY_COLOR)
    ) {
        Column(
            modifier = Modifier.background(SECONDARY_COLOR)
                .padding(15.dp, 0.dp, 15.dp, 0.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(0.dp, 10.dp, 0.dp, 10.dp)) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = Color.DarkGray,
                    modifier = Modifier
                        .size(36.dp)
                        .clickable(onClick = onBackClick)
                )
                Spacer(modifier = Modifier.width(16.dp))
                Text (
                    text = text,
                    color = PRIMARY_FONT_COLOR,
                    fontSize = TextUnit(24f, TextUnitType.Sp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewBackAndTextHeader() {
    BackAndTextHeader("Test Header", {} )
}
