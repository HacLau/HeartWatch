package com.shunlin.heartwatch.base

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding
import com.shunlin.heartwatch.model.NewsEntity
import com.shunlin.heartwatch.model.PageType
import com.shunlin.heartwatch.model.RecordEntity
import com.shunlin.heartwatch.view.ContentActivity
import com.shunlin.heartwatch.view.RecordMoreActivity
import com.shunlin.heartwatch.view.RecordNewActivity

abstract class BaseFragment<VB : ViewBinding, VM : ViewModel> : Fragment() {
    lateinit var binding: VB
    lateinit var viewModel: VM
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = initViewBinding(inflater, container)
        viewModel = initViewModel()
        initData()
        return binding.root
    }

    abstract fun initViewBinding(inflater: LayoutInflater, container: ViewGroup?): VB

    abstract fun initViewModel(): VM

    abstract fun initData()

    fun startRecordMoreActivity() {
        startActivity(Intent(this.requireActivity(), RecordMoreActivity::class.java))
    }


    fun startRecordNewActivity(pageType: String, record: RecordEntity? = null) {
        startActivity(Intent(requireActivity(), RecordNewActivity::class.java).apply {
            putExtra(PageType.pageType, pageType)
            putExtra(pageType, record)
        })
    }


    fun startContentActivity(pageType: String,news: NewsEntity? = null, url: String = "", title:String = "") {
        startActivity(Intent(requireActivity(), ContentActivity::class.java).apply {
            putExtra(PageType.pageType, pageType)
            putExtra(PageType.Content.title,title)
            when (pageType) {
                PageType.Content.url -> putExtra(pageType, url)
                PageType.Content.news -> putExtra(pageType, news)
            }
        })
    }
}