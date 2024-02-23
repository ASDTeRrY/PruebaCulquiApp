package com.prueba.pruebaculquiapp.login.ui.login

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.prueba.pruebaculquiapp.R
import com.prueba.pruebaculquiapp.Routes

@Composable
fun LoginScreen(navController: NavHostController, email: String) {
    Background()
    Header()
    Body(navController)

}

@Composable
fun Body(navController: NavHostController) {
    var isTextFieldFocused by remember { mutableStateOf(false) }
    val paddingSpacer = 15.dp
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
                        .background(Color.Red)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(0.20f)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.background),
                            contentDescription = "LoginIcono",
                            modifier = Modifier.fillMaxSize(0.2f).clip(CircleShape)
                            
                        )
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(start = 15.dp),
                            verticalArrangement = Arrangement.Center
                        ) {
                            Text(text = "Jane Down", color = Color.White,  fontWeight = FontWeight.Bold,)
                            Text(text = "Cesar.Rodriguezl@hotmail.com", color = Color.White)

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
                        var text by remember { mutableStateOf(TextFieldValue("")) }
                        TextField(
                            value = text,
                            label = { Text(text = "Email") },
                            onValueChange = { text = it },
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
                            navController.navigate(Routes.Login.createRoute("cesar.rodrigezL@hotmail.com"))
                        },
                        shape = RoundedCornerShape(10.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = colorResource(R.color.teal_700)
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                    )
                    {
                        Text(text = "Continue")
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
                            .height(50.dp)
                    )
                    {
                        Row(
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
                                fontSize = 17.sp,
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(top = 5.dp)
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
                            .height(50.dp)
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
                                fontSize = 17.sp,
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(top = 5.dp)
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
                            .height(50.dp)
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
                                fontSize = 17.sp,
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(top = 5.dp)
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
                            textAlign = TextAlign.Center
                        )
                    }
                    Spacer(modifier = Modifier.height(paddingSpacer))
                    Text(
                        text = "Forgot your password",
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