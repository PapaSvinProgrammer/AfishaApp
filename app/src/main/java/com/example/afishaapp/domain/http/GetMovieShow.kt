package com.example.afishaapp.domain.http

import com.example.afishaapp.data.module.QueryParameters
import com.example.afishaapp.data.module.movieShow.MovieShow
import com.example.afishaapp.data.module.movieShow.MovieShowResponse
import com.example.afishaapp.data.module.movieShow.PriceTime
import com.example.afishaapp.data.module.movieShow.Show
import com.example.afishaapp.domain.repository.http.MovieRepository
import java.time.LocalDate
import java.time.ZoneId
import javax.inject.Inject

private const val ACTUAL_SINCE = "actual_since"
private const val ACTUAL_UNTIL = "actual_until"

class GetMovieShow @Inject constructor(
    private val movieRepository: MovieRepository
) {
    private var resultFlag = false
    private var nextPage = 1

    suspend fun execute(
        queryParameters: QueryParameters,
        plusDays: Long
    ): List<Show> {
        val result = arrayListOf<Show>()
        val map = HashMap<Int, Int>()

        nextPage = 1
        resultFlag = false

        if (queryParameters.id <= 0 || queryParameters.locationSlug.isEmpty()) {
            return listOf()
        }

        val dateBorder = calculateDateBorder(plusDays)
        var newQueryParameters = createNewQueryParameters(queryParameters, dateBorder)

        var showResponse = movieRepository.getMovieShows(newQueryParameters)

        while (!resultFlag) {
            if (showResponse == null) {
                result.sort()
                return result
            }

            result.prepareShows(showResponse, map)

            if (showResponse.next.isNullOrEmpty()) {
                result.sort()
                return result
            }

            nextPage += 1
            newQueryParameters = createNewQueryParameters(queryParameters, dateBorder)

            showResponse = movieRepository.getMovieShows(newQueryParameters)
        }

        result.sort()
        return result
    }

    private fun ArrayList<Show>.sort() {
        this.forEach {
            it.priceTime.sortBy { priceTime ->
                priceTime.time
            }
        }
    }

    private fun ArrayList<Show>.prepareShows(showResponse: MovieShowResponse, map: HashMap<Int, Int>) {

        showResponse.results.forEach { movieShow ->
            val index = map.getOrDefault(movieShow.place.id, -1)

            if (index == -1) {
                this.createNewShowObject(movieShow, map)
            }
            else {
                this.updateShowObject(movieShow, index)
            }
        }
    }

    private fun ArrayList<Show>.createNewShowObject(movieShow: MovieShow, map: HashMap<Int, Int>) {
        val priceTime = PriceTime(
            price = movieShow.price.toString(),
            time = movieShow.dateTime
        )

        this.add(
            Show(
                id = movieShow.id,
                place = movieShow.place,
                priceTime = arrayListOf(priceTime)
            )
        )

        map[movieShow.place.id] = this.size - 1
    }

    private fun ArrayList<Show>.updateShowObject(movieShow: MovieShow, index: Int) {
        val priceTime = PriceTime(
            price = movieShow.price.toString(),
            time = movieShow.dateTime
        )

        this[index].priceTime.add(priceTime)
    }

    private fun createNewQueryParameters(
        queryParameters: QueryParameters,
        dateBorder: HashMap<String, Int>
    ): QueryParameters {
        return QueryParameters(
            id = queryParameters.id,
            page = nextPage,
            locationSlug = queryParameters.locationSlug,
            actualSince = dateBorder[ACTUAL_SINCE] ?: 0,
            actualUntil = dateBorder[ACTUAL_UNTIL] ?: 0
        )
    }

    private fun calculateDateBorder(plusDays: Long): HashMap<String, Int> {
        val actualSince = LocalDate
            .now()
            .atStartOfDay(ZoneId.systemDefault())
            .plusDays(plusDays)

        val actualUntil = actualSince.plusDays(1)

        val result: HashMap<String, Int> = HashMap()
        result[ACTUAL_SINCE] = actualSince.toEpochSecond().toInt()
        result[ACTUAL_UNTIL] = actualUntil.toEpochSecond().toInt()

        return result
    }
}