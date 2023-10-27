package com.shunlin.heartwatch.view

import androidx.lifecycle.ViewModelProvider
import com.shunlin.heartwatch.R
import com.shunlin.heartwatch.base.BaseActivity
import com.shunlin.heartwatch.databinding.ActivityStartBinding
import com.shunlin.heartwatch.helper.Contacts
import com.shunlin.heartwatch.model.PageType
import com.shunlin.heartwatch.helper.toast
import com.shunlin.heartwatch.vm.HeartViewModel

class StartActivity : BaseActivity<ActivityStartBinding, HeartViewModel>() {

    override fun setBinding(): ActivityStartBinding {
        return ActivityStartBinding.inflate(layoutInflater)
    }

    override fun setViewModel(): HeartViewModel {
        return ViewModelProvider(this)[HeartViewModel::class.java]
    }

    override fun initData() {
        binding.start.setOnClickListener {
            if (binding.startCheck.isChecked)
                startSplashActivity()
            else
                "please checked the privacy & policy box".toast(this)
        }

        binding.startPrivacy.setOnClickListener {
            startContentActivity(PageType.Content.url, title = getString(R.string.setting_privacy), url = Contacts.privacy)
        }

        binding.startPolicy.setOnClickListener {
            startContentActivity(PageType.Content.url, title = getString(R.string.setting_policy), url = Contacts.policy)
        }
    }
}