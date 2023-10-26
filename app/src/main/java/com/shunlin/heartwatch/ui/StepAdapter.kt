package com.shunlin.heartwatch.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import com.shunlin.heartwatch.app
import com.shunlin.heartwatch.databinding.LayoutStepPagerBinding
import com.shunlin.heartwatch.model.stepList

class StepAdapter(val context: Context):PagerAdapter() {
    lateinit var binding:LayoutStepPagerBinding
    override fun getCount(): Int {
        return stepList.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        binding = LayoutStepPagerBinding.inflate(LayoutInflater.from(app),container,false)
        binding.stepTitle.text = context.getString(stepList[position].title)
        binding.stepContent.text = context.getString(stepList[position].content)
        binding.stepImage.setBackgroundResource(stepList[position].image)
        container.addView(binding.root)
        return binding.root
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }
}