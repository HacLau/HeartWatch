package com.shunlin.heartwatch.view

import androidx.lifecycle.ViewModelProvider
import com.shunlin.heartwatch.base.BaseActivity
import com.shunlin.heartwatch.databinding.ActivitySplashBinding
import com.shunlin.heartwatch.helper.SharedHelper
import com.shunlin.heartwatch.vm.HeartViewModel

class ScreenActivity : BaseActivity<ActivitySplashBinding, HeartViewModel>() {

    override fun setBinding(): ActivitySplashBinding {
        return ActivitySplashBinding.inflate(layoutInflater)
    }

    override fun setViewModel(): HeartViewModel {
        return ViewModelProvider(this)[HeartViewModel::class.java]
    }

    override fun initData() {
        viewModel.startTimeTask(){
            binding.splashProgress.progress = it
            if (it == 100){
                if (SharedHelper.launchedStep){
                    startMainActivity()
                }else {
                    startStepActivity()
                }
            }
        }
    }


}