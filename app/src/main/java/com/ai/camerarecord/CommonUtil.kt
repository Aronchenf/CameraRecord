package com.ai.camerarecord

import android.content.Intent
import androidx.fragment.app.FragmentActivity

fun FragmentActivity.goIntent(activity: FragmentActivity) {
    val intent = Intent(this, activity::class.java)
    this.startActivity(intent)
}

fun FragmentActivity.goIntentWithSingleTask(activity: FragmentActivity) {
    val intent = Intent(this, activity::class.java)
    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    this.startActivity(intent)
}