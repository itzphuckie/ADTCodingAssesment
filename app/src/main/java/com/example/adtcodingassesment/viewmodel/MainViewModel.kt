package com.example.adtcodingassesment.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.adtcodingassesment.model.data.Article
import com.example.adtcodingassesment.model.network.MainRepository
import kotlinx.coroutines.*


class MainViewModel:ViewModel(){

    private val mainRepository = MainRepository()
    private val muArticleList = MutableLiveData<List<Article?>?>()
    private val muError = MutableLiveData<String>()

    private lateinit var job:Job

    val liveArticleList :LiveData<List<Article?>?> = muArticleList
    val liveError :LiveData<String> = muError

    /**
     * describe making the API call and provide article data to the screen
     * @params N/A
     */
     fun makeRetrofitCall(isConnected: Boolean) {
        job = CoroutineScope(Dispatchers.IO).launch {
            try{
                withContext(Dispatchers.Main){
                    muArticleList.value = mainRepository.getCachedReponse(isConnected)
                    Log.d("PostData",muArticleList.value.toString())
                }
            }
            catch(exception: Exception){
                muError.postValue(exception.toString())
            }
        }
    }

    override fun onCleared() {
        job.cancel()
        super.onCleared()
    }
}