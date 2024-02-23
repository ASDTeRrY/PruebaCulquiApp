package com.prueba.pruebaculquiapp

sealed class Routes(val route: String) {
    data object Initial : Routes("initialScreen")
    data object Login : Routes("loginScreen/{email}") {
        fun createRoute(email: String) = "loginScreen/$email"
    }

    data object SignUp : Routes("signupScreen/{email}") {
        fun createRoute(email: String) = "signupScreen/$email"
    }

    data object Home : Routes("homeScreen")
}