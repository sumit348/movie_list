package com.example.moviedb.ui.screen.moviepager

import android.content.res.Resources
import androidx.lifecycle.viewModelScope
import com.example.moviedb.data.constant.MovieListType
import com.example.moviedb.data.model.Movie
import com.example.moviedb.data.remote.api.ApiParams
import com.example.moviedb.data.repository.UserRepository
import com.example.moviedb.ui.base.loadmorerefresh.BaseLoadMoreRefreshViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviePagerViewModel @Inject constructor(
    val resources: Resources,
    private val userRepository: UserRepository
) : BaseLoadMoreRefreshViewModel<Movie>() {

    val mode = MutableStateFlow(MovieListType.POPULAR.type)

    override fun loadData(page: Int) {
        val hashMap = HashMap<String, String>()
        hashMap[ApiParams.PAGE] = page.toString()
        when (mode.value) {
            MovieListType.POPULAR.type -> hashMap[ApiParams.SORT_BY] = ApiParams.POPULARITY_DESC
            MovieListType.TOP_RATED.type -> hashMap[ApiParams.SORT_BY] = ApiParams.VOTE_AVERAGE_DESC
            else -> hashMap[ApiParams.SORT_BY] = ApiParams.POPULARITY_DESC
        }

        viewModelScope.launch {
            try {
                onLoadSuccess(page, userRepository.getMovieList(hashMap).results)
            } catch (e: Exception) {
                onError(e)
            }
        }
    }
}
