package com.shunlin.heartwatch.view

import android.content.res.ColorStateList
import android.view.Gravity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.shunlin.heartwatch.R
import com.shunlin.heartwatch.base.BaseActivity
import com.shunlin.heartwatch.database.RecordDatabase
import com.shunlin.heartwatch.databinding.ActivityRecordNewBinding
import com.shunlin.heartwatch.model.PageType
import com.shunlin.heartwatch.model.RecordEntity
import com.shunlin.heartwatch.model.RecordItemType
import com.shunlin.heartwatch.ui.DateAndTimePopupWindow
import com.shunlin.heartwatch.helper.DateKt
import com.shunlin.heartwatch.helper.dp2px
import com.shunlin.heartwatch.helper.logE
import com.shunlin.heartwatch.helper.other
import com.shunlin.heartwatch.helper.toast
import com.shunlin.heartwatch.helper.yes
import com.shunlin.heartwatch.vm.HeartViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class RecordNewActivity : BaseActivity<ActivityRecordNewBinding, HeartViewModel>() {
    private lateinit var recordEntity: RecordEntity
    private lateinit var pageType: String
    override fun setBinding(): ActivityRecordNewBinding {
        return ActivityRecordNewBinding.inflate(layoutInflater)
    }

    override fun setViewModel(): HeartViewModel {
        return ViewModelProvider(this)[HeartViewModel::class.java]
    }

    override fun initData() {
        binding.recordNewTitle.back.setOnClickListener {
            finish()
        }
        binding.clTime.setOnClickListener {
            DateAndTimePopupWindow(context = this, currTime = recordEntity.time, clickSure = {
                "current = ${System.currentTimeMillis()}   time = ${it} : ${DateKt.setShowTime(it)}".logE()
                binding.time.text = DateKt.setShowTime(it)
                recordEntity.time = it
            }).showAtLocation(binding.recordNewParent, Gravity.BOTTOM, 0, 0)
        }

        binding.recordNewSave.setOnClickListener {
            if (recordEntity.dias > recordEntity.sys) {
                getString(R.string.toast_record_new).toast(this)
            } else {
                CoroutineScope(Dispatchers.IO + SupervisorJob()).launch {
                    kotlin.runCatching {
                        RecordDatabase.getDatabase(this@RecordNewActivity).recordDao().let {
                            when (pageType) {
                                PageType.Record.add -> {
                                    it.insert(recordEntity)
                                }

                                PageType.Record.edit -> {
                                    it.update(recordEntity)
                                }
                            }
                        }
                    }
                }
                finish()
            }
        }
        pageType = intent.getStringExtra(PageType.pageType) ?: PageType.Record.add
        when (pageType) {
            PageType.Record.add -> {
                recordEntity = RecordEntity(sys = 90, dias = 60, time = System.currentTimeMillis(), level = 1, type = RecordItemType.record)
            }

            PageType.Record.edit -> {
                recordEntity =
                    intent.getParcelableExtra(PageType.Record.edit) ?: RecordEntity(
                        sys = 90,
                        dias = 60,
                        time = System.currentTimeMillis(),
                        level = 1,
                        type = RecordItemType.record
                    )
            }
        }
        binding.systolicPicker.onSelect = ::systolicOnMove
        binding.diastolicPicker.onSelect = ::diastolicOnMove

        binding.systolicPicker.onMove = ::systolicOnMove
        binding.diastolicPicker.onMove = ::diastolicOnMove

        recordEntity.let {
            binding.time.text = DateKt.setShowTime(it.time)
            mutableListOf<String>().apply {
                (20..300).forEach { num ->
                    add(num.toString())
                }
            }.let { list ->
                binding.systolicPicker.setData(list, list.indexOf(it.sys.toString()))
                binding.diastolicPicker.setData(list, list.indexOf(it.dias.toString()))
            }
        }
    }

    private fun systolicOnMove(value: String, index: Int) {
        recordEntity.sys = value.toInt()
        recordEntity.level = (recordEntity.dias in 20..<60).yes {
            0
        }.other {
            when (value.toInt()) {
                in 20..<90 -> {
                    0
                }

                in 90..<120 -> {
                    1
                }

                in 120..<130 -> {
                    2
                }

                in 130..<140 -> {
                    3
                }

                in 140..180 -> {
                    4
                }

                in 181..300 -> {
                    5
                }

                else -> {
                    recordEntity.level
                }
            }
        }
        moveDegreeScale()
    }

    private fun diastolicOnMove(value: String, index: Int) {
        recordEntity.dias = value.toInt()
        recordEntity.level = (recordEntity.sys in 20..<90).yes {
            0
        }.other {
            when (value.toInt()) {
                in 20..<60 -> {
                    0
                }

                in 60..<80 -> {
                    when (recordEntity.sys) {
                        in 90..119 -> {
                            1
                        }

                        in 120..129 -> {
                            2
                        }

                        else -> {
                            recordEntity.level
                        }
                    }
                }

                in 80..<90 -> {
                    when (recordEntity.sys) {
                        in 130..<140 -> 3
                        else -> recordEntity.level
                    }
                }

                in 90..120 -> {
                    when (recordEntity.sys) {
                        in 140..<180 -> 4
                        else -> recordEntity.level
                    }
                }

                in 121..300 -> {
                    when (recordEntity.sys) {
                        in 181..320 -> 5
                        else -> recordEntity.level
                    }
                }

                else -> {
                    recordEntity.level
                }
            }
        }
        moveDegreeScale()
    }

    private fun moveDegreeScale() {
        var color = R.color.record_degree_0
        var title = ""
        var content = ""
        var des = ""
        when (recordEntity.level) {
            0 -> {
                color = R.color.record_degree_0
                title = getString(R.string.record_degree_title_0)
                content = getString(R.string.record_degree_content_0)
                des = getString(R.string.record_degree_desc_0)
            }

            1 -> {
                color = R.color.record_degree_1
                title = getString(R.string.record_degree_title_1)
                content = getString(R.string.record_degree_content_1)
                des = getString(R.string.record_degree_desc_1)
            }

            2 -> {
                color = R.color.record_degree_2
                title = getString(R.string.record_degree_title_2)
                content = getString(R.string.record_degree_content_2)
                des = getString(R.string.record_degree_desc_2)
            }

            3 -> {
                color = R.color.record_degree_3
                title = getString(R.string.record_degree_title_3)
                content = getString(R.string.record_degree_content_3)
                des = getString(R.string.record_degree_desc_3)
            }

            4 -> {
                color = R.color.record_degree_4
                title = getString(R.string.record_degree_title_4)
                content = getString(R.string.record_degree_content_4)
                des = getString(R.string.record_degree_desc_4)
            }

            5 -> {
                color = R.color.record_degree_5
                title = getString(R.string.record_degree_title_5)
                content = getString(R.string.record_degree_content_5)
                des = getString(R.string.record_degree_desc_5)
            }
        }
        binding.degreeScale.translationX = (recordEntity.level * (36.0f + 18.4f)).dp2px(this)
        ColorStateList.valueOf(ContextCompat.getColor(this, color)).let {
            binding.degreeTitle.setTextColor(it)
            binding.degreeContent.setTextColor(it)
        }
        binding.degreeTitle.text = title
        binding.degreeContent.text = content
        binding.degreeDes.text = des
    }
}