package com.groot.documentscanner.widget

import android.content.Context
import android.content.Intent
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.glance.GlanceId
import androidx.glance.GlanceModifier
import androidx.glance.GlanceTheme
import androidx.glance.Image
import androidx.glance.ImageProvider
import androidx.glance.action.clickable
import androidx.glance.appwidget.CircularProgressIndicator
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.SizeMode
import androidx.glance.appwidget.action.actionStartActivity
import androidx.glance.appwidget.appWidgetBackground
import androidx.glance.appwidget.cornerRadius
import androidx.glance.appwidget.provideContent
import androidx.glance.background
import androidx.glance.layout.Alignment
import androidx.glance.layout.Box
import androidx.glance.layout.Column
import androidx.glance.layout.fillMaxWidth
import androidx.glance.layout.padding
import androidx.glance.layout.wrapContentSize
import androidx.glance.text.FontStyle
import androidx.glance.text.Text
import androidx.glance.text.TextAlign
import androidx.glance.text.TextStyle
import com.groot.documentscanner.MainActivity
import com.groot.documentscanner.R


class DocScannerWidget : GlanceAppWidget() {


    companion object {
        internal val ICON_SQUARE = DpSize(50.dp, 50.dp)
        internal val SMALL_SQUARE = DpSize(100.dp, 100.dp)
        internal val MEDIUM_SQUARE = DpSize(150.dp, 150.dp)
    }

    override val sizeMode = SizeMode.Responsive(
        setOf(
            ICON_SQUARE,
            SMALL_SQUARE,
            MEDIUM_SQUARE,
        )
    )

    override suspend fun provideGlance(context: Context, id: GlanceId) {
        provideContent {
            DocumentScannerWidget {
                actionStartActivity(
                    Intent(context.applicationContext, MainActivity::class.java)
                        .setAction(Intent.ACTION_VIEW)
                        .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                )
            }
        }
    }

    @Composable
    fun DocumentScannerWidget(onClick: () -> Unit) {
        GlanceTheme {
            Box(
                modifier = GlanceModifier
                    .wrapContentSize()
                    .appWidgetBackground()
                    .background(GlanceTheme.colors.background)
                    .cornerRadius(16.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    modifier = GlanceModifier
                        .wrapContentSize()
                        .clickable(onClick)
                        .padding(12.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        provider = ImageProvider(R.drawable.ic_document_scanner),
                        contentDescription = "",
                        modifier = GlanceModifier.wrapContentSize()
                    )
                    Text(
                        text = "Document Scanner",
                        style = TextStyle(
                            color = GlanceTheme.colors.onSurface,
                            fontSize = 22.sp,
//                            fontStyle = FontStyle.Italic,
                            textAlign = TextAlign.Center,
//                    textDecoration = TextDecoration.Underline
                        ),
                        modifier = GlanceModifier
                            .fillMaxWidth()
                            .padding(top = 8.dp)
                            .background(GlanceTheme.colors.surface)
                    )

                    Text(
                        text = "Scan Document and share with ease",
                        style = TextStyle(
                            color = GlanceTheme.colors.onSurface,
                            fontSize = 12.sp,
                            fontStyle = FontStyle.Italic,
                            textAlign = TextAlign.Center,
//                    textDecoration = TextDecoration.Underline
                        ),
                        modifier = GlanceModifier
                            .fillMaxWidth()
                            .padding(top = 3.dp)
                            .background(GlanceTheme.colors.surface)
                    )
                }
            }
        }
    }


    @Composable
    private fun LoadingState() {
        CircularProgressIndicator()
    }


}