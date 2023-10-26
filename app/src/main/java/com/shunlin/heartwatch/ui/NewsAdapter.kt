package com.shunlin.heartwatch.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.shunlin.heartwatch.databinding.LayoutNewsItemBinding
import com.shunlin.heartwatch.model.NewsEntity

class NewsAdapter(
    private val context: Context,
    private val list: MutableList<NewsEntity>,
    private val onItemClick:(NewsEntity)->Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return NewsViewHolder(LayoutNewsItemBinding.inflate(LayoutInflater.from(context)))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is NewsViewHolder){
            list[position]?.let {
                holder.binding.itemTitle.text = it.title
                holder.binding.itemContent.text = it.content
                holder.binding.itemImage.setImageResource(it.image)
                holder.binding.itemNews.setOnClickListener {_ ->
                    onItemClick.invoke(it)
                }
            }

        }
    }

    inner class NewsViewHolder(itemBinding:LayoutNewsItemBinding):RecyclerView.ViewHolder(itemBinding.root){
        var binding = itemBinding
    }
}