package com.prueba.pruebaculquiapp.login.ui.signup

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
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.layout.ContentScale
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
fun SignUpScreen(navController: NavHostController, email: String) {
    Background()
    Header()
    Body(navController)
}

@Composable
fun Body(navController: NavHostController) {
    var isTextFieldFocused by remember { mutableStateOf(false) }
    val paddingSpacer = 20.dp
    Box {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.85f)
                .align(Alignment.BottomCenter)
        ) {
            Text(
                text = "Sign up",
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
                    Text(
                        text = "Looks like you don,t have an account. Let's create a new account for",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Light,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        color = Color.White,
                    )
                    Text(
                        text = "Cesar.Rodriguezl@hotmail.com",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        color = Color.White,
                    )
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
                            label = { Text(text = "Name") },
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
                            label = { Text(text = "Password") },
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
                    Column(
                        modifier = Modifier
                            .padding(start = 10.dp),
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "By selecting Agree and continue below,",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Light,
                            color = Color.White,
                        )
                        Row {
                            Text(
                                text = "I agree to ",
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Light,
                                color = Color.White,
                            )
                            Text(
                                text = "Terms of Service and Privacy Policy",
                                fontSize = 15.sp,
                                color = colorResource(
                                    id = R.color.teal_700
                                )
                            )
                        }
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
                            .height(55.dp)
                    )
                    {
                        Text(text = "Agree and continue", fontSize = 18.sp)
                    }

                    Spacer(modifier = Modifier.height(paddingSpacer))
                    Text(
                        text = "Forgot your password?",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(55.dp),
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