package com.groot.documentscanner.ui.screen

import androidx.compose.runtime.Composable
import com.groot.documentscanner.utility.PDFReader
import java.io.File

@Composable
fun PdfPreview(pdfFilePath: String) {
    PDFReader(file = File(pdfFilePath))
}