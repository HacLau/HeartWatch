package com.shunlin.heartwatch.view

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.shunlin.heartwatch.base.BaseActivity
import com.shunlin.heartwatch.databinding.ActivityMainBinding
import com.shunlin.heartwatch.ui.FragAdapter
import com.shunlin.heartwatch.vm.HeartViewModel

class MainActivity : BaseActivity<ActivityMainBinding, HeartViewModel>() {

    override fun setBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    override fun setViewModel(): HeartViewModel {
        return ViewModelProvider(this)[HeartViewModel::class.java]
    }

    override fun initData() {
        binding.navGroup.setOnCheckedChangeListener { _, id ->
            when(id){
                binding.navHome.id ->{
                    binding.mainViewPager.currentItem = 0
                }
                binding.navRecord.id ->{
                    binding.mainViewPager.currentItem = 1
                }
                binding.navNews.id ->{
                    binding.mainViewPager.currentItem = 2
                }
                binding.navSetting.id ->{
                    binding.mainViewPager.currentItem = 3
                }
            }
        }

        binding.mainViewPager.addOnPageChangeListener(object :OnPageChangeListener{
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }

            override fun onPageSelected(position: Int) {
                when(position){
                    0->binding.navHome.isChecked = true
                    1->binding.navRecord.isChecked = true
                    2->binding.navNews.isChecked = true
                    3->binding.navSetting.isChecked = true
                }
            }

            override fun onPageScrollStateChanged(state: Int) {
            }

        })

        binding.mainViewPager.adapter = FragAdapter(supportFragmentManager, mutableListOf<Fragment>().apply {
            add(HomeFragment())
            add(RecordFragment())
            add(NewsFragment())
            add(SettingsFragment())
        })
    }

}