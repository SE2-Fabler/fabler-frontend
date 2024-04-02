package com.se2.fabler.ui.pages

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
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
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Key
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.se2.fabler.AppModel
import com.se2.fabler.R
import com.se2.fabler.getTestAppModel
import com.se2.fabler.models.CredentialsData

@Composable
fun UsernameField(
    value: String,
    onChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    label: String = "Username",
    placeholder: String = "Enter your username"
) {
    val focusManager = LocalFocusManager.current
    val leadingIcon = @Composable {
        Icon(
            imageVector = Icons.Default.Person,
            contentDescription = ""
        )
    }
    TextField(
        value = value,
        onValueChange = onChange,
        modifier = modifier,
        leadingIcon = leadingIcon,
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
        keyboardActions = KeyboardActions(
            onNext = { focusManager.moveFocus(FocusDirection.Down) }
        ),
        placeholder = { Text(placeholder) },
        label = { Text(label) },
        singleLine = true,
        visualTransformation = VisualTransformation.None
    )
}

@Composable
fun PasswordField(
    value: String,
    onChange: (String) -> Unit,
    submit: () -> Unit,
    modifier: Modifier = Modifier,
    label: String = "Password",
    placeholder: String = "Enter your Password"
) {

    val isPasswordVisible = remember { mutableStateOf(false) }
    val leadingIcon = @Composable {
        Icon(
            Icons.Default.Key,
            contentDescription = "",
            tint = MaterialTheme.colorScheme.primary
        )
    }
    val trailingIcon = @Composable {
        IconButton(onClick = { isPasswordVisible.value = !isPasswordVisible.value }) {
            Icon(
                if (isPasswordVisible.value) Icons.Default.VisibilityOff else Icons.Default.Visibility,
                contentDescription = "",
                tint = MaterialTheme.colorScheme.primary
            )
        }
    }
    TextField(
        value = value,
        onValueChange = onChange,
        modifier = modifier,
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Done,
            keyboardType = KeyboardType.Password
        ),
        keyboardActions = KeyboardActions(
            onDone = { submit() }
        ),
        placeholder = { Text(placeholder) },
        label = { Text(label) },
        singleLine = true,
        visualTransformation = if (isPasswordVisible.value) VisualTransformation.None else PasswordVisualTransformation()
    )
}

fun checkCredentials(credentials: CredentialsData, context: Context, app: AppModel): Boolean {
    val token = app.service.authUser(credentials)
    return if (credentials.isNotEmpty() && token != null) {
        app.loggedInUserData = app.service.getUserDataAll(token.id)
        app.pushView("HomePage")
        true
    } else {
        Toast.makeText(context, "Wrong Credentials", Toast.LENGTH_SHORT).show()
        false
    }
}

@SuppressLint("RememberReturnType")
@Composable
fun SignInPage(app: AppModel) {
    Surface (modifier = Modifier.fillMaxSize()) {
        val credentialsData = remember { mutableStateOf(CredentialsData()) }
        val context = LocalContext.current

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
            Spacer(modifier = Modifier.height(200.dp))
            UsernameField(
                value = credentialsData.value.username,
                onChange = { data -> credentialsData.value = credentialsData.value.copy(username = data) },
                modifier = Modifier.fillMaxWidth()
                    .padding(bottom = 10.dp)
                    .clip(RoundedCornerShape(30.dp))
            )
            PasswordField(
                value = credentialsData.value.password,
                onChange = { data -> credentialsData.value = credentialsData.value.copy(password = data) },
                submit = {
                    if (!checkCredentials(credentialsData.value, context, app)) credentialsData.value = CredentialsData()
                },
                modifier = Modifier.fillMaxWidth()
                    .clip(RoundedCornerShape(30.dp))
            )
            Row(verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End) {
                Checkbox(
                    checked = credentialsData.value.rememberMe,
                    onCheckedChange = { isChecked ->
                        credentialsData.value = credentialsData.value.copy(rememberMe = isChecked)
                    },
                    modifier = Modifier.padding(end = 8.dp)
                )
                Text(
                    text = "Remember Me",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray
                )
            }
            Spacer(modifier = Modifier.height(20.dp))
            Button(
                onClick = {
                    if (!checkCredentials(credentialsData.value, context, app)) credentialsData.value = CredentialsData()
                },
                enabled = credentialsData.value.isNotEmpty(),
                shape = RoundedCornerShape(5.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Sign In")
            }
            Spacer(modifier = Modifier.height(20.dp))
            HorizontalDivider()
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "Don't have an account?",
                    color = Color.White,
                    fontWeight = FontWeight.Bold)
                TextButton(onClick = {}) {
                    Text(
                        text = "SIGN UP",
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSignInPage() {
    SignInPage(getTestAppModel())
}
