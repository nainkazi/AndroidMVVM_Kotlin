package com.archetecture.androidmvvm.ui.news_details

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.archetecture.androidmvvm.R
import com.archetecture.androidmvvm.data.models.NewsEntityKt
import com.archetecture.androidmvvm.utils.ImageUtilsKt
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_details.*

class NewsDetailsKt : AppCompatActivity() {

    var newsEntity: NewsEntityKt? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        val data = intent.extras
        newsEntity = data!!.getParcelable<NewsEntityKt>("news")
        setData()
        setListener()
    }

    private fun setListener() {
        full_story_link.setOnClickListener(View.OnClickListener {
            showWebView()
        })
    }

    private fun setData() {
        tv_title!!.setText(newsEntity!!.title)
        summary_content!!.setText(newsEntity!!.summary)
        if (newsEntity!!.mediaEntityList != null && !newsEntity!!.mediaEntityList!!.isEmpty()) {
            val thumbnailURL = newsEntity!!.mediaEntityList!![0].url
            ImageUtilsKt().loadImage(this, thumbnailURL, news_image)
        }
    }

    private fun showWebView() {
        if (newsEntity != null && !TextUtils.isEmpty(newsEntity!!.articleUrl)) {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(newsEntity!!.articleUrl)
            startActivity(intent)
        } else {
            Snackbar.make(full_story_link!!.rootView, getString(R.string.not_found), Snackbar.LENGTH_LONG).show()
        }
    }
}