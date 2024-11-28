package com.groot.documentscanner.receiver

import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.GlanceAppWidgetReceiver
import com.groot.documentscanner.widget.DocScannerWidget

class GlanceSampleReceiver : GlanceAppWidgetReceiver() {

    override val glanceAppWidget: GlanceAppWidget = DocScannerWidget()
}
