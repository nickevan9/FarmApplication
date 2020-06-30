package com.example.farmapplication.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.farmapplication.data.model.FarmEntity
import com.example.farmapplication.data.model.ListFarmStyle
import com.example.farmapplication.ui.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.jsoup.Jsoup

class HomeViewModel : BaseViewModel() {
    private val listItemSouth: MutableLiveData<ListFarmStyle> = MutableLiveData()
    val mListItemSouth: LiveData<ListFarmStyle> = listItemSouth

    private val listItemCentral: MutableLiveData<ListFarmStyle> = MutableLiveData()
    val mListItemCentral: LiveData<ListFarmStyle> = listItemCentral


    private val listItemNorth: MutableLiveData<ListFarmStyle> = MutableLiveData()
    val mListItemNorth: LiveData<ListFarmStyle> = listItemNorth


    fun getListItem(params: String, region: Int) {
        showLoading.value = true

        viewModelScope.launch(exceptionHandler) {
            val listItem = mutableListOf<FarmEntity>()
            withContext(Dispatchers.IO) {

                val doc =
                    Jsoup.connect("https://hoanganhnongsan.com/danh-muc-san-pham/$params/")
                        .get()
                val elements = doc.select(".woocommerce ul.products li.product")
                for (element in elements) {

                    val image = element.getElementsByTag("img").attr("src")

                    val text = element.getElementsByClass("desc").select("h4").text()

                    val price = element.getElementsByClass("desc").select("span")[1].text()

                    val link = element.select("a").attr("href")

                    listItem.add(FarmEntity(0, text, image, price, link))
                }

                when (region) {
                    0 -> {
                        listItemSouth.postValue(ListFarmStyle(0, listItem, region))
                    }

                    1 -> {
                        listItemCentral.postValue(ListFarmStyle(0, listItem, region))
                    }

                    2 -> {
                        listItemNorth.postValue(ListFarmStyle(0, listItem, region))
                    }
                }

                showLoading.value = false


            }
        }
    }
}
