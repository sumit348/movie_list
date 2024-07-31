package com.example.moviedb.compose.ui.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.Image
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.moviedb.R
import com.example.moviedb.compose.ui.widget.BoxContent
import com.example.moviedb.data.model.Movie
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.components.rememberImageComponent
import com.skydoves.landscapist.glide.GlideImage
import com.skydoves.landscapist.placeholder.placeholder.PlaceholderPlugin

@Composable
fun DetailScreen(
    navController: NavController,
    movieId: String?,
    viewModel: DetailViewModel = hiltViewModel()
) {
    val movie by viewModel.movie.collectAsState()
    LaunchedEffect(key1 = movieId, block = {
        viewModel.setValueMovieId(id = movieId)
        viewModel.getMovieDetail(movieId = movieId)
    })

    BoxContent(
        enableRefresh = true,
        viewModel = viewModel
    ) {
        movie?.let {
            MovieDetailBody(
                movie = it,
                onClickBack = { navController.popBackStack() },
            )
        } ?: MovieDetailEmptyBody(onClickBack = {
            navController.popBackStack()
        })
    }
}

@Composable
fun MovieDetailBody(
    movie: Movie,
    onClickBack: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        Box(modifier = Modifier.fillMaxWidth()) {
            GlideImage(
                imageModel = { movie.getFullBackdropPath() ?: "" },
                modifier = Modifier.fillMaxWidth(),
                component = rememberImageComponent {
                    +PlaceholderPlugin.Loading(Icons.Filled.Image)
                    +PlaceholderPlugin.Failure(Icons.Filled.Error)
                },
                imageOptions = ImageOptions(),
            )
            Image(
                painterResource(R.drawable.ic_arrow_back_white_24dp),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .clickable {
                        onClickBack.invoke()
                    }
                    .padding(12.dp),
            )
        }
        Text(
            movie.title ?: "",
            color = Color.White,
            modifier = Modifier.padding(16.dp),
            fontSize = 20.sp,
        )
        Text(movie.releaseDate ?: "", color = Color.White)
        Text(movie.overview ?: "", color = Color.White)
    }
}

@Composable
fun MovieDetailEmptyBody(
    onClickBack: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        Box(modifier = Modifier.fillMaxWidth()) {
            Image(
                painterResource(R.drawable.ic_arrow_back_white_24dp),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .clickable {
                        onClickBack.invoke()
                    }
                    .padding(12.dp),
            )
        }
    }
}