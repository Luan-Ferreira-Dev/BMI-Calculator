package com.example.bmicalculator.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun BodyMassIndexApp (viewModel: IndexViewModel = viewModel(),
                      navController: NavHostController = rememberNavController()) {

    val uiState by viewModel.uiState.collectAsState()

    NavHost(navController = navController,
        startDestination = "start") {
        composable(route = "start") {
            StartScreen(indexViewModel = viewModel, modifier = Modifier.padding(16.dp)) {
                viewModel.saveStates()
                navController.navigate("IndexScreen")
            }
        }
        composable(route = "IndexScreen") {
            IndexScreen(uiState = uiState, modifier = Modifier.padding(16.dp))
        }
    }
}