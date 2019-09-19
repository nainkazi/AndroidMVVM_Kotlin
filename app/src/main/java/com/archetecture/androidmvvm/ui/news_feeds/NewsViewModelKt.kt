package com.archetecture.androidmvvm.ui.news_feeds

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.archetecture.androidmvvm.data.models.NewsEntityKt


class NewsViewModelKt(context1: Context) : ViewModel() {


    var context: Context? = context1
    var newsRepository: NewsRepositoryKt? = null
    var liveData: LiveData<List<NewsEntityKt>> = MutableLiveData<List<NewsEntityKt>>()

    fun init(){
        this.newsRepository = NewsRepositoryKt()
        this.liveData = newsRepository!!.listLiveData
    }


    fun getNewsList() {
        newsRepository!!.getNewsFeed()
    }

    fun getNewsListData(): LiveData<List<NewsEntityKt>> {
        context = context
        newsRepository = NewsRepositoryKt()
        return newsRepository!!.listLiveData
    }
}