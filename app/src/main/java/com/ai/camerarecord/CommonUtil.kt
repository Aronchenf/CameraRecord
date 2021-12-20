package com.ai.camerarecord

import android.content.Intent
import androidx.fragment.app.FragmentActivity

 fun FragmentActivity.goIntent(activity: FragmentActivity) {
    val intent = Intent(this, activity::class.java)
    this.startActivity(intent)
}