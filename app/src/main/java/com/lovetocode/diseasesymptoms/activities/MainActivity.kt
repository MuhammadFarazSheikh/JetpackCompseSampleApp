package com.lovetocode.diseasesymptoms.activities

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.lovetocode.diseasesymptoms.composeclasses.userToDOAdd
import com.lovetocode.diseasesymptoms.composeclasses.weatherData
import com.lovetocode.diseasesymptoms.models.BaseBO
import com.lovetocode.diseasesymptoms.models.BottomNavItem
import com.lovetocode.diseasesymptoms.viewmodels.CommonViewModel
import com.lovetocode.diseasesymptoms.viewmodels.RoomDBViewModel
import com.montymobile.callsignature.networking.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var baseBO: BaseBO
    val viewModel: CommonViewModel by viewModels()
    val roomDBViewModel: RoomDBViewModel by viewModels()
    lateinit var navController:NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        getWeatherData()

        setContent {
            mainContent()
        }
    }
    
    @Composable
    private fun content()
    {

        NavHost(navController = navController, startDestination = "ToDoList")
        {
            composable("ToDoList"){
                userToDOAdd(roomDBViewModel)
            }

            composable("WeatherInfo"){
                weatherData(baseBO)
            }
        }
    }

    @Composable
    fun bottomNavigation(navController: NavHostController) {
        val items = listOf(
            BottomNavItem.Home,
            BottomNavItem.MyNetwork,
        )



        BottomNavigation(backgroundColor = Color.White, modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
        ) {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            var currentDestination = navBackStackEntry?.destination
            items.forEach { screens->
                BottomNavigationItem(
                    icon = { Icon(painterResource(id = screens.icon), contentDescription = null) },
                    label = { Text(screens.title) },
                    selected = currentDestination?.hierarchy?.any { it.route == screens.screen_route } == true,
                    onClick = {
                        navController.navigate(screens.screen_route) {
                            // Pop up to the start destination of the graph to
                            // avoid building up a large stack of destinations
                            // on the back stack as users select items
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            // Avoid multiple copies of the same destination when
                            // reselecting the same item
                            launchSingleTop = true
                            // Restore state when reselecting a previously selected item
                            restoreState = true
                        }
                    }
                )
            }
        }
    }

    private fun getWeatherData()
    {
        viewModel.getData("Pakistan").observe(this, Observer {
            when(it)
            {
                is Resource.Success->
                {
                    baseBO = it.value
                }
                is Resource.Failure->
                {

                }
                is Resource.Loading->
                {

                }
            }
        })
    }

    @Preview
    @Composable
    fun mainContent()
    {
        navController = rememberNavController()
        var scaffoldState = rememberScaffoldState(drawerState = DrawerState(DrawerValue.Closed))
        Scaffold(
            scaffoldState = scaffoldState,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(), bottomBar = {
                    bottomNavigation(navController = navController)
            }) {
            content()
        }
    }
}