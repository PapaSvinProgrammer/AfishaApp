package com.example.afishaapp.ui.screen.map

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.util.Log
import android.view.View
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.draw.clip
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
import com.example.afishaapp.app.support.ConvertInfo
import com.example.afishaapp.app.support.metro
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

    viewModel.getPlaceInfo(placeId)

    LaunchedEffect(Unit) {
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
        sheetContent = { SheetContent(viewModel) }
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
private fun InformationSheetContent(place: Place?) {
    Log.d("RRRR", place.toString())

    place?.let {
        Spacer(modifier = Modifier.height(20.dp))

        Text(
            modifier = Modifier.fillMaxWidth(),
            text = ConvertInfo.convertTitle(it.title),
            textAlign = TextAlign.Start,
            fontSize = 17.sp,
            fontWeight = FontWeight.Bold
        )

        Text(
            modifier = Modifier.fillMaxWidth().padding(top = 10.dp),
            text = "Адрес: ${it.address}",
            textAlign = TextAlign.Start,
            fontSize = 15.sp
        )

        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "Номер: ${it.phone}",
            textAlign = TextAlign.Start,
            fontSize = 15.sp
        )

        Row(
            modifier = Modifier.padding(top = 5.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            val icon = metro[it.location] ?: R.drawable.ic_error

            Icon(
                painter = painterResource(icon),
                contentDescription = null,
                tint = Color.Unspecified,
                modifier = Modifier.size(20.dp)
            )

            Text(
                modifier = Modifier.fillMaxWidth().padding(start = 10.dp),
                text = it.subway,
                textAlign = TextAlign.Start,
                fontSize = 15.sp
            )
        }

        if (!it.images.isNullOrEmpty()) {
            Text(
                modifier = Modifier.fillMaxWidth().padding(vertical = 20.dp),
                text = stringResource(R.string.images),
                textAlign = TextAlign.Start,
                fontWeight = FontWeight.Bold,
                fontSize = 17.sp
            )

            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(it.images) {
                    ImageCard(it.image)
                }
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth().padding(top = 20.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Button(
                onClick = { },
                modifier = Modifier.fillMaxWidth().weight(1f),
                shape = RoundedCornerShape(10.dp)
            ) {
                Text(text = "Маршрут")
            }

            Button(
                onClick = { },
                modifier = Modifier.fillMaxWidth().weight(1f),
                shape = RoundedCornerShape(10.dp)
            ) {
                Text(text = "Подробнее")
            }
        }
    }
}

@Composable
private fun SettingAreaSheetContent(viewModel: MapViewModel) {

}

@Composable
private fun SheetContent(viewModel: MapViewModel) {
    val segmentedButtonsList = stringArrayResource(R.array.map_info_segmented_button)
    var selectedIndex by remember { mutableIntStateOf(0) }

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
            0 -> InformationSheetContent(viewModel.place)
            1 -> SettingAreaSheetContent(viewModel)
        }
    }
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