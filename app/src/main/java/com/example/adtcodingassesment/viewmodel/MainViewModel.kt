package com.example.adtcodingassesment.viewmodel
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

    init {
        makeRetrofitCall()
    }

    private fun makeRetrofitCall() {
        job = CoroutineScope(Dispatchers.IO).launch {
            try{
                val newsReponse = mainRepository.getNewsResponse()
                withContext(Dispatchers.Main){
                    when(newsReponse.isSuccessful){
                        true ->{
                            muArticleList.value = newsReponse.body()?.articles
                        }
                        false ->{
                            muError.value = newsReponse.errorBody().toString()
                        }
                    }
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