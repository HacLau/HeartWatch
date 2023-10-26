package com.shunlin.heartwatch.view

import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.shunlin.heartwatch.base.BaseActivity
import com.shunlin.heartwatch.database.RecordDatabase
import com.shunlin.heartwatch.databinding.ActivityRecordMoreBinding
import com.shunlin.heartwatch.model.PageType
import com.shunlin.heartwatch.model.RecordEntity
import com.shunlin.heartwatch.ui.RecordAdapter
import com.shunlin.heartwatch.ui.RecyclerItemDecoration
import com.shunlin.heartwatch.helper.DateKt
import com.shunlin.heartwatch.helper.RecordType
import com.shunlin.heartwatch.vm.HeartViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class RecordMoreActivity : BaseActivity<ActivityRecordMoreBinding, HeartViewModel>() {
    override fun setBinding(): ActivityRecordMoreBinding {
        return ActivityRecordMoreBinding.inflate(layoutInflater)
    }

    override fun setViewModel(): HeartViewModel {
        return ViewModelProvider(this)[HeartViewModel::class.java]
    }

    override fun initData() {
        binding.recordMoreTitle.back.setOnClickListener {
            finish()
        }

        binding.recordMoreRv.addItemDecoration(RecyclerItemDecoration(6))
        binding.recordMoreRv.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        viewModel.recordAdapter = RecordAdapter(this, listOf() , onEditClick = {
            startRecordNewActivity(PageType.Record.edit, it)
        })

        binding.recordMoreRv.adapter = viewModel.recordAdapter
    }

    override fun onResume() {
        super.onResume()
        getRecordData()
    }

    private fun getRecordData() {
        viewModel.recordAdapter.setList(mutableListOf<RecordEntity>().apply {
            CoroutineScope(Dispatchers.IO + SupervisorJob()).launch {
                RecordDatabase.getDatabase(this@RecordMoreActivity).recordDao().queryAll().filter {
                    it.time < when (viewModel.recordType) {
                        RecordType.RECENT -> {
                            DateKt.getToday() * 1000
                        }

                        RecordType.WEEK -> {
                            DateKt.getThisWeek() * 1000
                        }

                        RecordType.MONTH -> {
                            DateKt.getThisMonth() * 1000
                        }

                        RecordType.LASTMONTH -> {
                            DateKt.getLastMonth() * 1000
                        }

                        else -> {
                            System.currentTimeMillis()
                        }
                    }
                }.forEach { record ->
                    add(record)
                }

            }
        })


    }

}