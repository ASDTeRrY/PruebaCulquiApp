package com.prueba.pruebaculquiapp.home.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.prueba.pruebaculquiapp.R
import com.prueba.pruebaculquiapp.Routes
import com.prueba.pruebaculquiapp.login.domain.model.UserModel

@Composable
fun HomeScreen(homeViewModel: HomeViewModel, navController: NavHostController) {
    homeViewModel.initialData()
    Body(homeViewModel, navController)
}

@Composable
fun Body(homeViewModel: HomeViewModel, navController: NavHostController) {
    val state by homeViewModel.state.collectAsState()
    when (val currentState = state) {
        is HomeState.Loading -> {
            CircularProgressIndicator()
        }

        is HomeState.Success -> {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(colorResource(id = R.color.background2))
            ) {

            }
            RecyclerView(currentState.userList, navController)
        }

        is HomeState.Error -> {
            val error = (currentState).error
            Text(text = "Error: $error")
        }
    }
}

@OptIn(ExperimentalCoilApi::class)
@Composable
fun RecyclerView(userList: List<UserModel>, navController: NavHostController) {
    LazyColumn {
        items(userList) { item ->
            Card(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp)
                    .clickable {
                        navController.navigate(Routes.Initial.route)
                    },
                colors = CardDefaults.cardColors(containerColor = Color.DarkGray.copy(alpha = 0.5f)),
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.20f)
                ) {
                    Image(
                        painter = rememberImagePainter(data = item.avatar),
                        contentDescription = "LoginIcono",
                        modifier = Modifier
                            .width(75.dp)
                            .height(75.dp)
                            .clip(CircleShape),
                        contentScale = ContentScale.Crop,
                        alignment = Alignment.TopCenter

                    )
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(start = 10.dp),
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "${item.firstName} ${item.lastName}",
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                        )
                        Text(text = item.email, color = Color.White)

                    }
                }
            }
        }
    }
}
