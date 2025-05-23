package com.example.afishaapp.ui.screen.startSetting

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieClipSpec
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.afishaapp.R
import com.example.afishaapp.app.navigation.HomeRoute
import com.example.afishaapp.app.utils.CategoryNode
import com.example.afishaapp.app.utils.categoryList
import com.example.afishaapp.app.utils.convertData.ConvertDate
import com.example.afishaapp.ui.theme.DefaultPadding
import com.example.afishaapp.ui.widget.material.AnimateBackgroundLayout
import com.example.afishaapp.ui.widget.material.DatePickerFieldToModal
import com.example.afishaapp.ui.widget.material.HorizontalLinePagerIndicator
import kotlinx.coroutines.launch

@Composable
fun StartSettingScreen(
    navController: NavController,
    viewModel: StartSettingViewModel
) {
    LaunchedEffect(Lifecycle.Event.ON_START) {
        viewModel.getCities()
    }

    val pagerState = rememberPagerState { 3 }

    AnimateBackgroundLayout(currentPage = pagerState.currentPage) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxSize()
        ) {
            when (it) {
                0 -> CountryStartPage(viewModel)
                1 -> CategoryStartPage(viewModel)
                2 -> DatePickerStartPage(viewModel)
            }
        }

        TextButton(
            modifier = Modifier
                .statusBarsPadding()
                .align(Alignment.TopEnd),
            onClick = { navController.navigate(HomeRoute) }
        ) {
            Text(
                text = stringResource(R.string.skip),
                color = Color.Black
            )
        }

        BottomNavigationRow(
            modifier = Modifier.align(Alignment.BottomCenter),
            pagerState = pagerState,
            onSuccess = {
                navController.navigate(HomeRoute) { popUpTo(navController.graph.id) }
            }
        )
    }
}

@Composable
private fun BottomNavigationRow(
    pagerState: PagerState,
    modifier: Modifier,
    onSuccess: () -> Unit
) {
    val scope = rememberCoroutineScope()

    Box(
        modifier = modifier
            .navigationBarsPadding()
            .fillMaxWidth()
            .padding(DefaultPadding)
    ) {
        HorizontalLinePagerIndicator(
            state = pagerState,
            modifier = Modifier.align(Alignment.Center)
        )

        IconButton(
            modifier = Modifier.align(Alignment.CenterEnd),
            onClick = {
                if (pagerState.currentPage == 2) {
                    onSuccess()
                }
                else {
                    scope.launch {
                        pagerState.animateScrollToPage(pagerState.currentPage + 1)
                    }
                }
            }
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Default.ArrowForward,
                contentDescription = null,
                tint = Color.Black
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CountryStartPage(viewModel: StartSettingViewModel) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.anim_world))
    var isExpanded by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(40.dp))
        LottieAnimation(
            modifier = Modifier
                .fillMaxWidth()
                .height(450.dp),
            composition = composition,
            clipSpec = LottieClipSpec.Progress(min = 0f, max = 0.5f)
        )

        TitlePage(
            title = stringResource(R.string.select_city),
            description = stringResource(R.string.select_city_description)
        )

        Spacer(modifier = Modifier.height(30.dp))

        ExposedDropdownMenuBox(
            expanded = isExpanded,
            onExpandedChange = { isExpanded = it }
        ) {
            OutlinedTextField(
                modifier = Modifier.menuAnchor(MenuAnchorType.PrimaryNotEditable),
                value = viewModel.selectedCity,
                readOnly = true,
                onValueChange = { viewModel.updateSelectedCity(it) },
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Filled.ArrowDropDown,
                        contentDescription = null,
                        modifier = Modifier.rotate(if (isExpanded) 180f else 0f),
                        tint = Color.Black
                    )
                },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = Color.Black,
                    focusedBorderColor = Color.Black
                )
            )

            ExposedDropdownMenu(
                expanded = isExpanded,
                onDismissRequest = { isExpanded = false }
            ) {
                viewModel.cities.forEach {
                    DropdownMenuItem(
                        text = { Text(text = it.name) },
                        onClick = {
                            viewModel.saveCity(it)
                            viewModel.updateSelectedCity(it.name)
                            isExpanded = false
                        }
                    )
                }
            }
        }
    }
}

@Composable
private fun TitlePage(
    title: String,
    description: String
) {
    Text(
        text = title,
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold,
        color = Color.Black
    )

    Spacer(modifier = Modifier.height(10.dp))

    Text(
        text = description,
        textAlign = TextAlign.Center,
        fontSize = 15.sp,
        color = Color.Black
    )
}

@Composable
private fun CategoryStartPage(viewModel: StartSettingViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .padding(horizontal = DefaultPadding)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(80.dp))
        TitlePage(
            title = stringResource(R.string.favorite_category),
            description = stringResource(R.string.favorite_category_description)
        )

        Spacer(modifier = Modifier.height(DefaultPadding))
        GenerateCategoryList(
            selectedCategory = viewModel.selectedCategory,
            onClick = {
                viewModel.selectedCategory[it.slug] = it.slug !in viewModel.selectedCategory
            }
        )
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun GenerateCategoryList(
    selectedCategory: MutableMap<String, Boolean>,
    onClick: (CategoryNode) -> Unit
) {
    FlowRow(
        maxItemsInEachRow = 3,
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        categoryList.forEach {
            val isClicked = selectedCategory.getOrDefault(it.slug, false)

            BorderCard(
                modifier = Modifier.weight(1f),
                isClicked = isClicked,
                onClick = { onClick(it) },
                content = {
                    Column(
                        modifier = Modifier.padding(vertical = DefaultPadding),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        val color = if (isClicked) MaterialTheme.colorScheme.primary else Color.Black

                        Icon(
                            painter = painterResource(it.icon),
                            contentDescription = null,
                            tint = color,
                            modifier = Modifier.size(40.dp)
                        )

                        Spacer(modifier = Modifier.height(5.dp))

                        Text(
                            text = it.title,
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth().padding(horizontal = 5.dp),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            color = color
                        )
                    }
                }
            )
        }
    }
}

@Composable
private fun BorderCard(
    modifier: Modifier = Modifier,
    isClicked: Boolean = false,
    content: @Composable () -> Unit,
    onClick: () -> Unit = {}
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(10.dp))
            .fillMaxWidth()
            .border(
                width = if (isClicked) 2.dp else 1.dp ,
                color = if (isClicked) MaterialTheme.colorScheme.primary else Color.Black,
                shape = RoundedCornerShape(10.dp)
            )
            .clickable { onClick() }
    ) { content() }
}

@Composable
private fun DatePickerStartPage(viewModel: StartSettingViewModel) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.anim_geometry))

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(40.dp))

        LottieAnimation(
            modifier = Modifier
                .fillMaxWidth()
                .height(450.dp),
            composition = composition,
            clipSpec = LottieClipSpec.Progress(min = 0f, max = 0.5f)
        )

        TitlePage(
            title = stringResource(R.string.date_born),
            description = stringResource(R.string.date_born_description)
        )

        Spacer(modifier = Modifier.height(30.dp))

        DatePickerFieldToModal(
            color = Color.Black,
            dateSelected = viewModel.selectedDate,
            onDateSelected = { millis ->
                val dateStr = millis?.let { ConvertDate.convertMillisToDate(it) } ?: ""
                viewModel.updateSelectedDate(dateStr)
            }
        )
    }
}