package com.shunlin.heartwatch.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.shunlin.heartwatch.base.BaseFragment
import com.shunlin.heartwatch.databinding.FragmentNewsBinding
import com.shunlin.heartwatch.model.PageType
import com.shunlin.heartwatch.model.newsList
import com.shunlin.heartwatch.ui.NewsAdapter
import com.shunlin.heartwatch.ui.RecyclerItemDecoration
import com.shunlin.heartwatch.vm.FragmentViewModel

class NewsFragment : BaseFragment<FragmentNewsBinding, FragmentViewModel>() {
    override fun initViewBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentNewsBinding {
        return FragmentNewsBinding.inflate(inflater, container, false)
    }

    override fun initViewModel(): FragmentViewModel {
        return ViewModelProvider(this)[FragmentViewModel::class.java]
    }

    override fun initData() {

        binding.newsRv.addItemDecoration(RecyclerItemDecoration(6))
        binding.newsRv.layoutManager = LinearLayoutManager(this.requireActivity(), LinearLayoutManager.VERTICAL, false)
        binding.newsRv.adapter = NewsAdapter(this.requireActivity(), newsList) {
            startContentActivity(PageType.Content.news, news = it)
        }
    }
}