package com.example.afishaapp.ui.screen.entry

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.afishaapp.R

var email by mutableStateOf("")
var password by mutableStateOf("")

var emailIsError by mutableStateOf(false)
var passwordIsError by mutableStateOf(false)

var visibilityPassword by mutableStateOf(false)

@Preview(showSystemUi = true)
@Composable
fun EntryScreen() {
    Scaffold(
        topBar = { EntryTopAppBar {  } }
    ) { intentPadding ->
        Column(
            modifier = Modifier
                .padding(intentPadding)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text(text = stringResource(R.string.input_email_text)) },
                modifier = Modifier.padding(0.dp, 50.dp, 0.dp, 0.dp),
                isError = emailIsError,
                supportingText = {
                    if (emailIsError) Text(text = stringResource(R.string.invalid_email_text))
                },
                trailingIcon = {
                    if (emailIsError) {
                        Icon(
                            painter = painterResource(R.drawable.ic_error),
                            contentDescription = stringResource(R.string.invalid_email_text)
                        )
                    }
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
            )

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text(text = stringResource(R.string.input_password_text)) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                visualTransformation = if (visibilityPassword) VisualTransformation.None else PasswordVisualTransformation(),
                isError = passwordIsError,
                trailingIcon = {
                    val icon = if (passwordIsError)
                        painterResource(R.drawable.ic_error)
                    else if (visibilityPassword)
                        painterResource(R.drawable.ic_visibility)
                    else
                        painterResource(R.drawable.ic_visibility_off)

                    val contentDescription = if (passwordIsError)
                        stringResource(R.string.invalid_password)
                    else if (visibilityPassword)
                        stringResource(R.string.show_password_text)
                    else
                        stringResource(R.string.hide_password_text)

                    IconButton(onClick = { visibilityPassword = !visibilityPassword }) {
                        Icon(
                            painter = icon,
                            contentDescription = contentDescription
                        )
                    }
                },
                supportingText = {
                    if (passwordIsError) Text(text = stringResource(R.string.error_password_text))
                }
            )

            Button(
                onClick = {  },
                modifier = Modifier.padding(0.dp, 10.dp, 0.dp, 0.dp)
            ) {
                Text(text = stringResource(R.string.entry_button_text))
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EntryTopAppBar(onClick: () -> Unit) {
    CenterAlignedTopAppBar(
        title = { Text(text = stringResource(R.string.entry_text)) },
        navigationIcon = {
            IconButton(onClick = onClick) {
                Icon(
                    painter = painterResource(R.drawable.ic_arrow_back),
                    contentDescription = stringResource(R.string.back_text)
                )
            }
        },
    )
}