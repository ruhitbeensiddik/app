package com.example.bmicare

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.bmicare.ui.screens.BmiInputScreen
import com.example.bmicare.ui.screens.BmiResultScreen
import com.example.bmicare.ui.screens.GenderSelectionScreen
import com.example.bmicare.ui.theme.BMICareTheme
import com.example.bmicare.viewmodel.BmiViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BMICareTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    val viewModel: BmiViewModel = viewModel()

                    NavHost(navController = navController, startDestination = "gender_selection") {
                        composable("gender_selection") {
                            GenderSelectionScreen(
                                viewModel = viewModel,
                                onNext = { navController.navigate("bmi_input") }
                            )
                        }
                        composable("bmi_input") {
                            BmiInputScreen(
                                viewModel = viewModel,
                                onCalculate = { navController.navigate("bmi_result") }
                            )
                        }
                        composable("bmi_result") {
                            BmiResultScreen(
                                viewModel = viewModel,
                                onRecalculate = { 
                                    navController.popBackStack("gender_selection", false) 
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}
