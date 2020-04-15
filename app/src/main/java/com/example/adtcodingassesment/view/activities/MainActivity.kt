package com.example.adtcodingassesment.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.adtcodingassesment.R
import com.example.adtcodingassesment.model.data.Article
import com.example.adtcodingassesment.view.adapters.NewsAdapter
import com.example.adtcodingassesment.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {
    private var articleList: MutableList<Article?>? = mutableListOf()
    private lateinit var adapterArticle: NewsAdapter

    private val MainViewModel by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        MainViewModel.liveArticleList.observe(this, Observer {
            setUpRecyclerView(it)
        })
        MainViewModel.liveError.observe(this, Observer {
            Toast.makeText(this, "ERROR $it", Toast.LENGTH_LONG).show()
            Log.d("ViewModel","ERROR $it")
        })
    }

    private fun setUpRecyclerView(it: List<Article?>?) {
        it?.let { articleResponse ->
            articleList?.apply {
                articleList = it.toMutableList()
                adapterArticle = NewsAdapter(articleList,this@MainActivity)
            }
            findViewById<RecyclerView>(R.id.recycler_view).run {
                layoutManager = LinearLayoutManager(this@MainActivity, RecyclerView.VERTICAL, false)
                adapter = adapterArticle
            }
            adapterArticle.notifyDataSetChanged()
        }
    }
}
