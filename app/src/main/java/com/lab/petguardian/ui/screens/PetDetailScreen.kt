package com.lab.petguardian.ui.screens

import android.content.res.Configuration
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContent
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Balance
import androidx.compose.material.icons.filled.CatchingPokemon
import androidx.compose.material.icons.filled.Female
import androidx.compose.material.icons.filled.Male
import androidx.compose.material.icons.filled.Palette
import androidx.compose.material.icons.filled.Pets
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import com.lab.petguardian.R
import com.lab.petguardian.petAge
import com.lab.petguardian.ui.common.CommonBackButton
import com.lab.petguardian.ui.common.CommonPlanItem
import com.lab.petguardian.ui.theme.Geraldine
import com.lab.petguardian.ui.theme.PetGuardianTheme

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun PetDetailScreen(petId: String?, onClickBackButton: () -> Unit) {
    val context = LocalContext.current
    val petDetailViewModel: PetDetailViewModel = hiltViewModel()

    val petDetailState by petDetailViewModel.petDetailState.collectAsState()

    Scaffold(topBar = {
        CommonBackButton(
            modifier = Modifier
                .padding(top = WindowInsets.safeContent.asPaddingValues().calculateTopPadding()),
            onClickBackButton = {  onClickBackButton() }
        )
    }) { it ->

        LaunchedEffect(key1 = petId) {
            if (petId != null) {
                petDetailViewModel.getPetById(petId)
            } else {
                Toast.makeText(context, "Pet not found", Toast.LENGTH_SHORT).show()
            }
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = it.calculateBottomPadding())
        ) {
            Image(
                painter = painterResource(R.mipmap.cat_love_dog),
                contentDescription = "Foto de perfil por defecto",
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.FillWidth
            )
            Box(
                modifier = Modifier
                    .matchParentSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(Color.Transparent, Color.Black.copy(alpha = 0.3f)),
                            startY = 500f,
                            endY = 0f
                        )
                    )
            )
            Card(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .height(50.dp)
                    .fillMaxWidth(),
                shape = RectangleShape
            ) {}
            Card(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .zIndex(1f), shape = RoundedCornerShape(30.dp),
                colors = CardDefaults.cardColors(containerColor = Geraldine)
            ) {
                Card(
                    modifier = Modifier
                        .padding(horizontal = 30.dp)
                        .padding(top = 20.dp),
                    shape = RectangleShape,
                    colors = CardDefaults.cardColors(containerColor = Color.Transparent)
                ) {
                    Text(text = petDetailState.namePet, fontWeight = FontWeight.Bold, fontSize = 25.sp)
                    Text(text = "${petDetailState.ages} years")
                    Spacer(modifier = Modifier.height(20.dp))
                    Row(
                        modifier = Modifier
                            .padding(bottom = 20.dp)
                            .fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround
                    ) {
                        PetFeatureItem(
                            featureName = "Type",
                            featureValue = petDetailState.type,
                            icon = Icons.Default.CatchingPokemon
                        )

                        PetFeatureItem(
                            featureName = "Sex",
                            featureValue = petDetailState.gender,
                            icon = if (petDetailState.gender == "Female") Icons.Default.Female else Icons.Default.Male
                        )
                        PetFeatureItem(
                            featureName = "Weight",
                            featureValue = petDetailState.weight.toString(),
                            icon = Icons.Default.Balance
                        )
                    }
                }
                Card(
                    modifier = Modifier
                        .height(250.dp)
                        .zIndex(1f),
                    shape = RoundedCornerShape(30.dp)
                ) {
                    Text(
                        modifier = Modifier
                            .padding(horizontal = 20.dp)
                            .padding(top = 20.dp),
                        text = "Next Plans",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )
                    LazyColumn(modifier = Modifier.padding(10.dp)) {
                        items(6) {
                            CommonPlanItem()
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun PetFeatureItem(featureName: String, featureValue: String, icon: ImageVector) {
    Column(
        modifier = Modifier.padding(horizontal = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            modifier = Modifier.size(30.dp),
            imageVector = icon,
            contentDescription = "pet"
        )
        Text(text = featureName)
        Text(text = featureValue, fontWeight = FontWeight.Bold, fontSize = 18.sp)
    }
}
