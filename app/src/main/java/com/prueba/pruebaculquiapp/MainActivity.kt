package com.prueba.pruebaculquiapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.prueba.pruebaculquiapp.home.ui.home.HomeScreen
import com.prueba.pruebaculquiapp.login.ui.initial.InitialScreen
import com.prueba.pruebaculquiapp.login.ui.initial.InitialViewModel
import com.prueba.pruebaculquiapp.login.ui.login.LoginScreen
import com.prueba.pruebaculquiapp.login.ui.login.LoginViewModel
import com.prueba.pruebaculquiapp.login.ui.signup.SignUpScreen
import com.prueba.pruebaculquiapp.login.ui.signup.SignUpViewModel
import com.prueba.pruebaculquiapp.ui.theme.PruebaCulquiAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val initialViewModel: InitialViewModel by viewModels()
    private val loginViewModel: LoginViewModel by viewModels()
    private val signUpViewModel: SignUpViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PruebaCulquiAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navigationController = rememberNavController()
                    NavHost(
                        navController = navigationController,
                        startDestination = Routes.Initial.route
                    ) {
                        composable(Routes.Initial.route) { InitialScreen(initialViewModel, navController = navigationController) }
                        composable(
                            Routes.Login.route,
                            arguments = listOf(navArgument("email") { type = NavType.StringType })
                        ) { backStackEntry ->
                            LoginScreen(
                                loginViewModel,
                                navigationController,
                                backStackEntry.arguments?.getString("email")!!
                            )
                        }
                        composable(
                            Routes.SignUp.route,
                            arguments = listOf(navArgument("email") { type = NavType.StringType })
                        ) { backStackEntry ->
                            SignUpScreen(
                                signUpViewModel,
                                navigationController,
                                backStackEntry.arguments?.getString("email")!!
                            )
                        }
                        composable(
                            Routes.Home.route,
                            arguments = listOf(navArgument("email") { type = NavType.StringType })
                        ) { backStackEntry ->
                            HomeScreen(
                                navigationController
                            )
                        }

                    }

                }
            }
        }
    }
}