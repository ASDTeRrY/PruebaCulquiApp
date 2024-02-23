package com.prueba.pruebaculquiapp.login.ui.login

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.prueba.pruebaculquiapp.R
import com.prueba.pruebaculquiapp.Routes

@Composable
fun LoginScreen(loginViewModel: LoginViewModel, navController: NavHostController, email: String) {
    initialLogin(loginViewModel, email)
    Background()
    Header()
    Body(loginViewModel, navController, email)
}

fun initialLogin(loginViewModel: LoginViewModel, email: String) {
    loginViewModel.getUser(email)
}

@Composable
fun Body(loginViewModel: LoginViewModel, navController: NavHostController, email: String) {
    val state by loginViewModel.state.collectAsState()
    val userModel by loginViewModel.userState.collectAsState()
    val passwordText by loginViewModel.passwordStateFlow.collectAsState()
    var isTextFieldFocused by remember { mutableStateOf(false) }
    var isButtonEnable by remember { mutableStateOf(false) }
    var passwordVisibility by remember { mutableStateOf(false) }
    val paddingSpacer = 15.dp

    when (state) {
        is LoginState.Loading -> {
        }
        is LoginState.Success -> {
            navController.navigate(Routes.Home.route)
        }
        is LoginState.Error -> {
        }
    }

    Box {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.80f)
                .align(Alignment.BottomCenter)
        ) {
            Text(
                text = "Log in",
                modifier = Modifier
                    .wrapContentWidth()
                    .padding(start = 25.dp),
                color = Color.White,
                fontSize = 50.sp,
                textAlign = TextAlign.Start
            )
            Card(
                modifier = Modifier
                    .padding(top = 15.dp, start = 10.dp, bottom = 25.dp, end = 10.dp),
                colors = CardDefaults.cardColors(containerColor = Color.DarkGray.copy(alpha = 0.75f)),
            ) {
                Column(
                    modifier = Modifier
                        .padding(
                            top = 20.dp,
                            start = 20.dp,
                            end = 20.dp
                        )
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(0.20f)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.background),
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
                                text = "${userModel?.firstName} ${userModel?.lastName}",
                                color = Color.White,
                                fontWeight = FontWeight.Bold,
                            )
                            Text(text = email, color = Color.White)

                        }
                    }
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
                            value = passwordText,
                            label = { Text(text = "Password") },
                            onValueChange = { loginViewModel.onLoginChange(it) },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                            singleLine = true,
                            maxLines = 1,
                            trailingIcon = {
                                Text(
                                    text = if (passwordVisibility) "Hide" else "View",
                                    textAlign = TextAlign.End,
                                    modifier = Modifier.padding(end = 15.dp).clickable {
                                        passwordVisibility = !passwordVisibility
                                    })
                            },
                            visualTransformation = if (passwordVisibility) {
                                VisualTransformation.None
                            } else {
                                PasswordVisualTransformation()
                            },
                            colors = TextFieldDefaults.colors(
                                focusedIndicatorColor = Color.Transparent,
                                unfocusedIndicatorColor = Color.Transparent
                            ),
                            modifier = Modifier.fillMaxWidth().onFocusChanged {
                                isTextFieldFocused = it.isFocused
                            }
                        )

                    }
                    Spacer(modifier = Modifier.height(paddingSpacer))
                    Button(
                        onClick = {
                            loginViewModel.sendLogin(email, passwordText)
                        },
                        shape = RoundedCornerShape(10.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = colorResource(R.color.teal_700)
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(55.dp)
                    )
                    {
                        Text(text = "Continue", fontSize = 18.sp)
                    }

                    Spacer(modifier = Modifier.height(25.dp))
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