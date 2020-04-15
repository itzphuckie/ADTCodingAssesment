package com.example.adtcodingassesment.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.adtcodingassesment.model.data.Article
import com.example.adtcodingassesment.model.network.MainRepository
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main

class MainViewModel:ViewModel() {
    private val mainRepository = MainRepository()
    private val muArticleList = MutableLiveData<List<Article?>?>()
    private val muError = MutableLiveData<String>()

    private lateinit var job:Job

    val articleList :LiveData<List<Article?>?> = muArticleList
    val error :LiveData<String> = muError

    init {
        makeRetrofitCall()
    }

    private fun makeRetrofitCall() {
        job = CoroutineScope(IO).launch {
            val newsReponse = mainRepository.getNewsResponse()
            withContext(Main){
                when(newsReponse.isSuccessful){
                    true ->{
                        muArticleList.value = newsReponse.body()?.articles
                    }
                    false ->{
                        muError.postValue(newsReponse.errorBody().toString())
                    }
                }
            }

        }
    }



}