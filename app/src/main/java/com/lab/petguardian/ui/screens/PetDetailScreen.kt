package com.lab.petguardian.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Balance
import androidx.compose.material.icons.filled.CatchingPokemon
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Palette
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lab.petguardian.R
import com.lab.petguardian.ui.theme.Geraldine

@Composable
fun PetDetailScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(R.mipmap.cat_love_dog),
            contentDescription = "Foto de perfil por defecto",
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.TopCenter),
            contentScale = ContentScale.Crop
        )
        Card(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth(), shape = RoundedCornerShape(30.dp),
            colors = CardDefaults.cardColors(containerColor = Geraldine)
        ) {
            Card(
                modifier = Modifier.padding(horizontal = 30.dp).padding(top = 20.dp),
                shape = RectangleShape,
                colors = CardDefaults.cardColors(containerColor = Color.Transparent)
            ) {
                Text(text = "Michina", fontWeight = FontWeight.Bold, fontSize = 25.sp)
                Text(text = "2 years old")
                Spacer(modifier = Modifier.height(20.dp))
                Row(modifier = Modifier.padding(bottom = 20.dp)) {
                    Card(shape = RectangleShape) {
                        Icon(
                            modifier = Modifier.size(30.dp),
                            imageVector = Icons.Default.CatchingPokemon,
                            contentDescription = "pet"
                        )
                        Text(text = "Sex")
                        Text(text = "Male", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                    }
                    Card {
                        Icon(imageVector = Icons.Default.Palette, contentDescription = "pet")
                        Text(text = "Sex")
                        Text(text = "Male")
                    }
                    Card {
                        Icon(imageVector = Icons.Default.Check, contentDescription = "pet")
                        Text(text = "Sex")
                        Text(text = "Male")
                    }
                    Card {
                        Icon(imageVector = Icons.Default.Balance, contentDescription = "pet")
                        Text(text = "Sex")
                        Text(text = "Male")
                    }
                }
            }

            Card(
                modifier = Modifier
                    .height(250.dp)
            ) {
                Text(
                    modifier = Modifier.padding(horizontal = 20.dp).padding(top = 20.dp),
                    text = "Next Plans",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
                LazyColumn(modifier = Modifier.padding(20.dp)) {
                    items(6) {
                        PlanItem()
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun PetDetailScreenPreview() {
    PetDetailScreen()
}