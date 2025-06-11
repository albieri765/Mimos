package com.example.mimos.screens.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.mimos.R

@Composable
fun ProductPager(onImageClick: (Int) -> Unit) {
    val pagerState = rememberPagerState(pageCount = { 3 })

    val images = listOf(
        R.drawable.promoa,
        R.drawable.promob,
        R.drawable.promoc
    )

    HorizontalPager(state = pagerState, modifier = Modifier.height(180.dp)) { page ->
        Image(
            painter = painterResource(id = images[page]),
            contentDescription = "Banner $page",
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onImageClick(page) }
        )
    }
}
