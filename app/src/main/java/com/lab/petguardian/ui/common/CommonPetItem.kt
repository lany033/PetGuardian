package com.lab.petguardian.ui.common

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
fun CommonPetItem(onClickPetView: () -> Unit, namePet: String) {
    Card(
        modifier = Modifier
            .width(130.dp)
            .padding(5.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(30.dp),
        elevation = CardDefaults.cardElevation(5.dp)
    ) {
        Column {
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                painter = painterResource(R.mipmap.cat_playing),
                contentDescription = "Foto de perfil por defecto",
                contentScale = ContentScale.Crop,
            )
            Column(
                modifier = Modifier
                    .padding(horizontal = 10.dp)
                    .weight(1f),
                verticalArrangement = Arrangement.SpaceAround
            ) {
                Card(
                    colors = CardDefaults.cardColors(containerColor = Color.Transparent),
                    shape = RectangleShape
                ) {
                    Text(text = "2 years old", fontSize = 10.sp)
                    Text(text = namePet, fontWeight = FontWeight.Bold)
                }
                CommonButtonItem(onClick = { onClickPetView() }, text = "View")
            }

        }
    }
}

@Preview
@Composable
fun AddPetItem() {
    Button(
        modifier = Modifier
            .width(130.dp)
            .fillMaxHeight()
            .padding(5.dp),
        onClick = {  },
        shape = RoundedCornerShape(30.dp),
        border = BorderStroke(3.dp, Geraldine),
        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.background),
        elevation = ButtonDefaults.elevatedButtonElevation(defaultElevation = 5.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "add pet",
                modifier = Modifier.size(50.dp),
                tint = Geraldine
            )
            Text(text = "Add Pet", fontWeight = FontWeight.Bold, fontSize = 18.sp, color = Geraldine)
        }
    }
}



