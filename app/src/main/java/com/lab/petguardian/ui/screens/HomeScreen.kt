package com.lab.petguardian.ui.screens

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import com.lab.petguardian.R
import com.lab.petguardian.data.AuthManager
import com.lab.petguardian.ui.navigation.AuthenticationGraph
import com.lab.petguardian.ui.navigation.Graph

@Composable
fun HomeScreen(authManager: AuthManager, onClickLogout: () -> Unit, navController: NavController) {

    var user = authManager.getCurrentUser()

    val context = LocalContext.current

    var showDialog by remember { mutableStateOf(false) }

    fun onclickLogoutDialog() {
        showDialog = true
    }

    val onLogoutConfirmed: () -> Unit = {
        val sharedPreferences = context.getSharedPreferences("user_prefers", Context.MODE_PRIVATE)
        with(sharedPreferences.edit()) {
            clear()
            apply()
        }
        onClickLogout()
    }

    Scaffold { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = padding.calculateBottomPadding())
        ) {
            Box {
                if (showDialog) {
                    LogoutDialog(
                        onConfirmLogout = { onLogoutConfirmed() },
                        onDismiss = { showDialog = false },
                        onClickProfile = { /*TODO*/ })
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 5.dp, horizontal = 20.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Card(
                    shape = RectangleShape,
                    colors = CardDefaults.cardColors(containerColor = Color.Transparent),
                ) {
                    Text(text = "Welcome back")
                    Text(text = user?.email ?: "Anonimo", fontSize = 25.sp, fontWeight = FontWeight.Bold)
                }
                Card(
                    modifier = Modifier
                        .size(54.dp)
                        .clip(CircleShape)
                        .clickable { onclickLogoutDialog() },
                    colors = CardDefaults.cardColors(containerColor = Color.Transparent),
                    elevation = CardDefaults.cardElevation(5.dp)
                ) {
                    Image(
                        painter = painterResource(R.mipmap.cat_love_dog),
                        contentDescription = "Foto de perfil por defecto",
                        contentScale = ContentScale.Crop
                    )
                }

            }
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                item {
                    Card(
                        shape = RoundedCornerShape(20.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.Transparent),
                        border = CardDefaults.outlinedCardBorder(),
                        modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp)
                    ) {
                        Row(
                            modifier = Modifier.padding(10.dp),
                            horizontalArrangement = Arrangement.End,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(imageVector = Icons.Default.Add, contentDescription = "plus")
                            Text(text = "Add pet", fontWeight = FontWeight.Bold)
                        }
                    }

                    LazyRow(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                        contentPadding = PaddingValues(horizontal = 16.dp)
                    ) {
                        item {
                            PetItem()
                            PetItem()
                            PetItem()
                        }
                    }

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp)
                            .padding(top = 16.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.Transparent)
                    ) {
                        Text(
                            text = "Next Plans",
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp
                        )
                        Divider(modifier = Modifier.padding(vertical = 10.dp))
                        LazyColumn(modifier = Modifier.height(300.dp)) {
                            items(6) {
                                PlanItem()
                            }
                        }
                    }
                }

            }
        }
    }
}

@Composable
fun LogoutDialog(
    onConfirmLogout: () -> Unit,
    onDismiss: () -> Unit,
    onClickProfile: () -> Unit
) {
    Dialog(
        onDismissRequest = { onDismiss() }
    ) {
        Card {
            Column(
                modifier = Modifier.padding(5.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TextButton(onClick = { onClickProfile() }) {
                    Icon(imageVector = Icons.Filled.AccountCircle, contentDescription = "account")
                    Spacer(modifier = Modifier.width(5.dp))
                    Text(text = "My Profile")
                }
                Divider()
                TextButton(onClick = { onConfirmLogout() }) {
                    Icon(imageVector = Icons.AutoMirrored.Filled.Logout, contentDescription = "logout")
                    Spacer(modifier = Modifier.width(5.dp))
                    Text(text = "Logout")
                }
            }
        }
    }
}


@Composable
fun PetItem() {
    Card(
        modifier = Modifier
            .width(130.dp)
            .padding(horizontal = 5.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(30.dp),
        elevation = CardDefaults.cardElevation(5.dp)
    ) {
        Column {
            Image(
                painter = painterResource(R.mipmap.cat_playing),
                contentDescription = "Foto de perfil por defecto",
            )
            Column(
                modifier = Modifier.padding(10.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Card(
                    colors = CardDefaults.cardColors(containerColor = Color.Transparent),
                    shape = RectangleShape
                ) {
                    Text(text = "2 years old", fontSize = 10.sp)
                    Text(text = "Michina", fontWeight = FontWeight.Bold)
                }
                CommonButtonItem(onClick = { /*TODO*/ }, text = "View")
            }

        }
    }
}

@Composable
fun CommonButtonItem(onClick: () -> Unit, text: String, modifier: Modifier? = null) {
    Button(
        modifier = Modifier.fillMaxWidth(),
        onClick = { onClick() },
        shape = RoundedCornerShape(30.dp),
    ) {
        Text(text = text, fontWeight = FontWeight.Bold)
    }
}

@Composable
fun PlanItem() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 5.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = "08/09/2024")
        Spacer(modifier = Modifier.width(20.dp))
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(25.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            Row(
                modifier = Modifier.padding(10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(R.mipmap.dog_hero_hd),
                    contentDescription = "Foto de perfil por defecto",
                    modifier = Modifier
                        .size(50.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.width(10.dp))
                Card(
                    shape = RectangleShape,
                    colors = CardDefaults.cardColors(containerColor = Color.Transparent)
                ) {
                    Text(text = "Bebito", fontWeight = FontWeight.Bold)
                    Text(text = "Proxima desparasitacion")
                }
            }
        }
    }
}

/*

@Preview(showSystemUi = true)
@Composable
fun HomeScreenPreview() {
    PetGuardianTheme {
        HomeScreen()
    }
}

@Preview(showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun HomeScreenPreviewNight() {
    PetGuardianTheme {
        HomeScreen()
    }
}*/
