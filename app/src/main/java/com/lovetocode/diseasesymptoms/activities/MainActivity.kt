package com.lovetocode.diseasesymptoms.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.lovetocode.diseasesymptoms.composeclasses.userData
import com.lovetocode.diseasesymptoms.composeclasses.userLogin
import com.lovetocode.diseasesymptoms.models.BottomNavItem
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var navController:NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(ComposeView(this).apply {
            setContent {
                mainContent()
            }
        })
    }
    
    @Composable
    private fun content()
    {
        NavHost(navController = navController, startDestination = "ToDoList")
        {
            composable("ToDoList"){
                userLogin()
            }

            composable("WeatherInfo"){
                userData()
            }
        }
    }

    @Composable
    fun bottomNavigation(navController: NavHostController) {
        val items = listOf(
            BottomNavItem.Home,
            BottomNavItem.MyNetwork,
        )
        BottomNavigation(modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()) {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            var currentDestination = navBackStackEntry?.destination
            items.forEach { screens->
                BottomNavigationItem(
                    icon = { Icon(Icons.Filled.Favorite, contentDescription = null) },
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