package com.example.afishaapp.ui.screen.start

import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.afishaapp.R
import com.example.afishaapp.app.navigation.EntryRoute
import com.example.afishaapp.app.navigation.HomeRoute
import com.example.afishaapp.app.navigation.RegistrationRoute
import com.example.afishaapp.ui.theme.acidFontFamily
import com.vk.id.onetap.compose.onetap.sheet.OneTapBottomSheet
import com.vk.id.onetap.compose.onetap.sheet.rememberOneTapBottomSheetState
import com.yandex.authsdk.YandexAuthLoginOptions
import com.yandex.authsdk.YandexAuthOptions
import com.yandex.authsdk.YandexAuthResult
import com.yandex.authsdk.YandexAuthSdk
import kotlinx.coroutines.launch

@Composable
fun StartScreen(
    navController: NavController,
    viewModel: StartViewModel
) {
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    if (viewModel.authSuccess) {
        navController.navigate(HomeRoute)
    }

    Scaffold(
        snackbarHost = {
            SnackbarHost(snackbarHostState)
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Column {
                Image(
                    painter = painterResource(R.drawable.start_poster),
                    contentDescription = null,
                    modifier = Modifier.fillMaxWidth().height(400.dp)
                )

                Text(
                    text = "Найдется и кино, и музыка, и театр",
                    fontFamily = acidFontFamily,
                    fontSize = 32.sp,
                    lineHeight = 30.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 40.dp)
                )
            }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth().align(Alignment.BottomCenter)
            ) {
                Button(
                    onClick = { navController.navigate(EntryRoute) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(40.dp, 30.dp, 40.dp, 10.dp),
                    colors = ButtonDefaults.buttonColors(Color.Black),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Text(
                        text = stringResource(R.string.entry_text),
                        color = Color.White
                    )
                }

                Button(
                    onClick = { navController.navigate(RegistrationRoute) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(40.dp, 0.dp, 40.dp, 10.dp),
                    colors = ButtonDefaults.buttonColors(Color.Black),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Text(
                        text = stringResource(R.string.registration_text),
                        color = Color.White
                    )
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(bottom = 10.dp)
                ) {
                    HorizontalDivider(
                        thickness = 1.dp,
                        color = Color.Gray,
                        modifier = Modifier.weight(1f)
                    )

                    Text(
                        text = "Войти с помощью",
                        color = Color.Gray,
                        modifier = Modifier.weight(2f),
                        textAlign = TextAlign.Center,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium
                    )

                    HorizontalDivider(
                        thickness = 1.dp,
                        color = Color.Gray,
                        modifier = Modifier.weight(1f)
                    )
                }

                SupportRegistrationRow(
                    padding = innerPadding,
                    onSuccess = { token ->
                        viewModel.updateAuthStatus(true)
                    },
                    onFail = {
                        scope.launch {
                            snackbarHostState.showSnackbar("Вход не выполнен")
                        }
                    }
                )
            }
        }
    }
}

@Composable
private fun SupportRegistrationRow(
    padding: PaddingValues,
    onSuccess: (String) -> Unit,
    onFail: () -> Unit
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    val googleAuthClient = GoogleAuthClient(LocalContext.current)
    val vkBottomSheetState = rememberOneTapBottomSheetState()

    val sdk = remember { YandexAuthSdk.create(YandexAuthOptions(context)) }
    val launcher = rememberLauncherForActivityResult(sdk.contract) {
        handleYandexResult(
            yandexResult = it,
            onSuccess = onSuccess,
            onFail = onFail
        )
    }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(15.dp),
        modifier = Modifier.padding(bottom = padding.calculateBottomPadding() + 35.dp)
    ) {
        IconButton(
            onClick = { vkBottomSheetState.show() },
            modifier = Modifier.padding(vertical = 10.dp).size(45.dp)
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_vk),
                contentDescription = null,
                tint = Color.Unspecified,
                modifier = Modifier.size(34.dp)
            )
        }

        IconButton(
            onClick = {
                scope.launch {
                    val res = googleAuthClient.signIn()

                    if (res == null) {
                        onFail.invoke()
                    }
                    else if (res.isNotEmpty()) {
                        onSuccess.invoke(res)
                    }
                }
            },
            modifier = Modifier
                .clip(shape = RoundedCornerShape(8.dp))
                .background(Color.White)

        ) {
            Icon(
                painter = painterResource(R.drawable.ic_google),
                contentDescription = null,
                tint = Color.Unspecified,
                modifier = Modifier.size(32.dp)
            )
        }

        IconButton(
            onClick = { launcher.launch(YandexAuthLoginOptions()) },
            modifier = Modifier
                .clip(shape = RoundedCornerShape(8.dp))
                .background(Color.White)

        ) {
            Icon(
                painter = painterResource(R.drawable.ic_yandex),
                contentDescription = null,
                tint = Color.Unspecified,
                modifier = Modifier.size(38.dp)
            )
        }
    }

    OneTapBottomSheet(
        state = vkBottomSheetState,
        serviceName = "HUi",
        onAuth = { oAuth, accessToken ->
            Log.d("RRRR", accessToken.userData.toString())
        },
        onFail = { _, _ ->
            Log.d("RRRR", "FAIL")
        }
    )
}

private fun handleYandexResult(
    yandexResult: YandexAuthResult,
    onSuccess: (String) -> Unit,
    onFail: () -> Unit
) {
    when (yandexResult) {
        YandexAuthResult.Cancelled -> { }
        is YandexAuthResult.Failure -> onFail.invoke()
        is YandexAuthResult.Success -> onSuccess.invoke(yandexResult.token.value)
    }
}