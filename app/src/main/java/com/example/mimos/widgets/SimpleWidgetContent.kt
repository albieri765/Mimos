package com.example.mimos.widgets

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.glance.*
import androidx.glance.action.actionStartActivity
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.provideContent
import androidx.glance.layout.*
import androidx.glance.text.Text          // ✅ Glance Text
import com.example.mimos.MainActivity

class SimpleWidgetContent : GlanceAppWidget() {

    override suspend fun provideGlance(context: Context, id: GlanceId) {
        provideContent { GlanceTheme { WidgetBody() } }
    }

    @Composable
    private fun WidgetBody() {
        Column(
            modifier = GlanceModifier
                .fillMaxSize()
                .background(Color(0xFFFDF6EC))
                .padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalAlignment   = Alignment.CenterVertically
        ) {
            Text("MIMOS TE ESPERA")                  // de androidx.glance.text

            Spacer(GlanceModifier.height(8.dp))      // de androidx.glance.layout

            Button(                                  // de androidx.glance
                text = "Home",
                onClick = actionStartActivity<MainActivity>()
            )
        }
    }
}
