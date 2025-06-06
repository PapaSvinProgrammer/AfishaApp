package com.example.afishaapp.ui.screen.entry

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
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
import com.example.afishaapp.app.navigation.HomeRoute
import com.example.afishaapp.ui.widget.text.TitleTopBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EntryScreen(
    navController: NavController,
    viewModel: EntryViewModel
) {
    if (viewModel.isEntry) {
        navController.navigate(HomeRoute) {
            popUpTo(navController.graph.id)
        }
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { TitleTopBar(stringResource(R.string.entry_text)) },
                navigationIcon = {
                    IconButton(
                        onClick = { navController.popBackStack() }
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Default.ArrowBack,
                            contentDescription = stringResource(R.string.arrow_back_description)
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier.padding(innerPadding)
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                OutlinedTextField(
                    value = viewModel.emailValue,
                    shape = RoundedCornerShape(10.dp),
                    onValueChange = { viewModel.updateEmail(it) },
                    label = { Text(text = stringResource(R.string.input_email_text)) },
                    modifier = Modifier.padding(top = 50.dp),
                    isError = viewModel.errorEmail || viewModel.entryError,
                    supportingText = {
                        if (viewModel.errorEmail)
                            Text(text = stringResource(R.string.invalid_email_text))
                        else if (viewModel.entryError)
                            Text(text = stringResource(R.string.entry_error))
                    },
                    trailingIcon = {
                        if (viewModel.errorEmail) {
                            Icon(
                                painter = painterResource(R.drawable.ic_error),
                                contentDescription = stringResource(R.string.invalid_email_text)
                            )
                        }
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                    singleLine = true
                )

                OutlinedTextField(
                    value = viewModel.passwordValue,
                    shape = RoundedCornerShape(10.dp),
                    onValueChange = { viewModel.updatePassword(it) },
                    label = { Text(text = stringResource(R.string.input_password_text)) },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                    visualTransformation = if (viewModel.visibilityPassword)
                        VisualTransformation.None
                    else
                        PasswordVisualTransformation(),
                    isError = viewModel.errorPassword || viewModel.entryError,
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

                        IconButton(onClick = { viewModel.updateVisibilityPassword(!viewModel.visibilityPassword) }) {
                            Icon(
                                painter = icon,
                                contentDescription = contentDescription
                            )
                        }
                    },
                    supportingText = {
                        if (viewModel.errorPassword)
                            Text(text = stringResource(R.string.error_password_text))
                    },
                    singleLine = true
                )

                Button(
                    onClick = { viewModel.entry() },
                    enabled = !viewModel.visibilityProgressBar,
                    modifier = Modifier
                        .padding(
                            vertical = 10.dp,
                            horizontal = 40.dp
                        )
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.onSurface
                    )
                ) {
                    Text(
                        text = stringResource(R.string.entry_button_text)
                    )
                }
            }

            if (viewModel.visibilityProgressBar) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    }
}