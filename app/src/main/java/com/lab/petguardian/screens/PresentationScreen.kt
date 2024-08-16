package com.lab.petguardian.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.lab.petguardian.R

@Preview
@Composable
fun PresentationScreen() {

    Box(modifier = Modifier.fillMaxSize()) {
        HorizontalPagerHomeScreen(Modifier.align(Alignment.Center))
        Button(modifier = Modifier
            .align(Alignment.BottomCenter)
            .padding(bottom = 60.dp).fillMaxWidth(), onClick = { /*TODO*/ }) {
            Text(text = "Get Started")
        }
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HorizontalPagerHomeScreen(modifier: Modifier) {

    val listPresentation = listOf(
        com.lab.petguardian.model.presentation.Presentation(
            "Title 1",
            "Description 1",
            R.mipmap.dog_hero
        ),
        com.lab.petguardian.model.presentation.Presentation(
            "Title 2",
            "Description 2",
            R.mipmap.cat_dog_2
        ),
        com.lab.petguardian.model.presentation.Presentation(
            "Title 3",
            "Description 3",
            R.mipmap.cat_playing
        )
    )

    val pagerState = rememberPagerState(pageCount = { listPresentation.size })

    Column(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
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
            modifier = Modifier.padding(12.dp),
            colors = CardDefaults.cardColors(containerColor = Color.Transparent)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                repeat(listPresentation.size) { iteration ->
                    val color =
                        if (pagerState.currentPage == iteration) Color.White else Color.LightGray
                    Box(
                        modifier = Modifier
                            .padding(4.dp)
                            .background(color, CircleShape)
                            .size(10.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun CarrouselItem(image: Int, title: String, description: String) {
    Column(modifier= Modifier.padding(start = 20.dp, end = 20.dp),horizontalAlignment = Alignment.CenterHorizontally) {
        Card(shape = RoundedCornerShape(25.dp),modifier = Modifier.size(400.dp)) {
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
                color = Color.Red,
                fontSize = 35.sp,
                fontWeight = FontWeight.Bold,
            )
            Text(
                text = description,
                color = Color.Red,
                fontSize = 12.sp,
                maxLines = 2,
                lineHeight = 12.sp
            )
        }
    }
}