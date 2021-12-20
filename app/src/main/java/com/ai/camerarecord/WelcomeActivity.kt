package com.ai.camerarecord

import android.Manifest
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import com.ai.camerarecord.databinding.ActivityWelcomeBinding
import com.ai.customcamera.base.BaseActivity
import com.ai.customcamera.permission.PermissionUtil

class WelcomeActivity : BaseActivity<ActivityWelcomeBinding>() {

    override fun getLayoutId() = R.layout.activity_welcome

    @RequiresApi(Build.VERSION_CODES.M)
    override fun initView(savedInstanceState: Bundle?) {
        super.initView(savedInstanceState)
        if (PermissionUtil.hasPermissions(
                this, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA,
                Manifest.permission.READ_PHONE_STATE
            )
        ) {
            this.goIntent(MainActivity())
        } else {
            PermissionUtil.init(this)
                .permissions(
                    Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA,
                    Manifest.permission.READ_PHONE_STATE
                ).result { isAllGranted, grantedList, deniedList ->
                    if (isAllGranted) {
                        this.goIntent(MainActivity())
                    }
                }
        }

    }
}