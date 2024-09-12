package com.example.hospitalsystem.utils

import android.app.Activity
import android.app.Dialog
import com.example.hospitalsystem.R

object ProgressLoading {

    private var progress: Dialog? = null
    private var hasActivity: Boolean = false

    private fun init(activity: Activity) {
        progress = Dialog(activity)
        progress?.setContentView(R.layout.progress)
    }

    fun show(activity: Activity) {
        if (!hasActivity) {
            hasActivity = true
            init(activity)
        }

        if (!(activity).isFinishing) {
            try {
                progress?.show()
            } catch (e: Exception) {
                e.localizedMessage
            }
        }
    }

    fun dismiss() {
        progress?.dismiss()
        hasActivity = false
    }
}