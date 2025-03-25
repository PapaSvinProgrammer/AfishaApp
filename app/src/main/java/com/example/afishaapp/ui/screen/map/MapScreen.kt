package com.example.afishaapp.ui.screen.map

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.view.View
import androidx.compose.foundation.gestures.snapping.SnapPosition
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
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
import com.yandex.runtime.image.ImageProvider
import androidx.core.graphics.createBitmap
import androidx.core.net.toUri
import com.example.afishaapp.app.navigation.PlaceRoute
import com.example.afishaapp.app.support.ConvertInfo
import com.example.afishaapp.app.support.metro
import com.example.afishaapp.data.module.Coordinate
import com.example.afishaapp.data.module.image.ImageItem
import com.example.afishaapp.data.module.place.Place
import com.example.afishaapp.ui.widget.card.ImageCard

@SuppressLint("StaticFieldLeak")
private lateinit var context: Context
private lateinit var mapView: MapView

private const val ZOOM = 16.5f

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MapScreen(
    navController: NavController,
    viewModel: MapViewModel,
    placeId: Int,
    lat: Double,
    lon: Double
) {
    context = LocalContext.current
    val segmentedButtonsList = stringArrayResource(R.array.map_info_segmented_button)
    var selectedIndex by remember { mutableIntStateOf(0) }

    LaunchedEffect(Unit) {
        viewModel.getPlaceInfo(placeId)

        mapView.onStart()
        MapKitFactory.getInstance().onStart()

        moveToStartLocation(Point(lat, lon))
        setMarkerInStartLocation(Point(lat, lon))
    }

    DisposableEffect(Unit) {
        onDispose {
            mapView.onStop()
            MapKitFactory.getInstance().onStop()
        }
    }

    BottomSheetScaffold(
        modifier = Modifier.fillMaxSize(),
        sheetPeekHeight = 100.dp,
        sheetContent = {
            Column(
                modifier = Modifier.navigationBarsPadding().fillMaxWidth(),
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
                            onClick = { selectedIndex = index },
                            icon = { },
                            label = { Text(text = s) },
                            selected = index == selectedIndex
                        )
                    }
                }

                when (selectedIndex) {
                    0 -> InformationSheetContent(navController, viewModel.place)
                    1 -> SettingAreaSheetContent(viewModel.place)
                }

            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier.fillMaxSize().padding(innerPadding)
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

@Composable
private fun InformationSheetContent(navController: NavController, place: Place?) {
    place?.let {
        Spacer(modifier = Modifier.height(20.dp))

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = DefaultPadding),
            text = ConvertInfo.convertTitle(place.title),
            textAlign = TextAlign.Start,
            fontSize = 17.sp,
            fontWeight = FontWeight.Bold
        )

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = 10.dp,
                    start = DefaultPadding,
                    end = DefaultPadding
                ),
            text = "Адрес: ${place.address}",
            textAlign = TextAlign.Start,
            fontSize = 15.sp
        )

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = DefaultPadding),
            text = "Номер: ${place.phone}",
            textAlign = TextAlign.Start,
            fontSize = 15.sp
        )

        MetroRow(place)
        ImagesRow(place.images)
        ControlButtonsRow(navController, place)
    }
}

@Composable
private fun MetroRow(place: Place) {
    Row(
        modifier = Modifier
            .padding(
                top = 5.dp,
                start = DefaultPadding,
                end = DefaultPadding
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        val icon = metro[place.location] ?: R.drawable.ic_error

        Icon(
            painter = painterResource(icon),
            contentDescription = null,
            tint = Color.Unspecified,
            modifier = Modifier.size(20.dp)
        )

        Text(
            modifier = Modifier.fillMaxWidth().padding(start = 10.dp),
            text = place.subway,
            textAlign = TextAlign.Start,
            fontSize = 15.sp
        )
    }
}

@Composable
private fun ControlButtonsRow(navController: NavController, place: Place) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                top = 20.dp,
                start = DefaultPadding,
                end = DefaultPadding
            ),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Button(
            onClick = {
                navigateToYandexMaps(place.coordinates)
            },
            modifier = Modifier.fillMaxWidth().weight(1f),
            shape = RoundedCornerShape(10.dp)
        ) {
            Text(text = "Маршрут")
        }

        Button(
            onClick = {
                navigateToPlaceScreen(navController, place.id)
            },
            modifier = Modifier.fillMaxWidth().weight(1f),
            shape = RoundedCornerShape(10.dp)
        ) {
            Text(text = "Подробнее")
        }
    }
}

@Composable
private fun ImagesRow(images: List<ImageItem>?) {
    val listState = rememberLazyListState()
    val flingBehavior = rememberSnapFlingBehavior(listState, SnapPosition.Start)

    if (!images.isNullOrEmpty()) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    vertical = 20.dp,
                    horizontal = DefaultPadding
                ),
            text = stringResource(R.string.images),
            textAlign = TextAlign.Start,
            fontWeight = FontWeight.Bold,
            fontSize = 17.sp
        )

        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            contentPadding = PaddingValues(horizontal = DefaultPadding),
            state = listState,
            flingBehavior = flingBehavior
        ) {
            items(images) {
                ImageCard(it.thumbnails.highImage)
            }
        }
    }
}

@Composable
private fun SettingAreaSheetContent(place: Place?) {

}

private fun moveToStartLocation(point: Point) {
    mapView.mapWindow.map.move(
        CameraPosition(point, ZOOM, 0f, 0f),
        Animation(Animation.Type.SMOOTH, 3f),
        null
    )
}

private fun setMarkerInStartLocation(point: Point) {
    val bitMap = createBitmapFromVector(R.drawable.ic_pin)
    val mapObjectCollection = mapView.mapWindow.map.mapObjects

    mapObjectCollection.addPlacemark().apply {
        geometry = point
        setIcon(ImageProvider.fromBitmap(bitMap))
    }
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

private fun navigateToPlaceScreen(navController: NavController, id: Int) {
    navController.navigate(
        PlaceRoute(
            placeId = id
        )
    )
}