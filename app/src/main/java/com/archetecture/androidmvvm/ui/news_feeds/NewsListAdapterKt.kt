package com.archetecture.androidmvvm.ui.news_feeds

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView

import androidx.recyclerview.widget.RecyclerView

import com.archetecture.androidmvvm.R
import com.archetecture.androidmvvm.data.models.NewsEntityKt
import com.archetecture.androidmvvm.utils.ImageUtilsKt

import java.util.ArrayList


class NewsListAdapterKt
(val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val dataSet: MutableList<NewsEntityKt>

    private val VIEW_TYPE_ITEM = 0
    private val VIEW_TYPE_LOADING = 1
    private var isLoading: Boolean = false

    val items: List<NewsEntityKt>
        get() = dataSet

    /*Listeners*/
    internal var clickListener: ClickListeners? = null

    init {
        this.dataSet = ArrayList()
    }

    fun setLoaded() {
        isLoading = false
    }

    fun addItems(items: List<NewsEntityKt>) {
        dataSet.addAll(items)
        notifyDataSetChanged()
    }

    fun clearItems() {
        dataSet.clear()
        notifyDataSetChanged()
    }

    fun removeLoadingItem() {
        dataSet.removeAt(dataSet.size - 1)
        notifyItemRemoved(dataSet.size)
    }

    fun addLoadingItem() {
        //dataSet.add(null)
        notifyItemInserted(dataSet.size - 1)
    }

    interface ClickListeners {
        fun onRowClick(position: Int, v: View)

    }


    override fun getItemViewType(position: Int): Int {
        return if (dataSet[position] == null) VIEW_TYPE_LOADING else VIEW_TYPE_ITEM
    }

    lateinit var view: View

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        if (viewType == VIEW_TYPE_ITEM) {
            view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_news, parent, false)
            return ItemTypeViewHolder(view)
        }
            view = LayoutInflater.from(parent.context).inflate(R.layout.layout_loading_item, parent, false)
            return LoadingViewHolder(view)



    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, listPosition: Int) {
        val newsEntity = dataSet[listPosition]
        if (holder is ItemTypeViewHolder) {
            try {

                holder.newsTitle.text = newsEntity.title
                if (newsEntity.mediaEntityList != null && !newsEntity.mediaEntityList!!.isEmpty()) {
                    var thumbnailURL = newsEntity.mediaEntityList!![0].url
                    ImageUtilsKt().loadImage(context, thumbnailURL, holder.imageView)
                }
            } catch (e: Exception) {

            }

        } else if (holder is LoadingViewHolder) {
            holder.progressBar.isIndeterminate = true
        }
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    fun setClickListener(clickListener: ClickListeners) {
        this.clickListener = clickListener
    }



    internal inner class ItemTypeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        var newsTitle: TextView
        var imageView: ImageView
        var mParentFrame: View

        init {
            this.mParentFrame = itemView.findViewById(R.id.rl_header)
            this.newsTitle = itemView.findViewById<View>(R.id.news_title) as TextView
            this.imageView = itemView.findViewById<View>(R.id.news_item_image) as ImageView

            this.mParentFrame.setOnClickListener(this)
        }

        override fun onClick(v: View) {
            when (v.id) {
                R.id.rl_header -> if (clickListener != null)
                    clickListener!!.onRowClick(adapterPosition, v)
            }
        }
    }

    internal inner class LoadingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var progressBar: ProgressBar

        init {
            progressBar = itemView.findViewById<View>(R.id.progressBar) as ProgressBar
        }
    }
}
