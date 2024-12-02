package com.example.screennews.screen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.screennews.Data.Movie
import com.example.screennews.Data.getMovies
import com.example.screennews.Navigation.MovieScreens
import java.time.LocalTime


@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Main(
    navController: NavController,
    movieList: List<Movie> = getMovies(),
    movie: String?
) {
    val currentTime = LocalTime.now()
    val greeting = when (currentTime.hour) {
        in 0..11 -> "Good Morning"
        in 12..17 -> "Good Afternoon"
        else -> "Good Evening"
    }
    Scaffold(
        topBar = {
            Surface(
                modifier = Modifier.fillMaxWidth(),
                color = Color.White,
                shadowElevation = 4.dp
            ) {
                TopAppBar(
                    title = { Column{
                        Text(
                            text = "$greeting..!!",
                            style = MaterialTheme.typography.bodyLarge,
                            color = Color.DarkGray,
                            fontWeight = FontWeight.Bold,
                            lineHeight = 20.sp
                        )

                        Text(
                            text = movie ?: "Movies",
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color.DarkGray,
                            lineHeight = 20.sp
                        )
                        Text(
                            text = "Enjoy the movies",
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color.DarkGray,
                            lineHeight = 20.sp
                        )
                    } }
                )
                
            }
        }
    ) { innerPadding ->
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(innerPadding) // Apply padding from Scaffold
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                Column(modifier = Modifier.padding(12.dp)) {
//                    Spacer(modifier = Modifier.height(50.dp))
                    LazyColumn {
                        items(items = movieList) {
                            Home(it){
                                navController.navigate(route = MovieScreens.DetailedScreen.name+"/$it")
                            }
                        }
                    }
                }
            }
        }
    }
}






@Composable
fun Home(Movies: Movie,onitemclick:(String)->Unit={}){
    var expanded by remember {
        mutableStateOf(false)
    }
    Surface(modifier= Modifier
        .padding(8.dp)
        .fillMaxWidth()
        .clickable { onitemclick(Movies.id) }
        , shape = RoundedCornerShape(10.dp),
        shadowElevation = 5.dp
    ) {
        Row(verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start) {
            Surface(modifier = Modifier
                .padding(5.dp)
                .size(100.dp),
                shape = RectangleShape, shadowElevation = 4.dp,
                color = Color.LightGray
            ) {
                AsyncImage(
                    model = Movies.poster,
                    contentDescription = "movie poster",
                    modifier = Modifier.clip(CircleShape)
                )

            }
            Column(modifier=Modifier.padding(8.dp)) {
                Text(text = Movies.title,
                    style = MaterialTheme.typography.bodyLarge)
                Text(text = "Director: ${Movies.director}",
                    style = MaterialTheme.typography.bodySmall)
                Text(text ="Realsed: ${Movies.year}",
                    style = MaterialTheme.typography.bodySmall)
                Icon(imageVector = if(expanded)Icons.Filled.KeyboardArrowUp
                else Icons.Filled.KeyboardArrowDown,
                    contentDescription ="arrow" ,
                    modifier= Modifier
                        .size(25.dp)
                        .clickable { expanded = !expanded },
                    tint = Color.Red)
                AnimatedVisibility(visible = expanded) {
                    Column {
                        Text(buildAnnotatedString {
                            withStyle(style = SpanStyle(color = Color.Green, fontSize = 13.sp,fontWeight = FontWeight.Bold,)){
                                append("Plot: ")
                            }
                            withStyle(style = SpanStyle(color = Color.DarkGray, fontSize = 13.sp, )){
                                append(Movies.plot)
                            }
                        }, lineHeight = 11.sp)

                        Divider()
                        Text(text = "Actors: ${Movies.actors}", fontSize = 12.sp, lineHeight = 11.sp, fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(4.dp))
                        Text(text = "Rating: ${Movies.rating}", fontSize = 12.sp, lineHeight = 11.sp, fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(2.dp))
                    }
                }
            }

        }
    }
}