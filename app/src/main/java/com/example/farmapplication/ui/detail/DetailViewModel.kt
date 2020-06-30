package com.example.farmapplication.ui.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.farmapplication.data.model.ListFarmStyle
import com.example.farmapplication.ui.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.jsoup.Jsoup

class DetailViewModel : BaseViewModel() {

    private val descriptionFarm: MutableLiveData<String> = MutableLiveData()
    val mDescriptionFarm: LiveData<String> = descriptionFarm

    fun getDescriptionFarm(url: String) {
        showLoading.value = true

        viewModelScope.launch(exceptionHandler) {
            withContext(Dispatchers.IO) {
                val doc = Jsoup.connect(url).get()
                descriptionFarm.postValue( doc.getElementsByClass("answer")[0].text())
                showLoading.value = false
            }
        }
    }

}