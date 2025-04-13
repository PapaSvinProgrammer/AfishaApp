package com.example.afishaapp.ui.screen.commentListPlace

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.afishaapp.data.module.comment.Comment
import com.example.afishaapp.domain.http.GetCommentPlace
import com.example.afishaapp.domain.module.DirectedFilterType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class CommentListPlaceViewModel @Inject constructor(
    private val getCommentPlace: GetCommentPlace
): ViewModel() {
    private var nextPage = 2

    var comments by mutableStateOf<List<Comment>>(listOf())
        private set

    var directedFilterState by mutableStateOf(false)
        private set
    var filter by mutableStateOf(DirectedFilterType.ASC)
        private set

    fun getComments(id: Int) {
        comments = listOf()

        viewModelScope.launch(Dispatchers.IO) {
            val temp = getCommentPlace.getAsc(id)

            temp?.let {
                comments = it.results
            }
        }
    }

    fun loadMoreComments(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val commentResponse = getCommentPlace.getAsc(
                id = id,
                page = nextPage
            )

            commentResponse?.let {
                val temp = comments.toMutableList()
                temp.addAll(it.results)

                comments = temp
            }
        }
    }

    fun updateDirectedFilterState(state: Boolean) {
        directedFilterState = state
    }

    fun updateFilter(type: DirectedFilterType) {
        filter = type
    }
}