package com.example.amovieapp.Screens.detail

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material.icons.sharp.Close
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.screennews.Data.getMovies
import com.example.screennews.screen.Home

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)

@Composable
fun detailscreen(navController: NavController,
                 movieId: String?){
    val newMovieList= getMovies().filter {movie->
        movie.id==movieId
    }
    var expan by remember {
        mutableStateOf(false)
    }

    Scaffold(
        topBar = {
            Surface(
                modifier = Modifier.fillMaxWidth(),
                color = Color.White,
                shadowElevation =  4.dp
            ) {
                TopAppBar(
                    title = { Text("Movies") },
                    navigationIcon = {
                        IconButton(onClick = {navController.popBackStack()}) {
                            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = " ")
                        }
                    }
                )
            }
        }
    ) {
        Surface(modifier= Modifier
            .fillMaxWidth()
            .fillMaxHeight()) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                Spacer(modifier = Modifier.height(60.dp))
                Home(Movies = newMovieList.first())
//                Text(text = newMovieList[0].title)
                Spacer(modifier = Modifier.height(8.dp))
                Divider()
                Spacer(modifier = Modifier.height(15.dp))
                Row {
                    Text(text = "Movie Images", fontWeight = FontWeight.ExtraBold, modifier = Modifier.padding(4.dp))
                    Spacer(modifier = Modifier.width(20.dp))
                    Icon(imageVector = if(expan)Icons.Filled.Remove
                    else Icons.Filled.Add , contentDescription = "",modifier= Modifier
                        .clickable { expan = !expan }
                        .align(Alignment.CenterVertically))
                }
                AnimatedVisibility(visible = expan) {
                    LazyRow{
                        items(newMovieList[0].images){image->
                            Surface (modifier= Modifier
                                .padding(12.dp)
                                .size(240.dp), shadowElevation = 5.dp, ){

                                AsyncImage(model = image, contentDescription = "image")
                            }
                        }
                    }
                }

            }
        }
    }


}