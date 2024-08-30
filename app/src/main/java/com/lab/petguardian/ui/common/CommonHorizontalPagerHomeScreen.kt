package com.lab.petguardian.ui.common

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.lab.petguardian.R
import com.lab.petguardian.model.presentation.Presentation
import com.lab.petguardian.ui.theme.Geraldine
import com.lab.petguardian.ui.theme.PalePrim
import com.lab.petguardian.ui.theme.SaffronMango


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CommonHorizontalPagerHomeScreen(modifier: Modifier) {

    val listPresentation = listOf(

        Presentation(
            "Title 1",
            "Description 1",
            R.mipmap.dog_hero_hd
        ),
        Presentation(
            "Title 2",
            "Description 2",
            R.mipmap.cat_love_dog
        ),
        Presentation(
            "Title 3",
            "Description 3",
            R.mipmap.cat_playing
        )
    )

    val pagerState = rememberPagerState(pageCount = { listPresentation.size })

    Column(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HorizontalPager(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            state = pagerState, pageSize = PageSize.Fill,
            pageSpacing = 20.dp
        ) {
            CommonCarrouselItem(
                image = listPresentation[it].painter,
                title = listPresentation[it].title,
                description = listPresentation[it].description,
            )
        }
        Card(
            modifier = Modifier.padding(10.dp),
            colors = CardDefaults.cardColors(containerColor = SaffronMango)
        ) {
            Row(
                horizontalArrangement = Arrangement.Center
            ) {
                repeat(listPresentation.size) { iteration ->
                    val color =
                        if (pagerState.currentPage == iteration) PalePrim else Geraldine
                    Box(
                        modifier = Modifier
                            .padding(5.dp)
                            .background(color, CircleShape)
                            .size(5.dp)
                    )
                }
            }
        }
    }
}