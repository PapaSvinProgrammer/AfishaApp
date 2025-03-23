package com.example.afishaapp.ui.screen.map

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.view.View
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
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

@SuppressLint("StaticFieldLeak")
private lateinit var context: Context
private lateinit var mapView: MapView
private const val ZOOM = 16.5f

@Composable
fun MapScreen(
    navController: NavController,
    lat: Double,
    lon: Double
) {
    context = LocalContext.current

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

    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(bottom = innerPadding.calculateBottomPadding())
                .fillMaxSize()
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
                modifier = Modifier.padding(
                    start = DefaultPadding,
                    top = innerPadding.calculateTopPadding() + 10.dp
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