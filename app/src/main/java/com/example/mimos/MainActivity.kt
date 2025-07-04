package com.example.mimos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.mimos.ui.theme.MimosTheme
import com.example.mimos.screens.components.SearchBar
import com.example.mimos.screens.MainScreen
import com.example.mimos.screens.components.FadeSlideIn
import com.example.mimos.screens.components.FondoHuellas


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MimosTheme {
                FondoHuellas {
                    FadeSlideIn(            // ya incluye rebote
                        delayMs = 0,
                        initialY = 80.dp,   // más alto = mayor desplazamiento inicial
                        overshoot = 1.12f   // sube para un rebote más marcado
                    )
                    {
                    MainScreen()}
                }
            }
        }
    }
}
