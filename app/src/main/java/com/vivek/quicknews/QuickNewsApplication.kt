package com.vivek.quicknews
import androidx.multidex.MultiDexApplication
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class QuickNewsApplication : MultiDexApplication() {
    override fun onCreate() {
        super.onCreate()
    }
}