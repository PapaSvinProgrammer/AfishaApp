package com.example.afishaapp.ui.screen.map

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.view.View
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.ContentTransform
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.BottomSheetScaffoldState
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import com.example.afishaapp.R
import com.example.afishaapp.ui.theme.DefaultPadding
import com.yandex.mapkit.Animation
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.map.CameraPosition
import com.yandex.mapkit.mapview.MapView
import androidx.core.graphics.createBitmap
import androidx.core.net.toUri
import com.example.afishaapp.app.navigation.PlaceRoute
import com.example.afishaapp.data.module.Coordinate
import com.example.afishaapp.ui.widget.material.AnimatedOutlineBox
import com.example.afishaapp.ui.widget.material.PlaceInformationSheetContent
import com.example.afishaapp.ui.widget.material.SquareSlider
import com.example.afishaapp.ui.widget.text.TitleBottomSheet
import com.yandex.mapkit.map.MapObjectTapListener
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.util.Locale

@SuppressLint("StaticFieldLeak")
private lateinit var context: Context
private lateinit var mapView: MapView
private lateinit var viewModel: MapViewModel
private lateinit var mainPoint: Point

@OptIn(ExperimentalMaterial3Api::class)
private lateinit var bottomSheetState: BottomSheetScaffoldState
private lateinit var scope: CoroutineScope
private lateinit var pagerState: PagerState

