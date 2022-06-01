package com.lovetocode.diseasesymptoms.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.DrawerState
import androidx.compose.material.DrawerValue
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.lovetocode.diseasesymptoms.composeclasses.userData
import com.lovetocode.diseasesymptoms.composeclasses.userLogin
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
        navController = rememberNavController()
        NavHost(navController = navController, startDestination = "ToDoList/{args}")
        {
            composable("ToDoList/{args}"){
                userLogin(navController)
            }

            composable("userData/{args}"){
                userData(it.arguments?.getString("args")!!)
            }
        }
    }

    @Preview
    @Composable
    fun mainContent()
    {
        var scaffoldState = rememberScaffoldState(drawerState = DrawerState(DrawerValue.Closed))
        Scaffold(
            scaffoldState = scaffoldState,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()) {
            content()
        }
    }
}