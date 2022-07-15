package com.learn.tiptop

import android.app.Application
import com.google.android.material.color.DynamicColors

class MyColor : Application() {
    override fun onCreate() {
        super.onCreate()
        DynamicColors.applyToActivitiesIfAvailable(this)
    }
}