package com.example.adtcodingassesment.view.activities

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.adtcodingassesment.App
import com.example.adtcodingassesment.R
import com.example.adtcodingassesment.model.data.Article
import com.example.adtcodingassesment.model.network.ConnectivityInspector
import com.example.adtcodingassesment.model.network.MainRepository
import com.example.adtcodingassesment.view.adapters.NewsAdapter
import com.example.adtcodingassesment.viewmodel.MainViewModel


class MainActivity : AppCompatActivity(),ConnectivityInspector.ConnectivityReceiverListener {
    private var articleList: MutableList<Article?>? = mutableListOf()
    private lateinit var adapterArticle: NewsAdapter

    private val ViewModel by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        MainRepository.context = applicationContext
        ViewModel.makeRetrofitCall(isConnected())
        ViewModel.liveArticleList.observe(this, Observer {
            setUpRecyclerView(it)

        })
        ViewModel.liveError.observe(this, Observer {
            Toast.makeText(this, "ERROR: $it", Toast.LENGTH_LONG).show()
            Log.d("viewModel",it.toString())
        })
    }
    override fun onResume() {
        super.onResume()
        App.instance?.setConnectivityListener(this);
    }

    /**
     * describe getting the state of the device connectivity
     */
    private fun isConnected() : Boolean{
        return ConnectivityInspector.isConnected
    }
    /**
     * describe setting the recycler view with the article lists
     * @params it = list of article
     */
    private fun setUpRecyclerView(it: List<Article?>?) {
        it?.let { articleResponse ->
            articleList?.apply {
                articleList = it.toMutableList()
                adapterArticle = NewsAdapter(articleList,this@MainActivity)
            }
            findViewById<RecyclerView>(R.id.recycler_view).run {
                val decorator = DividerItemDecoration(applicationContext, LinearLayoutManager.VERTICAL)
                decorator.setDrawable(ContextCompat.getDrawable(applicationContext, R.drawable.divider)!!)
                this.addItemDecoration(decorator)
                layoutManager = LinearLayoutManager(this@MainActivity, RecyclerView.VERTICAL, false)
                adapter = adapterArticle
            }
            adapterArticle.notifyDataSetChanged()
        }
    }

    override fun onNetworkConnectionChanged(isConnected: Boolean) {
        ViewModel.makeRetrofitCall(isConnected)
    }
}
