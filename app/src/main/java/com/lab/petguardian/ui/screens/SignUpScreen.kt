package com.lab.petguardian.ui.screens

import android.content.res.Configuration
import android.provider.ContactsContract.CommonDataKinds
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lab.petguardian.ui.common.CommonBackButton
import com.lab.petguardian.ui.common.CommonButton
import com.lab.petguardian.ui.common.CommonTextFieldWithTextAbove
import com.lab.petguardian.ui.theme.PetGuardianTheme


@Composable
fun SignUpScreen() {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize(), verticalArrangement = Arrangement.spacedBy(32.dp)
    ) {
        CommonBackButton(onClickBackButton = {})
        Card(
            colors = CardDefaults.cardColors(containerColor = Color.Transparent),
            shape = RectangleShape
        ) {
            Text(text = "Create Account", fontSize = 25.sp, fontWeight = FontWeight.Bold)
            Text(text = "Register with your E-mail address")
        }
        CommonTextFieldWithTextAbove(textAbove = "ADD AN E-MAIL ADDRESS", placeholderText = "E-mail address")
        CommonTextFieldWithTextAbove(textAbove = "CREATE PASSWORD", placeholderText = "Password")
        CommonButton(onClick = { /*TODO*/ }, text = "Sign Up", color = Color.Blue)
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun SignUpLightPreview(){
    PetGuardianTheme {
        SignUpScreen()
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun SignUpPreviewDark(){
    PetGuardianTheme {
        SignUpScreen()
    }
}




