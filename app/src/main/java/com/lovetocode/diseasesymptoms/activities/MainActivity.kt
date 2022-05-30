package com.lovetocode.diseasesymptoms.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.lovetocode.diseasesymptoms.composeclasses.userLogin
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var navController:NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ComposeView(this).apply {
            setContent {
                mainContent()
            }
        }
    }
    
    @Composable
    private fun content()
    {
        navController = rememberNavController()
        NavHost(navController = navController, startDestination = "ToDoList")
        {
            composable("ToDoList"){
                userLogin()
            }
        }
    }

    @Preview
    @Composable
    fun mainContent()
    {
        Scaffold(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()) {
            content()
        }
    }
}