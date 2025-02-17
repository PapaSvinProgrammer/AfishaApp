package com.example.afishaapp.ui.screen.registration

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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.afishaapp.R

private var email by mutableStateOf("")
private var password by mutableStateOf("")
private var checkPassword by mutableStateOf("")

private var visibilityPassword by mutableStateOf(false)
private var visibilityCheckPassword by mutableStateOf(false)

private var isErrorPassword by mutableStateOf(false)
private var isErrorCheckPassword by mutableStateOf(false)
private var isErrorEmail by mutableStateOf(false)

@Composable
fun RegistrationScreen(
    navController: NavController
) {
    Scaffold(
        topBar = {
            DefaultTopAppBar(text = stringResource(R.string.registration_text))  {
                navController.popBackStack()
            }
        }
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
                label = { Text(stringResource(R.string.input_email_text)) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                singleLine = true,
                modifier = Modifier.padding(0.dp, 50.dp, 0.dp, 0.dp),
                supportingText = {
                    if (isErrorEmail) Text(text = stringResource(R.string.invalid_email_text))
                },
                trailingIcon = {
                    if (isErrorEmail) {
                        Icon(
                            painter = painterResource(R.drawable.ic_error),
                            contentDescription = stringResource(R.string.invalid_email_text)
                        )
                    }
                },
                isError = isErrorEmail
            )

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text(text = stringResource(R.string.input_password_text)) },
                visualTransformation = if (visibilityPassword) VisualTransformation.None else PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                singleLine = true,
                trailingIcon = {
                    val icon = if (isErrorPassword)
                        painterResource(R.drawable.ic_error)
                    else if (visibilityPassword)
                        painterResource(R.drawable.ic_visibility)
                    else
                        painterResource(R.drawable.ic_visibility_off)

                    val contentDescription = if (isErrorPassword)
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
                isError = isErrorPassword,
                supportingText = {
                    if (isErrorPassword) Text(text = stringResource(R.string.error_password_text))
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
                    val icon = if (isErrorCheckPassword)
                        painterResource(R.drawable.ic_error)
                    else if (visibilityCheckPassword)
                        painterResource(R.drawable.ic_visibility)
                    else
                        painterResource(R.drawable.ic_visibility_off)

                    val contentDescription = if (isErrorCheckPassword)
                        stringResource(R.string.passwords_donе_match_text)
                    else if (visibilityCheckPassword)
                        stringResource(R.string.show_password_text)
                    else
                        stringResource(R.string.hide_password_text)

                    IconButton(onClick = { visibilityCheckPassword = !visibilityCheckPassword }) {
                        Icon(
                            painter = icon,
                            contentDescription = contentDescription
                        )
                    }
                },
                isError = isErrorCheckPassword,
                supportingText = {
                    if (isErrorCheckPassword) Text(text = stringResource(R.string.error_check_password_text))
                }
            )

            Button(
                onClick = {

                },
                modifier = Modifier.padding(0.dp, 10.dp, 0.dp, 0.dp)
            ) {
                Text(stringResource(R.string.registration_button_text))
            }
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
                    painter = painterResource(R.drawable.ic_arrow_back),
                    contentDescription = "Back"
                )
            }
        }
    )
}