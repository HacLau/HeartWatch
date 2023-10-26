package com.shunlin.heartwatch.view

import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.shunlin.heartwatch.R
import com.shunlin.heartwatch.base.BaseActivity
import com.shunlin.heartwatch.databinding.ActivityContentBinding
import com.shunlin.heartwatch.model.NewsEntity
import com.shunlin.heartwatch.model.PageType
import com.shunlin.heartwatch.helper.logE
import com.shunlin.heartwatch.vm.HeartViewModel

class ContentActivity : BaseActivity<ActivityContentBinding, HeartViewModel>() {
    override fun setBinding(): ActivityContentBinding {
        return ActivityContentBinding.inflate(layoutInflater)
    }

    override fun setViewModel(): HeartViewModel {
        return ViewModelProvider(this)[HeartViewModel::class.java]
    }

    override fun initData() {
        binding.contentTitle.back.setOnClickListener {
            finish()
        }
        when (intent.getStringExtra(PageType.pageType)) {
            PageType.Content.news -> {
                binding.webView.visibility = View.GONE
                binding.contentNews.visibility = View.VISIBLE
                val newsEntity = intent.getParcelableExtra<NewsEntity>(PageType.Content.news)
                setNewsEntity(newsEntity)
            }

            PageType.Content.url -> {
                binding.webView.visibility = View.VISIBLE
                binding.contentNews.visibility = View.GONE
                intent.getStringExtra(PageType.Content.title)?.let {
                    binding.contentTitle.title.text = it
                }
                intent.getStringExtra(PageType.Content.url)?.let {
                    binding.webView.loadUrl(it)
                }
            }
        }
    }

    private fun setNewsEntity(newsEntity: NewsEntity?) {
        newsEntity?.let {
            binding.contentTitle.title.text = getString(R.string.title_news)
            binding.contentNewsTitle.text = it.title
            binding.content.text = it.content
            binding.from.text = it.from
            binding.itemImage.setImageResource(it.image)
            it.from.logE()
        }
    }

}