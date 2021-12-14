package com.ai.camerarecord

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.ai.customcamera.permission.PermissionCallBack
import com.ai.customcamera.permission.PermissionDialogListener
import com.ai.customcamera.permission.PermissionUtil
import kotlin.math.log

class MainActivity : AppCompatActivity() {


    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        PermissionUtil.init(this)
            .permissions(
                Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_PHONE_STATE
            )
            .result { isAllGranted, grantedList, deniedList ->
                Log.e("TAG", "onCreate: ${deniedList.size}")
            }
    }


}