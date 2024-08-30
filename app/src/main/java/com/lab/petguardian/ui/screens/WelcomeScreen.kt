package com.lab.petguardian.ui.screens

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.lab.petguardian.R
import com.lab.petguardian.data.AuthManager
import com.lab.petguardian.data.AuthRes
import com.lab.petguardian.model.presentation.Presentation
import com.lab.petguardian.ui.common.CommonButton
import com.lab.petguardian.ui.common.CommonTextFieldWithTextAbove
import com.lab.petguardian.ui.theme.Geraldine
import com.lab.petguardian.ui.theme.PalePrim
import com.lab.petguardian.ui.theme.SaffronMango
import kotlinx.coroutines.launch


@Composable
fun WelcomeScreen(
    onClickSignUp: () -> Unit,
    onClickForgotPassword: () -> Unit,
    onClickHome: () -> Unit,
    authManager: AuthManager,
) {

    var showBottomSheet by remember { mutableStateOf(false) }

    var context = LocalContext.current

    if (showBottomSheet) {
        LoginBottomSheet(
            onDismiss = { showBottomSheet = false },
            onClickSignUp = { onClickSignUp() },
            onClickForgotPassword = { onClickForgotPassword() },
            onClickHome = { onClickHome() },
            authManager = authManager,
            context = context
        )
    }
    Scaffold(
        contentWindowInsets = WindowInsets.safeDrawing
    ) { innerppading ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerppading)
        ) {
            HorizontalPagerHomeScreen(Modifier.align(Alignment.Center))
            CommonButton(
                onClick = { showBottomSheet = true },
                text = "Get Started",
                modifier = Modifier
                    .align(
                        Alignment.BottomCenter
                    )
                    .padding(horizontal = 16.dp)
                    .padding(bottom = 16.dp)
            )
        }
    }

}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HorizontalPagerHomeScreen(modifier: Modifier) {

    val listPresentation = listOf(

        Presentation(
            "Title 1",
            "Description 1",
            R.mipmap.dog_hero_hd
        ),
        Presentation(
            "Title 2",
            "Description 2",
            R.mipmap.cat_love_dog
        ),
        Presentation(
            "Title 3",
            "Description 3",
            R.mipmap.cat_playing
        )
    )

    val pagerState = rememberPagerState(pageCount = { listPresentation.size })

    Column(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HorizontalPager(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            state = pagerState, pageSize = PageSize.Fill,
            pageSpacing = 20.dp
        ) {
            CarrouselItem(
                image = listPresentation[it].painter,
                title = listPresentation[it].title,
                description = listPresentation[it].description,
            )
        }
        Card(
            modifier = Modifier.padding(10.dp),
            colors = CardDefaults.cardColors(containerColor = SaffronMango)
        ) {
            Row(
                horizontalArrangement = Arrangement.Center
            ) {
                repeat(listPresentation.size) { iteration ->
                    val color =
                        if (pagerState.currentPage == iteration) PalePrim else Geraldine
                    Box(
                        modifier = Modifier
                            .padding(5.dp)
                            .background(color, CircleShape)
                            .size(5.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun CarrouselItem(image: Int, title: String, description: String) {
    Column(
        modifier = Modifier.padding(start = 16.dp, end = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            shape = RoundedCornerShape(25.dp),
            modifier = Modifier.size(400.dp),
            elevation = CardDefaults.cardElevation(10.dp)
        ) {
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = rememberAsyncImagePainter(model = image),
                contentDescription = title,
                contentScale = ContentScale.Crop
            )
        }
        Column(
            modifier = Modifier.padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = title,
                fontSize = 35.sp,
                fontWeight = FontWeight.Bold,
            )
            Text(
                text = description,
                fontSize = 12.sp,
                maxLines = 2,
                lineHeight = 12.sp
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginBottomSheet(
    onDismiss: () -> Unit,
    onClickSignUp: () -> Unit,
    onClickForgotPassword: () -> Unit,
    onClickHome: () -> Unit,
    authManager: AuthManager,
    context: Context
) {

    val modalBottomSheetState = rememberModalBottomSheetState()

    ModalBottomSheet(
        onDismissRequest = { onDismiss() },
        sheetState = modalBottomSheetState,
        dragHandle = { DragHandleCustom() }) {
        PetGuardianLogin(
            onClickSignUp = { onClickSignUp() },
            onClickForgotPassword = { onClickForgotPassword() },
            onClickHome = { onClickHome() },
            authManager = authManager,
            context = context
        )
    }
}

@Composable
fun DragHandleCustom() {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Spacer(modifier = Modifier.height(8.dp))
        Box(
            modifier = Modifier
                .height(4.dp)
                .width(100.dp)
                .background(Geraldine, RoundedCornerShape(4.dp))
        ) {}
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                modifier = Modifier.align(Alignment.TopStart),
                fontSize = 22.sp,
                text = "Continue with",
                fontWeight = FontWeight.Bold,
                color = Geraldine
            )
            Text(
                modifier = Modifier.align(Alignment.TopEnd),
                fontSize = 18.sp,
                text = "Cancel",
                fontWeight = FontWeight.Bold,
                color = Geraldine
            )
        }
        Divider()
    }

}

@Composable
fun PetGuardianLogin(
    onClickSignUp: () -> Unit,
    onClickForgotPassword: () -> Unit,
    onClickHome: () -> Unit,
    authManager: AuthManager,
    context: Context
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var scope = rememberCoroutineScope()



    Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {

        CommonTextFieldWithTextAbove(
            textAbove = "E-MAIL ADDRESS",
            placeholderText = "E-mail address",
            value = email,
            onValueChange = { email = it }
        )
        CommonTextFieldWithTextAbove(
            textAbove = "PASSWORD",
            placeholderText = "Password",
            value = password,
            onValueChange = { password = it }
        )
        CommonButton(onClick = {
            scope.launch {
                emailPassSignIn(
                    email = email,
                    password = password,
                    authManager = authManager,
                    context = context,
                    onClickSignUp = { onClickHome() })
            }
        }, text = "Continue", modifier = Modifier)
        ForgotPassword(onClickForgotPassword = { onClickForgotPassword() })
        SignUp(onClickSignUp = { onClickSignUp() })
        LoginDivider()
        LoginSocialButton(
            onClick = { /*TODO*/ },
            text = "Continue with Google",
            icon = R.drawable.ic_google
        )
        Spacer(modifier = Modifier.height(16.dp))
    }
}

private suspend fun emailPassSignIn(
    email: String,
    password: String,
    authManager: AuthManager,
    context: Context,
    onClickSignUp: () -> Unit
) {
    if (email.isNotEmpty() && password.isNotEmpty()) {
        when (val result = authManager.signInWithEmailAndPassword(email, password)) {
            is AuthRes.Success -> {
                onClickSignUp()
            }

            is AuthRes.Error -> {
                Toast.makeText(context, "Error SignUp: ${result.errorMessage}", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    } else {
        Toast.makeText(context, "Existen campos vacios", Toast.LENGTH_SHORT).show()
    }
}

@Composable
fun LoginSocialButton(onClick: () -> Unit, text: String, icon: Int) {
    Button(
        modifier = Modifier.fillMaxWidth(),
        onClick = { onClick() },
        shape = RoundedCornerShape(12.dp),
        border = BorderStroke(1.dp, Geraldine),
        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
    ) {
        Row(
            modifier = Modifier
                .padding(start = 12.dp, end = 16.dp, top = 10.dp, bottom = 10.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                painter = painterResource(id = icon),
                modifier = Modifier.size(20.dp),
                contentDescription = text,
                tint = Color.Unspecified
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = text, fontSize = 15.sp, color = Geraldine, fontWeight = FontWeight.Bold)
        }
    }
}


@Composable
fun LoginDivider() {
    Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        Divider(
            Modifier
                .height(1.dp)
                .weight(1f),
            color = Geraldine
        )
        Text(
            text = "OR",
            modifier = Modifier.padding(horizontal = 20.dp),
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = Geraldine
        )
        Divider(
            Modifier
                .height(1.dp)
                .weight(1f),
            color = Geraldine
        )
    }
}

@Composable
fun SignUp(onClickSignUp: () -> Unit) {
    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
        Text(text = "Don't have an account?", fontSize = 12.sp, color = SaffronMango)
        Spacer(modifier = Modifier.size(5.dp))
        Text(
            modifier = Modifier.clickable { onClickSignUp() }, text = "Sign up.",
            fontSize = 12.sp,
            textDecoration = TextDecoration.Underline,
            fontWeight = FontWeight.Bold,
            color = Geraldine
        )

    }
}

@Composable
fun ForgotPassword(onClickForgotPassword: () -> Unit) {
    Text(
        modifier = Modifier.clickable { onClickForgotPassword() }, text = "Forgot password?",
        fontSize = 12.sp,
        fontWeight = FontWeight.Bold,
        textDecoration = TextDecoration.Underline,
        color = Geraldine
    )
}


/*
@Preview
@Composable
fun LoginScreenPreview(){
    PetGuardianTheme {
        LoginScreen()
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun LoginScreenPreviewNight(){
    PetGuardianTheme {
        LoginScreen()
    }
}*/
