package com.example.moviedb.ui.popularmovie

import androidx.lifecycle.Observer
import com.example.moviedb.data.constant.MovieListType
import com.example.moviedb.data.model.Movie
import com.example.moviedb.data.remote.api.ApiParams
import com.example.moviedb.data.repository.UserRepository
import com.example.moviedb.factory.createMovieListResponse
import com.example.moviedb.ui.BaseViewModelTest
import com.example.moviedb.ui.screen.popularmovie.PopularMovieViewModel
import com.example.moviedb.utils.mock
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*

@ExperimentalCoroutinesApi
class PopularMovieViewModelTest : BaseViewModelTest() {

    private lateinit var viewModel: PopularMovieViewModel

    private val userRepository = mock<UserRepository>()

    @Before
    override fun setup() {
        super.setup()
        viewModel = PopularMovieViewModel()
    }

    /*@Test
    fun getDataSuccessTest() {
        testCoroutineRule.runBlockingTest {
            // given
            val fakeData = createMovieListResponse()
            val page = 1
            viewModel.mode.value = MovieListType.POPULAR.type

            val hashMap = HashMap<String, String>()
            hashMap[ApiParams.PAGE] = page.toString()
            when (viewModel.mode.value) {
                MovieListType.POPULAR.type -> hashMap[ApiParams.SORT_BY] = ApiParams.POPULARITY_DESC
                MovieListType.TOP_RATED.type -> hashMap[ApiParams.SORT_BY] =
                    ApiParams.VOTE_AVERAGE_DESC
                else -> hashMap[ApiParams.SORT_BY] = ApiParams.POPULARITY_DESC
            }
            val observer = mock<Observer<List<Movie>>>()
            viewModel.itemList.observeForever(observer)

            `when`(userRepository.getMovieList(hashMap)).thenReturn(fakeData)

            // when
            viewModel.loadData(page)

            // then
            Assert.assertEquals(4, viewModel.itemList.value?.size)
            Assert.assertEquals("1", viewModel.itemList.value?.getOrNull(0)?.id)
            Assert.assertEquals("2", viewModel.itemList.value?.getOrNull(1)?.id)
            Assert.assertEquals("3", viewModel.itemList.value?.getOrNull(2)?.id)
            Assert.assertEquals("4", viewModel.itemList.value?.getOrNull(3)?.id)

            verify(observer).onChanged(fakeData.results)
        }
    }*/
}
