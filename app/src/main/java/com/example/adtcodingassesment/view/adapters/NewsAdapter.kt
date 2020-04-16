package com.example.adtcodingassesment.view.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.adtcodingassesment.R
import com.example.adtcodingassesment.model.data.Article

class NewsAdapter(var muArticleList:MutableList<Article?>?,var mContext:Context):RecyclerView.Adapter<NewsAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.item_article_data,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return muArticleList?.size ?:0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var mArticle = muArticleList?.get(position)
        holder.onBind(mArticle)
    }

    inner class ViewHolder(view: View):RecyclerView.ViewHolder(view){
        val articleHealine = view.findViewById<TextView>(R.id.tv_headline)
        val articleImage = view.findViewById<ImageView>(R.id.img_healine)
        fun onBind(article: Article?){
            articleHealine.text = article?.title
            Glide.with(mContext).load(article?.urlToImage)
                .placeholder(R.drawable.no_image_placeholder)
                .into(articleImage)
        }
    }
}