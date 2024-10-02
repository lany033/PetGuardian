package com.lab.petguardian.ui.screens

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.lazy.items
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.lab.petguardian.R
import com.lab.petguardian.data.AuthManager
import com.lab.petguardian.ui.common.CommonSelectorButtomItem
import com.lab.petguardian.ui.common.CommonLogoutDialog
import com.lab.petguardian.ui.common.CommonPetItem
import com.lab.petguardian.ui.common.CommonPlanItem
import com.lab.petguardian.ui.common.CommonTextTitle
import com.lab.petguardian.ui.theme.Geraldine

@Composable
fun HomeScreen(
    onClickLogout: () -> Unit,
    onClickProfile: () -> Unit,
    onClickAddPet: () -> Unit,
    onClickDetailPet: (String) -> Unit
) {

    val homeViewModel: HomeViewModel = hiltViewModel()

    val homeUisState: HomeUiState by homeViewModel.homeUiState.collectAsState()

    val user = homeViewModel.getUser()

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
                    CommonLogoutDialog(
                        onConfirmLogout = { homeViewModel.signOut(navigateToWelcomeScreen = { onLogoutConfirmed() }, context = context) },
                        onDismiss = { showDialog = false },
                        onClickProfile = { onClickProfile() })
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
                    Text(
                        text = user?.displayName ?: "Anonimo",
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                Card(
                    modifier = Modifier
                        .size(54.dp)
                        .clip(CircleShape)
                        .clickable { onclickLogoutDialog() },
                    colors = CardDefaults.cardColors(containerColor = Color.Transparent),
                    elevation = CardDefaults.cardElevation(10.dp)
                ) {
                    if (user?.photoUrl != null) {
                        AsyncImage(
                            model = ImageRequest.Builder(LocalContext.current)
                                .data(user.photoUrl)
                                .crossfade(true).build(),
                            contentDescription = "Photo",
                            placeholder = painterResource(id = R.drawable.account_circle),
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize()
                        )
                    } else {
                        Image(
                            painter = painterResource(R.mipmap.cat_love_dog),
                            contentDescription = "Foto de perfil por defecto",
                            contentScale = ContentScale.Crop
                        )
                    }
                }
            }
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                item {
                    LazyRow(
                        modifier = Modifier.height(200.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
                    ) {
                        items(homeUisState.petListByUser) {pet ->
                            CommonPetItem(onClickPetView = {onClickDetailPet(pet.id)}, namePet = pet.name)
                        }
                        item {
                            CommonSelectorButtomItem(
                                "Add Pet",
                                onClickAddPet = { onClickAddPet() },
                                icon = Icons.Default.Add,
                                color = Geraldine
                            )
                        }
                    }

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp)
                            .padding(top = 16.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.Transparent)
                    ) {
                        CommonTextTitle("Next Plans")
                        Divider(modifier = Modifier.padding(vertical = 10.dp))
                        LazyColumn(modifier = Modifier.height(300.dp)) {
                            items(homeUisState.petPlansList) {petPlan->
                                CommonPlanItem(title = petPlan.title, date = petPlan.date, namePet = petPlan.petName)
                            }
                        }
                    }
                }

            }
        }
    }
}

