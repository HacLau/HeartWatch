package com.shunlin.heartwatch.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.shunlin.heartwatch.base.BaseFragment
import com.shunlin.heartwatch.databinding.FragmentHomeBinding
import com.shunlin.heartwatch.model.PageType
import com.shunlin.heartwatch.model.newsList
import com.shunlin.heartwatch.ui.NewsAdapter
import com.shunlin.heartwatch.ui.RecyclerItemDecoration
import com.shunlin.heartwatch.helper.DateKt
import com.shunlin.heartwatch.helper.toTime
import com.shunlin.heartwatch.vm.FragmentViewModel

class HomeFragment : BaseFragment<FragmentHomeBinding,FragmentViewModel>() {
    override fun initViewBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(inflater, container, false)
    }

    override fun initViewModel(): FragmentViewModel {
        return ViewModelProvider(this)[FragmentViewModel::class.java]
    }

    override fun initData() {
        binding.homeTopTitleBg.setOnClickListener {
            startRecordNewActivity(PageType.Record.add)
        }
        binding.homeTopNextImage.setOnClickListener {
            startRecordNewActivity(PageType.Record.add)
        }
        binding.homeRv.addItemDecoration(RecyclerItemDecoration(6))
        binding.homeRv.layoutManager = LinearLayoutManager(this.requireActivity(), LinearLayoutManager.VERTICAL, false)
        binding.homeRv.adapter = NewsAdapter(this.requireActivity(),newsList.subList(0,5)){
            startContentActivity(PageType.Content.news, news = it)
        }
        binding.homeTopDay.text = DateKt.getDay().toTime()
        binding.homeTopMonth.text = "/${DateKt.getMonth().plus(1).toTime()}"
    }
}