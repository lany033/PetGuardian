package com.lab.petguardian.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import com.lab.petguardian.R
import java.io.File


@Composable
fun LauncherScreen(){
    Card(colors = CardDefaults.cardColors(containerColor = Color(0xFFbdfe4c))) {
        Image(
            painter = rememberAsyncImagePainter(R.mipmap.logo_petguardian_greenyellow),
            contentDescription = null,
            contentScale = ContentScale.FillWidth,
            modifier = Modifier.fillMaxSize()
        )
    }
}
