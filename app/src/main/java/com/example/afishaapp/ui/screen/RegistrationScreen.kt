package com.example.afishaapp.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material.icons.rounded.ArrowBack
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
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.example.afishaapp.R

@Composable
fun RegistrationScreen() {
    Scaffold(
        topBar = {
            DefaultTopAppBar(text = stringResource(R.string.registration_text))  { }
        }
    ) { intentPadding ->
        Column(
            modifier = Modifier
                .padding(intentPadding)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            var email by remember { mutableStateOf("") }
            var password by remember { mutableStateOf("") }
            var checkPassword by remember { mutableStateOf("") }
            var visibilityPassword by remember { mutableStateOf(false) }
            var visibilityCheckPassword by remember { mutableStateOf(false) }

            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text(stringResource(R.string.input_email_text)) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                singleLine = true
            )

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text(text = stringResource(R.string.input_password_text)) },
                visualTransformation = if (visibilityPassword) VisualTransformation.None else PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                singleLine = true,
                trailingIcon = {
                    val icon = if (visibilityPassword)
                        Icons.Filled.Visibility
                    else
                        Icons.Filled.VisibilityOff

                    val contentDescription = if (visibilityCheckPassword)
                        stringResource(R.string.show_password_text)
                    else
                        stringResource(R.string.hide_password_text)

                    IconButton(onClick = { visibilityPassword = !visibilityPassword }) {
                        Icon(
                            icon,
                            contentDescription = contentDescription
                        )
                    }
                }
            )

            OutlinedTextField(
                value = checkPassword,
                onValueChange = { checkPassword = it },
                label = { Text(text = stringResource(R.string.check_password_text)) },
                visualTransformation = if (visibilityCheckPassword) VisualTransformation.None else PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                singleLine = true,
                trailingIcon = {
                    val icon = if (visibilityCheckPassword)
                        Icons.Filled.Visibility
                    else
                        Icons.Filled.VisibilityOff
                    val contentDescription = if (visibilityCheckPassword)
                        stringResource(R.string.show_password_text)
                    else
                        stringResource(R.string.hide_password_text)

                    IconButton(onClick = {visibilityCheckPassword = !visibilityCheckPassword}) {
                        Icon(
                            icon,
                            contentDescription = contentDescription
                        )
                    }
                }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DefaultTopAppBar(text: String, navigationIconListener: () -> Unit) {
    CenterAlignedTopAppBar(
        title = { Text(text) },
        navigationIcon = {
            IconButton(
                onClick = navigationIconListener
            ) {
                Icon(
                    Icons.Rounded.ArrowBack,
                    contentDescription = "Back"
                )
            }
        }
    )
}