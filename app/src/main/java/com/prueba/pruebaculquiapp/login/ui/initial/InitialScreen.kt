package com.prueba.pruebaculquiapp.login.ui.initial

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.prueba.pruebaculquiapp.R
import com.prueba.pruebaculquiapp.Routes
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun InitialScreen(initialViewModel: InitialViewModel, navController: NavHostController) {

    initialViewModel.initialData()
    Background()
    Header()
    Body(navController, initialViewModel)
    SearchUser(initialViewModel, navController)
}

@Composable
fun Body(navController: NavHostController, initialViewModel: InitialViewModel) {

    var isTextFieldFocused by remember { mutableStateOf(false) }
    val emailText by initialViewModel.textStateFlow.collectAsState()
    val isBtnEnable by initialViewModel.isBtnEnable.collectAsState(initial = false)
    val paddingSpacer = 20.dp

    Box {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.85f)
                .align(Alignment.BottomCenter)
        ) {
            Text(
                text = "Hi!",
                modifier = Modifier
                    .wrapContentWidth()
                    .padding(start = 25.dp),
                color = Color.White,
                fontSize = 50.sp,
                textAlign = TextAlign.Start
            )
            Card(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 15.dp, start = 10.dp, bottom = 25.dp, end = 10.dp),
                colors = CardDefaults.cardColors(containerColor = Color.DarkGray.copy(alpha = 0.75f)),
            ) {

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(
                            top = 20.dp,
                            bottom = 10.dp,
                            start = 15.dp,
                            end = 15.dp
                        )
                ) {
                    Card(
                        shape = RoundedCornerShape(10.dp),
                        border = BorderStroke(
                            width = 2.dp,
                            color = if (isTextFieldFocused) colorResource(R.color.teal_700) else Color.Transparent
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(55.dp)
                    )
                    {
                        TextField(
                            value = emailText,
                            label = { Text(text = "Email") },
                            singleLine = true,
                            maxLines = 1,
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                            onValueChange = { initialViewModel.onEmailChange(it)},
                            colors = TextFieldDefaults.colors(
                                focusedIndicatorColor = Color.Transparent,
                                unfocusedIndicatorColor = Color.Transparent
                            ),
                            modifier = Modifier.onFocusChanged {
                                isTextFieldFocused = it.isFocused
                            }
                        )

                    }
                    Spacer(modifier = Modifier.height(paddingSpacer))
                    Button(
                        onClick = {
                            initialViewModel.getUser(emailText)
                        },
                        enabled = isBtnEnable,
                        shape = RoundedCornerShape(10.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = colorResource(R.color.teal_700),
                            disabledContainerColor = colorResource(R.color.btn_disable)
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(55.dp)
                    )
                    {
                        Text(text = "Continue", fontSize = 18.sp)
                    }
                    Spacer(modifier = Modifier.height(paddingSpacer))
                    Text(
                        text = "Or",
                        modifier = Modifier
                            .fillMaxWidth(),
                        color = Color.White,
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(paddingSpacer))
                    Button(
                        onClick = { },
                        shape = RoundedCornerShape(10.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.White
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(55.dp)
                    )
                    {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxSize()

                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.facebook),
                                contentDescription = "Facebook",
                            )
                            Text(
                                text = "Continue with Facebook",
                                textAlign = TextAlign.Center,
                                color = Color.Black,
                                fontWeight = FontWeight.Bold,
                                fontSize = 18.sp,
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(top = 8.dp)
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(paddingSpacer))
                    Button(
                        onClick = { },
                        shape = RoundedCornerShape(10.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.White
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(55.dp)
                    )
                    {
                        Row(
                            modifier = Modifier
                                .fillMaxSize()
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.google),
                                contentDescription = "Google",
                            )
                            Text(
                                text = "Continue with Google",
                                textAlign = TextAlign.Center,
                                color = Color.Black,
                                fontWeight = FontWeight.Bold,
                                fontSize = 18.sp,
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(top = 8.dp)
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(paddingSpacer))
                    Button(
                        onClick = { },
                        shape = RoundedCornerShape(10.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.White
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(55.dp)
                    )
                    {
                        Row(
                            modifier = Modifier
                                .fillMaxSize()
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.apple),
                                contentDescription = "Apple",
                            )
                            Text(
                                text = "Continue with Apple",
                                textAlign = TextAlign.Center,
                                color = Color.Black,
                                fontWeight = FontWeight.Bold,
                                fontSize = 18.sp,
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(top = 8.dp)
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(25.dp))
                    Row {
                        Text(
                            text = "Don't have an account?",
                            color = Color.White,
                            textAlign = TextAlign.Center
                        )
                        Text(
                            text = " Sign up",
                            color = colorResource(R.color.teal_700),
                            textAlign = TextAlign.Center,
                            modifier = Modifier.clickable {
                                navController.navigate(Routes.SignUp.createRoute(emailText))
                            }
                        )
                    }
                    Spacer(modifier = Modifier.height(paddingSpacer))
                    Text(
                        text = "Forgot your password?",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        color = colorResource(id = R.color.teal_700),
                    )

                }

            }

        }
    }

}

@Composable
fun SearchUser(initialViewModel: InitialViewModel, navController: NavHostController) {
    val userState by initialViewModel.userState.collectAsState()
    val snackbarHostState = rememberSnackbarHostState()
    val coroutineScope = rememberCoroutineScope()

    when (val state = userState) {
         UserState.Loading -> {
        }
        is UserState.Success -> {
            navController.navigate(Routes.SignUp.createRoute(email = state.user.email))
        }
        is UserState.Error -> {

            fun showSnackbar(message: String) {
                coroutineScope.launch {
                    snackbarHostState.showSnackbar(message)
                    delay(3000)
                    snackbarHostState.currentSnackbarData?.dismiss()
                }
            }
            showSnackbar("El usuario no se encuentra registrado")
            SnackbarHost(snackbarHostState, modifier = Modifier.padding(top = 25.dp))
        }
    }
}

@Composable
fun rememberSnackbarHostState(): SnackbarHostState {
    return SnackbarHostState()
}

@Composable()
fun Background() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                colorResource(id = R.color.background)
            )
    ) {
        Image(
            painter = painterResource(id = R.drawable.background),
            contentDescription = "background",
            modifier = Modifier
                .fillMaxHeight(0.55f)
                .align(Alignment.TopEnd)
        )
    }

}

@Composable
fun Header() {
    Box(
        Modifier
            .fillMaxSize()
            .padding(5.dp)
    ) {
        Icon(
            imageVector = Icons.Default.KeyboardArrowLeft,
            contentDescription = "back",
            tint = Color.White,
        )
    }
}

