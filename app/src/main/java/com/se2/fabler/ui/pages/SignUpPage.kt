package com.se2.fabler.ui.pages

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AlternateEmail
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.se2.fabler.AppModel
import com.se2.fabler.R
import com.se2.fabler.getTestAppModel
import com.se2.fabler.models.SignUpData
import com.se2.fabler.ui.components.FormTextField

@SuppressLint("RememberReturnType")
@Composable
fun SignUpPage(app: AppModel) {
    Surface (modifier = Modifier.fillMaxSize()) {
        val signUpData = remember { mutableStateOf(SignUpData()) }

        Image(
            painter = painterResource(id = R.drawable.window_rain),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 30.dp)
        ) {
            Spacer(modifier = Modifier.height(150.dp))
            Image(
                painter = painterResource(id = R.drawable.app_logo),
                contentDescription = null,
                modifier = Modifier
                    .size(150.dp)
                    .clip(RoundedCornerShape(15.dp))
            )
            Spacer(modifier = Modifier.height(55.dp))
            FormTextField(
                value = signUpData.value.name,
                onChange = { data -> signUpData.value = signUpData.value.copy(name = data) },
                modifier = Modifier.fillMaxWidth()
                    .padding(bottom = 10.dp)
                    .clip(RoundedCornerShape(30.dp)),
                label = "Name",
                icon = Icons.Outlined.AccountCircle
            )
            FormTextField(
                value = signUpData.value.username,
                onChange = { data -> signUpData.value = signUpData.value.copy(username = data) },
                modifier = Modifier.fillMaxWidth()
                    .padding(bottom = 10.dp)
                    .clip(RoundedCornerShape(30.dp)),
                label = "Username",
                icon = Icons.Default.AlternateEmail
            )
            FormTextField(
                value = signUpData.value.email,
                onChange = { data -> signUpData.value = signUpData.value.copy(email = data) },
                modifier = Modifier.fillMaxWidth()
                    .padding(bottom = 10.dp)
                    .clip(RoundedCornerShape(30.dp)),
                label = "Email",
                icon = Icons.Outlined.Email
            )
            PasswordField(
                value = signUpData.value.password,
                onChange = { data -> signUpData.value = signUpData.value.copy(password = data) },
                submit = {},
                modifier = Modifier.fillMaxWidth()
                    .padding(bottom = 10.dp)
                    .clip(RoundedCornerShape(30.dp))
            )
            PasswordField(
                label = "Confirm Password",
                placeholder = "Enter your password again",
                value = signUpData.value.confirmPassword, // Change this line
                onChange = { data -> signUpData.value = signUpData.value.copy(confirmPassword = data) },
                submit = {},
                modifier = Modifier.fillMaxWidth()
                    .clip(RoundedCornerShape(30.dp))
            )
            Spacer(modifier = Modifier.height(20.dp))
            Button(
                onClick = {
                    if (signUpData.value.password == signUpData.value.confirmPassword) {
                        // TODO: Passwords match, proceed with sign up
                    } else {
                        // TODO: Passwords do not match, show an error message
                    }
                },
                enabled = signUpData.value.isNotEmpty(),
                shape = RoundedCornerShape(5.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Sign Up")
            }
            Spacer(modifier = Modifier.height(15.dp))
            HorizontalDivider()
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "Already have an account?",
                    color = Color.White,
                    fontWeight = FontWeight.Bold)
                TextButton(onClick = {
                    app.popView()
                }) {
                    Text(
                        text = "SIGN IN",
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSignUpPage() {
    SignUpPage(getTestAppModel())
}
