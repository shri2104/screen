package com.example.screennews.firstS

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.HourglassEmpty
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.screennews.Navigation.MovieScreens




@Composable
fun firsts(navController: NavController) {
    var expanded by remember {
        mutableStateOf(false)
    }
    var firstName by remember {
        mutableStateOf("")
    }
    var lastName by remember {
        mutableStateOf("")
    }
    Column(
        modifier = Modifier.padding(8.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(120.dp))
        Text(text = "Let's Explore", fontSize = 25.sp)
        Divider(modifier = Modifier.padding(20.dp))
        Spacer(modifier = Modifier.height(15.dp))
        InputF(
            text = firstName,
            label = "First Name",
            onTextChange = {
                if (it.all { it.isLetter() || it.isWhitespace() }) firstName = it
            }
        )
        Spacer(modifier = Modifier.height(15.dp))
        InputF(
            text = lastName,
            label = "Last Name",
            onTextChange = {
                if (it.all { it.isLetter() || it.isWhitespace() }) lastName = it
            }
        )
        Spacer(modifier = Modifier.height(15.dp))
        Button(onClick = {
            val fullName = "$firstName $lastName"
            if(firstName.isNotEmpty()||lastName.isNotEmpty()){
            navController.navigate("${MovieScreens.HomeScreen.name}/$fullName")}
        }) {
            Text(text = "Enter")
        }
        Spacer(modifier = Modifier.height(100.dp))
        Row {
            Text(text = "application is currently undergoing processing",
                fontWeight = FontWeight.Bold,
                color = Color.Red)
            CircularProgressIndicator(
                color = androidx.compose.ui.graphics.Color.Red,
                modifier = Modifier.size(50.dp)
            )
        }
        Icon(imageVector = if(expanded)Icons.Filled.KeyboardArrowUp
        else Icons.Filled.KeyboardArrowDown,
            contentDescription ="arrow" ,
            modifier= Modifier
                .size(25.dp)
                .clickable { expanded = !expanded },
            tint = Color.Red)
        AnimatedVisibility(visible = expanded) {
            Text(text = "Please be informed that our application is currently" +
                    "undergoing processing. We are actively working to ensure" +
                    " everything runs smoothly and efficiently. We appreciate your" +
                    " understanding during this time as we strive to add more " +
                    "features for your enjoyment. Stay tuned for exciting updates.!",
                fontWeight = FontWeight.Bold,
                lineHeight = 15.sp,
                color = Color.Magenta
           )
        }

    }
}
