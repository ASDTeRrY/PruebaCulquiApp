package com.prueba.pruebaculquiapp.login.ui.login

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import com.prueba.pruebaculquiapp.Routes

@Composable
fun LoginScreen(navController: NavHostController, email: String) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Gray)
    ) {
        Text(text = "Initial Screen",
            modifier = Modifier
                .align(Alignment.Center)
                .clickable { navController.navigate(Routes.SignUp.createRoute("cesar.rodrigezL@hotmail.com")) })
    }
}