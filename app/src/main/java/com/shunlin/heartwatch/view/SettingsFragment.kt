package com.shunlin.heartwatch.view

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.shunlin.heartwatch.BuildConfig
import com.shunlin.heartwatch.R
import com.shunlin.heartwatch.base.BaseFragment
import com.shunlin.heartwatch.databinding.FragmentSettingsBinding
import com.shunlin.heartwatch.model.PageType
import com.shunlin.heartwatch.vm.FragmentViewModel

class SettingsFragment : BaseFragment<FragmentSettingsBinding, FragmentViewModel>() {
    override fun initViewBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentSettingsBinding {
        return FragmentSettingsBinding.inflate(inflater, container, false)
    }

    override fun initViewModel(): FragmentViewModel {
        return ViewModelProvider(this)[FragmentViewModel::class.java]
    }

    override fun initData() {
        binding.settingPrivacyBg.setOnClickListener {
            startContentActivity(PageType.Content.url, title = getString(R.string.setting_privacy), url = "https://www.jianshu.com/p/bfa7bf5c6beb")
        }
        binding.settingPolicyBg.setOnClickListener {
            startContentActivity(PageType.Content.url, title = getString(R.string.setting_policy), url = "https://blog.csdn.net/AdrianAndroid/article/details/128936822")
        }
        binding.settingSharedBg.setOnClickListener {
            kotlin.runCatching {
                startActivity(Intent(Intent.ACTION_SEND).apply {
                    type = "text/plain"
                    putExtra(Intent.EXTRA_TEXT, "https://play.google.com/store/apps/details?id=${BuildConfig.APPLICATION_ID}")
                })
            }
        }
    }
}