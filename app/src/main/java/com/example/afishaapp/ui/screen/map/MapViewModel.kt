package com.example.afishaapp.ui.screen.map

import android.graphics.Bitmap
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.afishaapp.data.module.place.Place
import com.example.afishaapp.domain.http.GetPlace
import com.yandex.mapkit.geometry.Circle
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.map.CircleMapObject
import com.yandex.mapkit.map.MapObjectCollection
import com.yandex.mapkit.map.PlacemarkMapObject
import com.yandex.runtime.image.ImageProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class MapViewModel @Inject constructor(
    private val getPlace: GetPlace
): ViewModel() {
    lateinit var mapCollection: MapObjectCollection
    private var circleMapObject: CircleMapObject? = null
    private val placemarkList = mutableListOf<PlacemarkMapObject>()

    var place by mutableStateOf<Place?>(null)
        private set
    var selectedIndex by mutableIntStateOf(0)
        private set

    var radius by mutableFloatStateOf(100f)
        private set

    fun getPlaceInfo(placeId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            place = getPlace.getPlaceShortInfo(placeId)
        }
    }

    fun updateSelectedIndex(index: Int) {
        selectedIndex = index
    }

    fun updateRadius(radius: Float) {
        this.radius = radius
    }

    fun addCircleArea(
        point: Point,
        radius: Float,
        strokeColor: Int,
        fillColor: Int
    ) {
        val circle = Circle(
            point,
            radius
        )

        if (circleMapObject == null) {
            circleMapObject = mapCollection.addCircle(circle).apply {
                this.strokeWidth = 2f
                this.strokeColor = strokeColor
                this.fillColor = fillColor
            }
        }
        else {
            circleMapObject!!.geometry = circle
        }
    }

    fun deleteCircleArea() {
        circleMapObject?.let {
            mapCollection.remove(it)
        }

        circleMapObject = null
    }

    fun addPlacemarkList(list: List<Point>, bitmap: Bitmap?) {
        placemarkList.forEach {
            mapCollection.remove(it)
        }

        placemarkList.clear()

        list.forEach {
            val placemark = mapCollection.addPlacemark()
            placemark.geometry = it
            placemark.setIcon(ImageProvider.fromBitmap(bitmap))

            placemarkList.add(placemark)
        }
    }

    fun addPlacemark(point: Point, bitmap: Bitmap?) {
        mapCollection.addPlacemark().apply {
            geometry = point
            setIcon(ImageProvider.fromBitmap(bitmap))
        }
    }
}