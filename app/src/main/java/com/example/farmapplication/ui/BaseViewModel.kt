package com.example.farmapplication.ui

import android.os.Handler
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.farmapplication.utils.SingleLiveEvent
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlin.coroutines.CoroutineContext


abstract class BaseViewModel : ViewModel() {
    val showLoading = SingleLiveEvent<Boolean>()
    val showError = SingleLiveEvent<String>()


    val exceptionHandler: CoroutineContext =
        CoroutineExceptionHandler { _, throwable ->
            throwable.printStackTrace()
            showLoading.value = false
            showError.value = throwable.message.toString()
        }

    val mHandler = Handler()

    override fun onCleared() {
        super.onCleared()
        mHandler.removeCallbacksAndMessages(null)
    }
}