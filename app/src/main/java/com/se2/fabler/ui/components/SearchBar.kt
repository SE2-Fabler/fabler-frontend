package com.se2.fabler.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.se2.fabler.ui.theme.AppColors.Companion.PRIMARY_COLOR
import com.se2.fabler.ui.theme.AppColors.Companion.PRIMARY_FONT_COLOR
import com.se2.fabler.ui.theme.AppColors.Companion.SECONDARY_COLOR

@Composable
fun SearchBar(onSearchClick: (String) -> Unit, onBackClick: () -> Unit) {
    Surface(modifier = Modifier.background(SECONDARY_COLOR)) {
        var text by rememberSaveable { mutableStateOf("") }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .background(SECONDARY_COLOR)
        ) {
            OutlinedTextField(value = text,
                onValueChange = { text = it },
                placeholder = { Text("Search", color = Color.Gray) },
                shape = RoundedCornerShape(20.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = PRIMARY_FONT_COLOR,
                    unfocusedBorderColor = PRIMARY_COLOR,
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = SECONDARY_COLOR
                ),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back",
                        tint = Color.DarkGray,
                        modifier = Modifier
                            .size(36.dp)
                            .clickable(onClick = onBackClick)
                    )
                }, trailingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search",
                        tint = Color.DarkGray,
                        modifier = Modifier.size(36.dp)
                            .clickable(onClick = { onSearchClick(text) })
                    )
                }, modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun PreviewSearchBar() {
    SearchBar({}, {})
}
