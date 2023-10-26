package com.shunlin.heartwatch.base

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding
import com.shunlin.heartwatch.model.NewsEntity
import com.shunlin.heartwatch.model.PageType
import com.shunlin.heartwatch.model.RecordEntity
import com.shunlin.heartwatch.helper.logE
import com.shunlin.heartwatch.view.ContentActivity
import com.shunlin.heartwatch.view.MainActivity
import com.shunlin.heartwatch.view.RecordMoreActivity
import com.shunlin.heartwatch.view.RecordNewActivity
import com.shunlin.heartwatch.view.ScreenActivity
import com.shunlin.heartwatch.view.StepActivity

abstract class BaseActivity<VB : ViewBinding, VM : ViewModel> : AppCompatActivity() {
    lateinit var binding: VB
    lateinit var viewModel: VM
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setCustomDensity()
        binding = setBinding()
        setContentView(binding.root)
        viewModel = setViewModel()
        initData()
    }

    private fun setCustomDensity() {
        val metrics = resources.displayMetrics
        (metrics.widthPixels / 360f).let {
            metrics.density = it
            metrics.scaledDensity = it
            metrics.densityDpi = (160 * it).toInt()
        }
    }

    abstract fun setBinding(): VB

    abstract fun setViewModel(): VM

    abstract fun initData()

    fun startSplashActivity() {
        startActivity(Intent(this, ScreenActivity::class.java))
        finish()
    }

    fun startStepActivity() {
        startActivity(Intent(this, StepActivity::class.java))
        finish()
    }

    fun startMainActivity() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    fun startRecordMoreActivity() {
        startActivity(Intent(this, RecordMoreActivity::class.java))
    }


    fun startRecordNewActivity(pageType: String, record: RecordEntity? = null) {
        startActivity(Intent(this, RecordNewActivity::class.java).apply {
            putExtra(PageType.pageType, pageType)
            putExtra(pageType, record)
        })
    }


    fun startContentActivity(pageType: String, news: NewsEntity? = null, url: String = "", title: String = "") {
        startActivity(Intent(this, ContentActivity::class.java).apply {
            putExtra(PageType.pageType, pageType)
            putExtra(PageType.Content.title, title)
            when (pageType) {
                PageType.Content.url -> putExtra(pageType, url)
                PageType.Content.news -> putExtra(pageType, news)

            }

        })
    }
}