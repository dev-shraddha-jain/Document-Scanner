package com.groot.documentscanner.ui.screen

import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.google.mlkit.vision.documentscanner.GmsDocumentScannerOptions
import com.google.mlkit.vision.documentscanner.GmsDocumentScannerOptions.RESULT_FORMAT_JPEG
import com.google.mlkit.vision.documentscanner.GmsDocumentScannerOptions.RESULT_FORMAT_PDF
import com.google.mlkit.vision.documentscanner.GmsDocumentScannerOptions.SCANNER_MODE_FULL
import com.google.mlkit.vision.documentscanner.GmsDocumentScanning
import com.google.mlkit.vision.documentscanner.GmsDocumentScanningResult
import com.groot.documentscanner.ui.components.ButtonView
import com.groot.documentscanner.ui.components.CustomTopAppBar
import com.groot.documentscanner.ui.theme.PurpleGrey40

@Composable
fun HomeScreen() {
    val topBgColor = PurpleGrey40

    val activity = LocalContext.current as Activity

    val filePath = remember { mutableStateOf("") }
    val documentName = remember { mutableStateOf("") }
    val pageCountText = remember { mutableStateOf("") }
    val showScannedDocument = remember { mutableStateOf(false) }
    val documentSetting = remember { mutableStateOf(true) }


    val scannerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartIntentSenderForResult(),
        onResult = { result ->
            // Handle the selected content URI
            val result = GmsDocumentScanningResult.fromActivityResultIntent(result.data)
            result?.pages?.let { pages ->
                for (page in pages) {
                    val imageUri = pages[0].imageUri
                }
            }
            result?.pdf?.let { pdf ->
                val pdfUri = pdf.uri

                filePath.value = pdf.uri.path ?: ""
                println("xcxc ${pdf.uri.path}")
                documentSetting.value = false
                showScannedDocument.value = true
            }
        }
    )

    BackHandler {
        showScannedDocument.value = true
    }
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            CustomTopAppBar(
                bgColor = topBgColor,
                fgColor = Color.White,
                titleBarText = "Scan Document",
                actionItemText = "History",
            )
        },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
                    .consumeWindowInsets(it),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                if (showScannedDocument.value) {
                    PdfPreview(filePath.value)
                }


                if (documentSetting.value) {
                    OutlinedTextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp),
                        label = {
                            Text(text = "Enter Page Count")
                        },
                        value = pageCountText.value,
                        onValueChange = {
                            pageCountText.value = it
                        }
                    )

                    OutlinedTextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp),
                        label = {
                            Text(text = "Document Name")
                        },
                        value = documentName.value,
                        onValueChange = {
                            documentName.value = it
                        }
                    )

                    ButtonView(
                        topBgColor = topBgColor,
                        fgColor = Color.White,
                        onClick = {
                            val options = GmsDocumentScannerOptions.Builder()
                                .setGalleryImportAllowed(true)
                                .setPageLimit(pageCountText.value.toInt())
                                .setResultFormats(RESULT_FORMAT_JPEG, RESULT_FORMAT_PDF)
                                .setScannerMode(SCANNER_MODE_FULL)
                                .build()

                            val scanner = GmsDocumentScanning.getClient(options)

                            scanner.getStartScanIntent(activity)
                                .addOnSuccessListener { intentSender ->
                                    scannerLauncher.launch(
                                        IntentSenderRequest.Builder(intentSender).build()
                                    )
                                }
                                .addOnFailureListener {
                                }

                        },
                        text = "Scan Document"
                    )
                }

            }
        },
        bottomBar = {
            //todo
        }

    )
}