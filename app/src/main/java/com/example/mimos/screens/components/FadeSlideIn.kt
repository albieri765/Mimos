// FadeSlideIn.kt  (rebote incluido)
package com.example.mimos.screens.components   // ajusta a tu paquete

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Hace aparecer su [content] con:
 *  - Fade‑in
 *  - Deslizamiento vertical
 *  - Pequeño rebote (spring)
 *
 * @param delayMs     Retraso antes de iniciar la animación.
 * @param initialY    Desplazamiento vertical inicial.
 * @param overshoot   Cuánto rebota (>1f = rebote, 1f = sin rebote).
 *                    1.1f da un rebote suave; 1.2f un poco más marcado.
 */
@Composable
fun FadeSlideIn(
    delayMs: Int = 0,
    initialY: Dp = 60.dp,
    overshoot: Float = 1.1f,
    content: @Composable () -> Unit
) {
    var visible by remember { mutableStateOf(false) }

    // Alpha (desvanecimiento)
    val alpha by animateFloatAsState(
        targetValue = if (visible) 1f else 0f,
        animationSpec = tween(durationMillis = 400, delayMillis = delayMs),
        label = "alpha"
    )

    // Desplazamiento vertical con spring
    val offsetY by animateDpAsState(
        targetValue = if (visible) 0.dp else initialY,
        animationSpec = spring(
            stiffness = Spring.StiffnessLow,
            dampingRatio = 0.6f,               // controla rebote
            visibilityThreshold = 0.5.dp
        ),
        label = "offsetY"
    )

    // Escala para efecto de overshoot (opcional, pero le da vida)
    val scale by animateFloatAsState(
        targetValue = if (visible) 1f else overshoot,
        animationSpec = spring(
            stiffness = Spring.StiffnessLow,
            dampingRatio = 0.6f,
        ),
        label = "scale"
    )

    // Lanzar una sola vez
    LaunchedEffect(Unit) { visible = true }

    Box(
        modifier = Modifier
            .graphicsLayer(scaleX = scale, scaleY = scale)
            .offset(y = offsetY)
            .alpha(alpha)
    ) {
        content()
    }
}
