package com.example.afishaapp.ui.screen.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.afishaapp.R
import com.example.afishaapp.app.navigation.NavRoutes
import com.example.afishaapp.ui.screen.cityBottomSheet.CityBottomSheet
import com.example.afishaapp.ui.theme.acidFontFamily

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel
) {
    viewModel.getCity()
    viewModel.getDefaultCity()

    CenterAlignedTopAppBar(
        navigationIcon = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(10.dp, 0.dp, 0.dp, 0.dp)
                    .clip(CircleShape)
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = ripple(),
                        onClick = {
                            viewModel.cityBottomSheetState.value = true
                        }
                    )
            ) {
                Text(
                    text = viewModel.defaultCity,
                    fontFamily = acidFontFamily,
                    fontSize = 22.sp,
                    modifier = Modifier.padding(5.dp, 0.dp, 0.dp, 0.dp)
                )
                Icon(
                    painter = painterResource(R.drawable.ic_keyboard_arrow_down),
                    contentDescription = "City list",
                    modifier = Modifier.padding(5.dp, 0.dp)
                )
            }
        },
        actions = {
            IconButton(onClick = { navController.navigate(NavRoutes.ACCOUNT.name) }) {
                Icon(
                    painter = painterResource(R.drawable.ic_account),
                    contentDescription = stringResource(R.string.ic_account_content_description)
                )
            }
        },
        title = { Text(text = "") }
    )

    CityBottomSheet(
        visibilityState = viewModel.cityBottomSheetState,
        data = viewModel.city,
        onClick = {
            viewModel.updateDefaultCity(it)
        }
    )
}