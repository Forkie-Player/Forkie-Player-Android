package com.example.forkieplayer.sharedpreference

import android.app.Application

class ForkieApplication : Application(){
    companion object{
        lateinit var prefs: ForkiePrefs
    }

    override fun onCreate() {
        prefs = ForkiePrefs(applicationContext)
        super.onCreate()
    }
}