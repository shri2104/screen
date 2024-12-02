package com.example.screennews.Navigation

enum class MovieScreens{
    FirstScreen,
    HomeScreen,
    DetailedScreen;
    companion object{
        fun fromRoute(route:String?):MovieScreens
                = when(route?.substringBefore("/")){
                    FirstScreen.name->FirstScreen
            HomeScreen.name->HomeScreen
            DetailedScreen.name->DetailedScreen
            null->HomeScreen
            else->throw IllegalArgumentException("Route $route is not recognized")
        }
    }
}
