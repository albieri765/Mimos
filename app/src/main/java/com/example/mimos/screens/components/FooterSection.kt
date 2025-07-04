package com.example.mimos.screens.components

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.mimos.R

@Composable
fun FooterSection() {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Redes sociales
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Image(
                painter = painterResource(R.drawable.facebook),
                contentDescription = "Facebook",
                modifier = Modifier
                    .size(32.dp)
                    .clickable {
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/profile.php?id=61573128476366")
                        )
                        context.startActivity(intent)
                    }
            )
            Image(
                painter = painterResource(R.drawable.instagram),
                contentDescription = "Instagram",
                modifier = Modifier
                    .size(32.dp)
                    .clickable {
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.tiktok.com/@mimos.petclubtacna?_t=ZM-8xjJVZoeyIi&_r=1")
                        )
                        context.startActivity(intent)
                    }
            )
            Image(
                painter = painterResource(R.drawable.youtube),
                contentDescription = "YouTube",
                modifier = Modifier
                    .size(32.dp)
                    .clickable {
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://youtube.com"))
                        context.startActivity(intent)
                    }
            )
        }

        // Botones de tiendas
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Image(
                painter = painterResource(R.drawable.google_play),
                contentDescription = "Google Play",
                modifier = Modifier
                    .height(48.dp)
                    .clickable {
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com"))
                        context.startActivity(intent)
                    }
            )
            Image(
                painter = painterResource(R.drawable.app_store),
                contentDescription = "App Store",
                modifier = Modifier
                    .height(48.dp)
                    .clickable {
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.apple.com/app-store/"))
                        context.startActivity(intent)
                    }
            )
            Image(
                painter = painterResource(R.drawable.app_gallery),
                contentDescription = "App Gallery",
                modifier = Modifier
                    .height(48.dp)
                    .clickable {
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://appgallery.huawei.com"))
                        context.startActivity(intent)
                    }
            )
        }
    }
}
