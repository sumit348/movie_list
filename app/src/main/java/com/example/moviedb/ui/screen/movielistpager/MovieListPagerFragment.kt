package com.example.moviedb.ui.screen.movielistpager

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.viewpager2.widget.ViewPager2
import com.example.moviedb.R
import com.example.moviedb.data.constant.MovieListType
import com.example.moviedb.databinding.FragmentMovieListPagerBinding
import com.example.moviedb.ui.base.BaseFragment
import com.example.moviedb.ui.base.BaseViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieListPagerFragment :
    BaseFragment<FragmentMovieListPagerBinding, BaseViewModel>() {

    override val layoutId: Int = R.layout.fragment_movie_list_pager

    override val viewModel: BaseViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val movieListPagerAdapter = MovieListPagerAdapter(
            ArrayList<MovieListType>().apply {
                add(MovieListType.POPULAR)
                add(MovieListType.TOP_RATED)
            }, this
        )

        viewBinding.movieListPager.apply {
            clipToPadding = false
            clipChildren = false
            // retain 1 page on each size
            offscreenPageLimit = 1
            orientation = ViewPager2.ORIENTATION_VERTICAL

            adapter = movieListPagerAdapter
        }

    }
}
