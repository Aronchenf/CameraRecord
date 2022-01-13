package com.ai.camerarecord

import android.Manifest
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import com.ai.camerarecord.databinding.ActivityWelcomeBinding
import com.ai.customcamera.base.BaseActivity
import com.ai.customcamera.permission.PermissionUtil

class WelcomeActivity : BaseActivity<ActivityWelcomeBinding>() {

    override fun getLayoutId() = R.layout.activity_welcome

    @RequiresApi(Build.VERSION_CODES.M)
    override fun initView(savedInstanceState: Bundle?) {
        super.initView(savedInstanceState)
        PermissionUtil.init(this)
            .permissions(
                Manifest.permission.CAMERA,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.RECORD_AUDIO,
                Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.MODIFY_AUDIO_SETTINGS,
                Manifest.permission.RESTART_PACKAGES,
                Manifest.permission.ACCESS_NETWORK_STATE,
                Manifest.permission.CHANGE_WIFI_STATE,
                Manifest.permission.ACCESS_WIFI_STATE,
                Manifest.permission.VIBRATE,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_LOCATION_EXTRA_COMMANDS
            )
            .result { isAllGranted, grantedList, deniedList ->
                Log.e("TAG", "initView: $isAllGranted", )
                if (isAllGranted) {
                    Log.e("TAG", "initView: $isAllGranted", )
                    this.goIntentWithSingleTask(MainActivity())
                    this.finish()
                } else {
                    Log.e("TAG", "initView: $isAllGranted", )
                    finish()
                }
            }
//
    }
}