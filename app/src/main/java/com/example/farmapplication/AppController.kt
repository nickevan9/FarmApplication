package com.example.farmapplication

import android.app.Application
import com.example.farmapplication.helper.ObjectBox

class AppController : Application(){
    override fun onCreate() {
        super.onCreate()
        ObjectBox.init(this)

    }
}