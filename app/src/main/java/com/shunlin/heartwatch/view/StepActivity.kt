package com.shunlin.heartwatch.view

import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.shunlin.heartwatch.base.BaseActivity
import com.shunlin.heartwatch.databinding.ActivityStepBinding
import com.shunlin.heartwatch.helper.SharedHelper
import com.shunlin.heartwatch.helper.dp2px
import com.shunlin.heartwatch.helper.logE
import com.shunlin.heartwatch.ui.StepAdapter
import com.shunlin.heartwatch.vm.HeartViewModel

class StepActivity : BaseActivity<ActivityStepBinding, HeartViewModel>() {
    override fun setBinding(): ActivityStepBinding {
        return ActivityStepBinding.inflate(layoutInflater)
    }

    override fun setViewModel(): HeartViewModel {
        return ViewModelProvider(this)[HeartViewModel::class.java]
    }

    override fun initData() {
        binding.stepViewPager.adapter = StepAdapter(context = this)
        binding.stepViewPager.addOnPageChangeListener(object : OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}

            override fun onPageSelected(position: Int) {
                when (position) {
                    0, 1 -> {
                        binding.stepNextImage.visibility = View.VISIBLE
                        binding.stepNextButton.visibility = View.GONE
                    }

                    2 -> {
                        binding.stepNextImage.visibility = View.GONE
                        binding.stepNextButton.visibility = View.VISIBLE
                    }
                }
                "translationX = ${(resources.displayMetrics.widthPixels * position / 30.0f).dp2px(this@StepActivity)}".logE()
                binding.ivProgress.translationX = (resources.displayMetrics.widthPixels * position  / 30.0f).dp2px(this@StepActivity)
            }

            override fun onPageScrollStateChanged(state: Int) {}

        })
        binding.stepSkip.setOnClickListener {
            startMainActivity()
            SharedHelper.launchedStep = true
        }
        binding.stepNextImage.setOnClickListener {
            binding.stepViewPager.currentItem++
        }
        binding.stepNextButton.setOnClickListener {
            startMainActivity()
            SharedHelper.launchedStep = true
        }
    }

}