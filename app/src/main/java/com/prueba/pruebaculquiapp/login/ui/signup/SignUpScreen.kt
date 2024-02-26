package com.prueba.pruebaculquiapp.login.ui.signup

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
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
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
fun SignUpScreen(
    signUpViewModel: SignUpViewModel,
    navController: NavHostController,
    email: String
) {
    Background()
    Header(navController)
    Body(signUpViewModel, navController, email)
    LaunchedEffect(Unit) {
        signUpViewModel.getUser(email)
    }
}

@Composable
fun Body(signUpViewModel: SignUpViewModel, navController: NavHostController, email: String) {
    val state by signUpViewModel.state.collectAsState()
    val userState by signUpViewModel.userState.collectAsState()
    val emailText by signUpViewModel.emailStateFlow.collectAsState()
    val passwordText by signUpViewModel.passwordStateFlow.collectAsState()
    var isTextFieldFocused by remember { mutableStateOf(false) }
    var passwordVisibility by remember { mutableStateOf(false) }
    val paddingSpacer = 20.dp

    when (state) {
        is SignUpState.Loading -> {
        }
        is SignUpState.Success -> {
            navController.navigate(Routes.Home.route)
        }
        is SignUpState.Error -> {
        }
    }
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
                        text = email,
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
                        TextField(
                            value = "${userState?.firstName} ${userState?.lastName}",
                            label = { Text(text = "Name") },
                            onValueChange = { signUpViewModel.onSignupChange(it, passwordText) },
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
                        TextField(
                            value = passwordText,
                            label = { Text(text = "Password") },
                            onValueChange = { signUpViewModel.onSignupChange(emailText, it) },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                            singleLine = true,
                            maxLines = 1,
                            trailingIcon = {
                                Text(
                                    text = if (passwordVisibility) "Hide" else "View",
                                    textAlign = TextAlign.End,
                                    modifier = Modifier
                                        .padding(end = 15.dp)
                                        .clickable {
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
                            modifier = Modifier
                                .fillMaxWidth()
                                .onFocusChanged {
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
                            fontSize = 16.sp,
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
                            signUpViewModel.sendRegister(email, passwordText)
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
fun Header(navController: NavHostController) {
    Box(
        Modifier
            .fillMaxSize()
            .padding(5.dp)
    ) {
        Icon(
            imageVector = Icons.Default.KeyboardArrowLeft,
            contentDescription = "back",
            tint = Color.White,
            modifier = Modifier.clickable {
                navController.popBackStack()
            }
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