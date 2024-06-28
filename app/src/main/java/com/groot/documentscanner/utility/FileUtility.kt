package com.groot.documentscanner.utility

import android.app.Activity
import android.content.Context
import android.net.Uri
import android.os.Environment
import android.provider.OpenableColumns
import java.io.File
import java.io.FileOutputStream

object FileUtility {


     fun saveFileFromUri(context: Activity, uri: Uri?) {
        if (uri == null) {
            return
        }
        val inputStream = context.contentResolver.openInputStream(uri)
        val fileName = getFileName(context, uri)
        val file = File(Environment.getExternalStorageDirectory().absolutePath + "/" + fileName)
        val outputStream = FileOutputStream(file)
        inputStream?.copyTo(outputStream)
        inputStream?.close()
        outputStream.flush()
        outputStream.close()
    }

    private fun getFileName(context: Context, uri: Uri): String {
        var fileName = ""
        if (uri.scheme == "content") {
            val cursor = context.contentResolver.query(uri, null, null, null, null)
            cursor?.use {
                if (it.moveToFirst()) {
                    fileName = it.getColumnIndex(OpenableColumns.DISPLAY_NAME).toString()
                }
            }
        } else if (uri.scheme == "file") {
            fileName = uri.path?.substring(uri.path?.lastIndexOf("/")!! + 1) ?: ""
        }
        return fileName
    }
}