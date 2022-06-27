package com.lovetocode.diseasesymptoms.models

import com.lovetocode.diseasesymptoms.R

sealed class BottomNavItem(var title:String, var icon:Int, var screen_route:String)
{
    object Home : BottomNavItem("ToDo List", R.drawable.to_do_list,"ToDoList")
    object MyNetwork: BottomNavItem("Weather Data",R.drawable.sun,"WeatherInfo")
}