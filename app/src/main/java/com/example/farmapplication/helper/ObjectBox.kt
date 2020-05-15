package com.example.farmapplication.helper

import android.content.Context
import android.util.Log
import com.example.farmapplication.data.model.MyObjectBox
import io.objectbox.BoxStore

object ObjectBox {
    lateinit var boxStore: BoxStore
        private set

    fun init(context: Context) {
        boxStore = MyObjectBox.builder()
            .androidContext(context.applicationContext)
            .build()
//        if (BuildConfig.DEBUG) {
//            val started =
//                AndroidObjectBrowser(boxStore).start(context.applicationContext)
//            Log.i("ObjectBrowser", "Started: $started")
//        }
    }
}