package com.lab.petguardian.ui.common

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CommonSelectorButtomItem(text: String, onClickAddPet: () -> Unit, icon: ImageVector, color: Color, modifier: Modifier? = null) {
    Button(
        modifier = modifier ?: Modifier
            .width(130.dp)
            .fillMaxHeight()
            .padding(5.dp),
        onClick = { onClickAddPet() },
        shape = RoundedCornerShape(30.dp),
        border = BorderStroke(3.dp, color),
        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.background),
        elevation = ButtonDefaults.elevatedButtonElevation(defaultElevation = 5.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = "add pet",
                modifier = Modifier.size(50.dp),
                tint = color
            )
            Text(text = text, fontWeight = FontWeight.Bold, fontSize = 18.sp, color = color)
        }
    }
}

