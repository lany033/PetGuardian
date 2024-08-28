package com.lab.petguardian.ui.screens

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.lab.petguardian.R
import com.lab.petguardian.ui.common.CommonButton
import com.lab.petguardian.ui.theme.Geraldine
import com.lab.petguardian.ui.theme.PetGuardianTheme
import com.lab.petguardian.ui.theme.SaffronMango

@Composable
fun HomeScreen() {

    Scaffold { padding ->
        Column(modifier = Modifier.fillMaxSize().padding(top = padding.calculateBottomPadding())) {
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
                    Text(text = "Melanie Mantilla", fontSize = 30.sp, fontWeight = FontWeight.Bold)
                }
                Card(
                    modifier = Modifier.size(54.dp),
                    shape = CircleShape,
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
                        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp).padding(top = 16.dp),
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

@Preview
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
}