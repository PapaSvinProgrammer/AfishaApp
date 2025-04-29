package com.example.afishaapp.ui.screen.map

import android.graphics.Bitmap
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.afishaapp.data.module.QueryParameters
import com.example.afishaapp.data.module.place.Place
import com.example.afishaapp.domain.http.GetPlace
import com.yandex.mapkit.geometry.Circle
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.map.CircleMapObject
import com.yandex.mapkit.map.MapObjectCollection
import com.yandex.mapkit.map.MapObjectTapListener
import com.yandex.mapkit.map.PlacemarkMapObject
import com.yandex.runtime.image.ImageProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class MapViewModel @Inject constructor(
    private val getPlace: GetPlace
): ViewModel() {
    var mapCollection: MapObjectCollection? = null

    private var circleMapObject: CircleMapObject? = null
    private var defaultPlace: Place? = null

    private val _placemarkMap = mutableMapOf<PlacemarkMapObject, Place>()
    val placemarkMap: Map<PlacemarkMapObject, Place> = _placemarkMap

    var places by mutableStateOf<List<Place>>(listOf())
        private set
    var selectedIndex by mutableIntStateOf(0)
        private set

    var radius by mutableFloatStateOf(100f)
        private set

    fun getPlacesWithRadius(lat: Double, lon: Double, radius: Int) {
        val queryParameters = QueryParameters(
            lat = lat,
            lon = lon,
            radius = radius
        )

        viewModelScope.launch(Dispatchers.IO) {
            val placesNearbyResponse = getPlace.getPlacesWithRadius(queryParameters)

            placesNearbyResponse?.let {
                places = it.results
            }
        }
    }

    fun getPlaceInfo(placeId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            defaultPlace = getPlace.getPlaceShortInfo(placeId)

            defaultPlace?.let {
                places = listOf(it)
            }
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
            circleMapObject = mapCollection?.addCircle(circle)?.apply {
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
            mapCollection?.remove(it)
        }

        circleMapObject = null
    }

    fun resetToDefaultPlace() {
        defaultPlace?.let {
            places = listOf(it)
        }
    }

    fun addPlacemarkList(
        list: List<Place>,
        bitmap: Bitmap?,
        listener: MapObjectTapListener
    ) {
        mapCollection?.let { collection ->
            deleteAllPlacemark()

            list.forEach { place ->
                place.coordinates?.let {
                    val point = Point(place.coordinates.lat, place.coordinates.lon)
                    val placemark = collection.addPlacemark().apply {
                        geometry = point
                        setIcon(ImageProvider.fromBitmap(bitmap))
                        addTapListener(listener)
                    }

                    _placemarkMap[placemark] = place
                }
            }
        }
    }

    fun deleteAllPlacemark() {
        placemarkMap.keys.forEach {
            mapCollection?.remove(it)
        }

        _placemarkMap.clear()
    }
}