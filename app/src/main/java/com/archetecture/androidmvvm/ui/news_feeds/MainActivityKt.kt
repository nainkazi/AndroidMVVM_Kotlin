package com.archetecture.androidmvvm.ui.news_feeds

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.archetecture.androidmvvm.R
import com.archetecture.androidmvvm.data.models.NewsEntityKt
import com.archetecture.androidmvvm.ui.news_details.NewsDetailsKt
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Created by Nain Kazi on 4/25/2019.
 */
class MainActivityKt : AppCompatActivity() {


    private var newsViewModel: NewsViewModelKt? = null
    private var newsEntitiesList: List<NewsEntityKt>? = null
    private var newsListAdapter: NewsListAdapterKt? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUI()
        setObservable()
        setData()
    }

    private fun setUI() {
        newsViewModel = NewsViewModelKt(this)
        newsViewModel!!.init()
        newsListAdapter = NewsListAdapterKt(this)
        news_list!!.setLayoutManager(LinearLayoutManager(this))
        news_list!!.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        news_list!!.setItemAnimator(DefaultItemAnimator())
        news_list!!.setAdapter(newsListAdapter)

        swipeRefreshLayout!!.setOnRefreshListener(SwipeRefreshLayout.OnRefreshListener {
            setData()
            swipeRefreshLayout!!.setRefreshing(false)
        })

        val impl = object : NewsListAdapterKt.ClickListeners {
            override fun onRowClick(position: Int, v: View) {
                val newsEntity = newsEntitiesList!!.get(position)
                    var intent = Intent(this@MainActivityKt, NewsDetailsKt::class.java)
                    intent.putExtra("news", newsEntity)
                    startActivity(intent)
            }
        }
        newsListAdapter!!.setClickListener(impl)
    }


    private fun setData() {
        swipeRefreshLayout!!.setRefreshing(true)
        newsViewModel!!.getNewsList()
    }

    private fun setObservable() {
        newsViewModel!!.liveData.observe(this, Observer { newsEntities ->

            if(newsEntities!= null) {
                swipeRefreshLayout!!.setRefreshing(false)
                newsListAdapter!!.clearItems()
                newsListAdapter!!.addItems(newsEntities)
                newsListAdapter!!.notifyDataSetChanged()
                newsEntitiesList = newsEntities
            }else{
                swipeRefreshLayout!!.isRefreshing =false
            }
        })

    }
}