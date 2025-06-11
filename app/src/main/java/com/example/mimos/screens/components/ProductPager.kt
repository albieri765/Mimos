package com.example.mimos.screens.components

import com.google.accompanist.pager.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.mimos.R


@OptIn(ExperimentalPagerApi::class)
@Composable
fun ProductPager() {
    val pagerState = rememberPagerState()
    val images = listOf(
        R.drawable.logo, // reemplaza con tus imágenes reales
        R.drawable.logo,
        R.drawable.logo
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
    ) {
        HorizontalPager(
            count = images.size,
            state = pagerState,
            modifier = Modifier.fillMaxSize()
        ) { page ->
            Image(
                painter = painterResource(id = images[page]),
                contentDescription = "Banner $page",
                modifier = Modifier.fillMaxSize()
            )
        }

        HorizontalPagerIndicator(
            pagerState = pagerState,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 8.dp)
        )
    }
}
