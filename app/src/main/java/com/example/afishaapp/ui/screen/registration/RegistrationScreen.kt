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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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

@Composable
fun RegistrationScreen(
    navController: NavController,
    viewModel: RegistrationViewModel
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        DefaultTopAppBar(text = stringResource(R.string.registration_text))  {
            navController.popBackStack()
        }

        OutlinedTextField(
            value = viewModel.email,
            onValueChange = { viewModel.updateEmail(it) },
            label = { Text(stringResource(R.string.input_email_text)) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            singleLine = true,
            modifier = Modifier.padding(0.dp, 50.dp, 0.dp, 0.dp),
            supportingText = {
                if (viewModel.errorEmail)
                    Text(text = stringResource(R.string.invalid_email_text))
            },
            trailingIcon = {
                if (viewModel.errorEmail) {
                    Icon(
                        painter = painterResource(R.drawable.ic_error),
                        contentDescription = stringResource(R.string.invalid_email_text)
                    )
                }
            },
            isError = viewModel.errorEmail,
        )

        OutlinedTextField(
            value = viewModel.password,
            onValueChange = { viewModel.updatePassword(it) },
            label = { Text(text = stringResource(R.string.input_password_text)) },
            visualTransformation = if (viewModel.visibilityPassword)
                VisualTransformation.None
            else
                PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            singleLine = true,
            trailingIcon = {
                val icon = if (viewModel.errorPassword)
                    painterResource(R.drawable.ic_error)
                else if (viewModel.visibilityPassword)
                    painterResource(R.drawable.ic_visibility)
                else
                    painterResource(R.drawable.ic_visibility_off)

                val contentDescription = if (viewModel.errorPassword)
                    stringResource(R.string.invalid_password)
                else if (viewModel.visibilityPassword)
                    stringResource(R.string.show_password_text)
                else
                    stringResource(R.string.hide_password_text)

                IconButton(
                    onClick = {
                        viewModel.updateVisibilityPassword(
                            state = !viewModel.visibilityPassword
                        )
                    }
                ) {
                    Icon(
                        painter = icon,
                        contentDescription = contentDescription
                    )
                }
            },
            isError = viewModel.errorPassword,
            supportingText = {
                if (viewModel.errorPassword)
                    Text(text = stringResource(R.string.error_password_text))
            }
        )

        OutlinedTextField(
            value = viewModel.checkPassword,
            onValueChange = { viewModel.updateCheckPassword(it) },
            label = { Text(text = stringResource(R.string.check_password_text)) },
            visualTransformation = if (viewModel.visibilityCheckPassword)
                VisualTransformation.None
            else
                PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            singleLine = true,
            trailingIcon = {
                val icon = if (viewModel.errorCheckPassword)
                    painterResource(R.drawable.ic_error)
                else if (viewModel.visibilityCheckPassword)
                    painterResource(R.drawable.ic_visibility)
                else
                    painterResource(R.drawable.ic_visibility_off)

                val contentDescription = if (viewModel.errorCheckPassword)
                    stringResource(R.string.passwords_donе_match_text)
                else if (viewModel.visibilityCheckPassword)
                    stringResource(R.string.show_password_text)
                else
                    stringResource(R.string.hide_password_text)

                IconButton(
                    onClick = {
                        viewModel.updateVisibilityCheckPassword(
                            state = !viewModel.visibilityCheckPassword
                        )
                    }
                ) {
                    Icon(
                        painter = icon,
                        contentDescription = contentDescription
                    )
                }
            },
            isError = viewModel.errorCheckPassword,
            supportingText = {
                if (viewModel.errorCheckPassword)
                    Text(text = stringResource(R.string.error_check_password_text))
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