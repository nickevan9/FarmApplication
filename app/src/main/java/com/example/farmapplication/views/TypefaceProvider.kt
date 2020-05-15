package com.example.farmapplication.views

import android.content.Context
import android.graphics.Typeface
import java.util.*

object TypefaceProvider {
    private const val TYPEFACE_FOLDER = "fonts"
    private const val TYPEFACE_EXTENSION = ".ttf"
    private val sTypeFaces =
        Hashtable<String, Typeface?>(4)

    fun getTypeFace(context: Context, fileName: String): Typeface? {
        var tempTypeface = sTypeFaces[fileName]
        if (tempTypeface == null) {
            val fontPath =
                StringBuilder(TYPEFACE_FOLDER).append('/')
                    .append(fileName).append(TYPEFACE_EXTENSION).toString()
            tempTypeface = Typeface.createFromAsset(context.assets, fontPath)
            sTypeFaces[fileName] = tempTypeface
        }
        return tempTypeface
    }
}