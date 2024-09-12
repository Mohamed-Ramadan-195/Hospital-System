package com.example.hospitalsystem

import android.app.Application
import com.example.hospitalsystem.utils.SharedPreferenceDatabase
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class HospitalApp: Application() {
    override fun onCreate() {
        super.onCreate()
        SharedPreferenceDatabase.initSharedPrefDatabase(this)
    }
}