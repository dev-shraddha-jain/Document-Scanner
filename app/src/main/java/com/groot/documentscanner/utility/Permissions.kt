package com.groot.documentscanner.utility

import android.Manifest
import android.os.Build


/**
 * returns media permission according to android build version
 **/
val MediaPermission = if (Build.VERSION.SDK_INT >= 33) {
    Manifest.permission.READ_MEDIA_IMAGES
} else {
    Manifest.permission.WRITE_EXTERNAL_STORAGE
}