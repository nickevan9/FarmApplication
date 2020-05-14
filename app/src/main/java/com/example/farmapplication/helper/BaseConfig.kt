package com.example.farmapplication.helper

import android.content.Context
import com.calculator.scientific.calculatrice.extension.getSharedPrefs


open class BaseConfig(val context: Context) {
    protected val prefs = context.getSharedPrefs()

    companion object {
        fun newInstance(context: Context) = BaseConfig(context)
    }


}
