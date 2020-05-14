package com.calculator.scientific.calculatrice.extension

import android.Manifest
import android.app.Activity
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Context.VIBRATOR_SERVICE
import android.os.Handler
import android.os.Looper
import android.os.Vibrator
import android.util.Log
import android.util.TypedValue
import android.widget.Toast
import androidx.annotation.AttrRes
import androidx.annotation.RequiresPermission
import com.example.farmapplication.BuildConfig
import com.example.farmapplication.helper.BaseConfig
import com.example.farmapplication.helper.PREFS_KEY


fun Context.getSharedPrefs() = getSharedPreferences(PREFS_KEY, Context.MODE_PRIVATE)


fun Context.toast(id: Int, length: Int = Toast.LENGTH_SHORT) {
    toast(getString(id), length)
}

fun Context.toast(msg: String, length: Int = Toast.LENGTH_SHORT) {
    try {
        if (isOnMainThread()) {
            doToast(this, msg, length)
        } else {
            Handler(Looper.getMainLooper()).post {
                doToast(this, msg, length)
            }
        }
    } catch (e: Exception) {
    }
}

fun isOnMainThread() : Boolean = Looper.myLooper() == Looper.getMainLooper()

private fun doToast(context: Context, message: String, length: Int) {
    if (context is Activity) {
        if (!context.isFinishing && !context.isDestroyed) {
            Toast.makeText(context, message, length).show()
        }
    } else {
        Toast.makeText(context, message, length).show()
    }
}


val Context.baseConfig: BaseConfig get() = BaseConfig.newInstance(this)

@RequiresPermission(Manifest.permission.VIBRATE)
fun Context.vibrate(milliseconds: Long) =
    (getSystemService(VIBRATOR_SERVICE) as? Vibrator)?.vibrate(milliseconds)

fun Context.getColorFromAttr(
    @AttrRes attrColor: Int,
    typedValue: TypedValue = TypedValue(),
    resolveRefs: Boolean = true
): Int {
    theme.resolveAttribute(attrColor, typedValue, resolveRefs)
    return typedValue.data
}


internal fun Context.logger(message: Int) {
    if (BuildConfig.DEBUG){
        Log.d(this.javaClass.simpleName, message.toString())
    }
}

fun Context.dpFloat(dp: Number): Float {
    return dp.toFloat() * resources.displayMetrics.density
}

fun Context.dpInt(dp: Number) : Int{
    return dp.toInt() * resources.displayMetrics.density.toInt()
}
