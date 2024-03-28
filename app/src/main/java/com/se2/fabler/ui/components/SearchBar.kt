package com.se2.fabler.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.se2.fabler.R
import com.se2.fabler.ui.theme.AppColors.Companion.PRIMARY_FONT_COLOR
import com.se2.fabler.ui.theme.AppColors.Companion.PRIMARY_COLOR
import com.se2.fabler.ui.theme.AppColors.Companion.SECONDARY_COLOR

@Composable
fun SearchBar(onSearchToggle: () -> Unit) {
    Surface(modifier = Modifier.background(SECONDARY_COLOR)) {
        var text by rememberSaveable { mutableStateOf("") }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .background(SECONDARY_COLOR)
        ) {
            OutlinedTextField(value = text,
                onValueChange = {text = it },
                placeholder = { Text("Search", color = Color.Gray) },
                shape = RoundedCornerShape(20.dp),
                colors = OutlinedTextFieldDefaults.colors (
                    focusedBorderColor = PRIMARY_FONT_COLOR,
                    unfocusedBorderColor = PRIMARY_COLOR,
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = SECONDARY_COLOR
                ),
                leadingIcon = {
                Image(
                    painter = painterResource(id = R.drawable.back_arrow),
                    contentDescription = "Back",
                    modifier = Modifier
                        .padding(10.dp)
                        .clickable(onClick = onSearchToggle)
                )
            }, trailingIcon = {
                Image(
                    painter = painterResource(id = R.drawable.baseline_search_36),
                    contentDescription = "Search",
                    modifier = Modifier.padding(10.dp)
                )
            }, modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp))
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun PreviewSearchBar() {
    SearchBar {}
}
