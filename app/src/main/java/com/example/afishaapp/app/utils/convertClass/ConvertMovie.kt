package com.example.afishaapp.app.utils.convertClass

import com.example.afishaapp.data.module.image.ImageItem
import com.example.afishaapp.data.module.image.Thumbnail
import com.example.afishaapp.data.module.movie.Movie
import com.example.afishaapp.data.room.likeMovie.MovieEntity

fun Movie.toMovieEntity(): MovieEntity {
    val movieEntity = MovieEntity(
        movieId = this.id,
        name = this.title,
        year = this.year,
        rating = this.imdbRating,
        image = this.images.first().thumbnails.highImage
    )

    return movieEntity
}

fun MovieEntity.toMovie(): Movie {
    val imageItem = ImageItem(
        image = this.image,
        thumbnails = Thumbnail(
            highImage = this.image,
            lowImage = this.image
        )
    )

    val movie = Movie(
        id = this.movieId,
        title = this.name,
        year = this.year,
        imdbRating = this.rating,
        poster = imageItem
    )

    return movie
}

fun List<MovieEntity>.toMovieList(): List<Movie> {
    val res = ArrayList<Movie>()

    this.forEach {
        res.add(it.toMovie())
    }

    return res
}