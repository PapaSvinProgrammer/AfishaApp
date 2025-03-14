package com.example.afishaapp.ui.screen.commentList

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.afishaapp.data.module.comment.Comment
import com.example.afishaapp.data.module.comment.CommentResponse
import com.example.afishaapp.domain.http.GetCommentEvent
import com.example.afishaapp.domain.http.GetCommentMovie
import com.example.afishaapp.domain.module.DirectedFilterType
import com.example.afishaapp.domain.module.EventCategory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class CommentListViewModel @Inject constructor(
    private val getCommentMovie: GetCommentMovie,
    private val getCommentEvent: GetCommentEvent
): ViewModel() {
    private var nextPage = 2
    var comments by mutableStateOf<List<Comment>>(listOf())
        private set

    var filter by mutableStateOf(DirectedFilterType.ASC)
        private set
    var directedFilterState by mutableStateOf(false)
        private set

    fun getComments(id: Int, type: EventCategory) {
        when (type) {
            EventCategory.EVENT-> getEventComments(id)
            EventCategory.MOVIE -> getMovieComments(id)
            else -> {}
        }
    }

    fun loadMoreComments(id: Int, type: EventCategory) {
        when (type) {
            EventCategory.EVENT -> loadMoreEventComments(id)
            EventCategory.MOVIE -> loadMoreMovieComments(id)
            else -> {}
        }
    }

    fun updateDirectedFilterState(state: Boolean) {
        directedFilterState = state
    }

    fun updateFilter(filterType: DirectedFilterType) {
        filter = filterType
    }

    private fun getEventComments(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val temp = getCommentEvent.getAsc(id)
            setNewComments(temp)
        }
    }

    private fun getMovieComments(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val temp = getCommentMovie.getAsc(id)
            setNewComments(temp)
        }
    }

    private fun loadMoreEventComments(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val temp = getCommentEvent.getAsc(
                eventId = id,
                page = nextPage
            )

            addComments(temp)
        }
    }

    private fun loadMoreMovieComments(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val temp = getCommentMovie.getAsc(
                movieId = id,
                page = nextPage
            )

            addComments(temp)
        }
    }

    private fun setNewComments(commentResponse: CommentResponse?) {
        commentResponse?.let {
            comments = it.results
        }
    }

    private fun addComments(commentResponse: CommentResponse?) {
        commentResponse?.let {
            val temp = comments.toMutableList()
            temp.addAll(it.results)

            comments = temp
        }
    }
}