private const val ZOOM = 16.5f

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MapScreen(
    navController: NavController,
    mapViewModel: MapViewModel,
    placeId: Int,
    lat: Double,
    lon: Double
) {
    context = LocalContext.current
    viewModel = mapViewModel
    mainPoint = Point(lat, lon)

    val segmentedButtonsList = stringArrayResource(R.array.map_info_segmented_button)
    val derivedIndex by remember {
        derivedStateOf {
            viewModel.selectedIndex
        }
    }

    pagerState = rememberPagerState(pageCount = { viewModel.places.size })
    bottomSheetState = rememberBottomSheetScaffoldState()
    scope = rememberCoroutineScope()

    LaunchedEffect(viewModel.places) {
        if (viewModel.places.isNotEmpty()) {
            viewModel.addPlacemarkList(
                list = viewModel.places,
                bitmap = createBitmapFromVector(R.drawable.ic_pin),
                listener = placemarkListener
            )
        }
    }

    LaunchedEffect(viewModel.selectedIndex) {
        when (viewModel.selectedIndex) {
            1 -> prepareMapForSettingsAreaSheetContent(mainPoint)
        }
    }

    LaunchedEffect(viewModel.radius) {
        if (viewModel.selectedIndex == 1) {
            addCircleArea(
                radius = viewModel.radius,
                point = mainPoint
            )

            viewModel.getPlacesWithRadius(
                lat = lat,
                lon = lon,
                radius = viewModel.radius.toInt()
            )
        }
    }

    LaunchedEffect(Unit) {
        viewModel.getPlaceInfo(placeId)

        mapView.onStart()
        MapKitFactory.getInstance().onStart()

        viewModel.mapCollection = mapView.mapWindow.map.mapObjects

        moveToStartLocation(mainPoint)
    }

    DisposableEffect(Unit) {
        onDispose {
            mapView.onStop()
            MapKitFactory.getInstance().onStop()
        }
    }

    BottomSheetScaffold(
        modifier = Modifier.fillMaxSize(),
        scaffoldState = bottomSheetState,
        sheetPeekHeight = 100.dp,
        sheetContent = {
            Column(
                modifier = Modifier
                    .navigationBarsPadding()
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                SingleChoiceSegmentedButtonRow {
                    segmentedButtonsList.forEachIndexed { index, s ->
                        SegmentedButton(
                            shape = SegmentedButtonDefaults.itemShape(
                                index = index,
                                count = segmentedButtonsList.size,
                                baseShape = RoundedCornerShape(8.dp)
                            ),
                            onClick = { viewModel.updateSelectedIndex(index) },
                            icon = { },
                            label = { Text(text = s) },
                            selected = index == derivedIndex
                        )
                    }
                }

                AnimatedContent(
                    targetState = viewModel.selectedIndex,
                    transitionSpec = {
                        if (viewModel.selectedIndex == 1) {
                            ContentTransform(
                                targetContentEnter = slideIntoContainer(
                                    towards = AnimatedContentTransitionScope.SlideDirection.Start,
                                    animationSpec = tween(300)
                                ) + fadeIn(),
                                initialContentExit = slideOutOfContainer(
                                    towards = AnimatedContentTransitionScope.SlideDirection.Start,
                                    animationSpec = tween(300)
                                ) + fadeOut()
                            )
                        }
                        else {
                            ContentTransform(
                                targetContentEnter = slideIntoContainer(
                                    towards = AnimatedContentTransitionScope.SlideDirection.End,
                                    animationSpec = tween(300)
                                ) + fadeIn(),
                                initialContentExit = slideOutOfContainer(
                                    towards = AnimatedContentTransitionScope.SlideDirection.End,
                                    animationSpec = tween(300)
                                ) + fadeOut()
                            )
                        }
                    }
                ) { index ->
                    when (index) {
                        0 ->
                            PlaceInformationSheetContent(
                                pagerState = pagerState,
                                list = viewModel.places,
                                mapButtonClick = { navigateToYandexMaps(it.coordinates) },
                                moreButtonClick = { navController.navigate(PlaceRoute(it.id)) },
                                moveToPoint = {
                                    scope.launch {
                                        bottomSheetState.bottomSheetState.partialExpand()
                                    }
                                    moveToStartLocation(it)
                                }
                            )
                        1 -> SettingAreaSheetContent()
                    }
                }
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            AndroidView(
                factory = { context ->
                    View.inflate(context, R.layout.map_fragment, null)
                },
                update = { view ->
                    mapView = view.findViewById(R.id.map_view)
                }
            )

            Box(
                modifier = Modifier
                    .statusBarsPadding()
                    .padding(
                        start = DefaultPadding,
                        top = 10.dp
                    )
            ) {
                ElevatedButton(
                    onClick = { navController.popBackStack() },
                    shape = CircleShape,
                    contentPadding = PaddingValues(0.dp),
                    modifier = Modifier.size(48.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White,
                        contentColor = Color.Black
                    )
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Default.ArrowBack,
                        contentDescription = stringResource(R.string.ic_arrow_back_content_description)
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
private val placemarkListener = MapObjectTapListener { mapObject, _ ->
    val place = viewModel.placemarkMap[mapObject]

    scope.launch { bottomSheetState.bottomSheetState.expand() }
    viewModel.updateSelectedIndex(0)

    scope.launch {
        val index = viewModel.places.indexOf(place)
        pagerState.animateScrollToPage(index)
    }

    true
}

@Composable
private fun SettingAreaSheetContent() {
    Column(
        modifier = Modifier
            .padding(horizontal = DefaultPadding)
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        Spacer(modifier = Modifier.height(20.dp))
        TitleBottomSheet(stringResource(R.string.setting_area))

        SquareSlider(
            value = viewModel.radius,
            onValueChange = { viewModel.updateRadius(it) },
            steps = 10,
            valueRange = 100f..1000f
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            val formattedNum = "%.1f".format(Locale.ENGLISH, viewModel.radius / 100)

            Text(
                text = "$formattedNum км.",
                fontSize = 15.sp,
                fontWeight = FontWeight.Medium
            )

            OutlinedButton(
                onClick = { resetSettings() }
            ) {
                Text(
                    text = stringResource(R.string.reset_settings),
                    color = Color.Black
                )
            }
        }

        Spacer(modifier = Modifier.height(10.dp))
        AnimatedOutlineBox(stringResource(R.string.filtering)) { }
    }
}

private fun resetSettings() {
    viewModel.deleteCircleArea()
    viewModel.deleteAllPlacemark()
    viewModel.updateRadius(0f)
    viewModel.resetToDefaultPlace()
}

private fun prepareMapForSettingsAreaSheetContent(point: Point) {
    if (viewModel.radius <= 100) {
        addCircleArea(
            point = point,
            radius = 100f
        )

        viewModel.getPlacesWithRadius(
            lat = point.latitude,
            lon = point.longitude,
            radius = 100
        )
    }
}

private fun addCircleArea(point: Point, radius: Float) {
    viewModel.addCircleArea(
        point = point,
        radius = radius,
        strokeColor = ContextCompat.getColor(context, R.color.red),
        fillColor = ContextCompat.getColor(context, R.color.red_alpha)
    )
}

private fun moveToStartLocation(point: Point) {
    mapView.mapWindow.map.move(
        CameraPosition(point, ZOOM, 0f, 0f),
        Animation(Animation.Type.SMOOTH, 3f),
        null
    )
}

private fun createBitmapFromVector(id: Int): Bitmap? {
    val drawable = ContextCompat.getDrawable(context, id) ?: return null
    val bitmap = createBitmap(drawable.intrinsicWidth, drawable.intrinsicHeight)

    val canvas = Canvas(bitmap)
    drawable.setBounds(0, 0, canvas.width, canvas.height)
    drawable.draw(canvas)

    return bitmap
}

private fun navigateToYandexMaps(coordinate: Coordinate) {
    val url = generateUrl(coordinate)

    val intent = Intent(
        Intent.ACTION_VIEW,
        url.toUri()
    )

    context.startActivity(intent)
}

private fun generateUrl(coordinate: Coordinate): String {
    val main = "https://yandex.ru/maps/"
    var url = ""

    url += main
    url += "?rtext=${coordinate.lat},${coordinate.lon}"

    return url
}