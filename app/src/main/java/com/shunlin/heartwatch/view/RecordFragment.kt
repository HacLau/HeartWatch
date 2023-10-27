package com.shunlin.heartwatch.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.shunlin.heartwatch.R
import com.shunlin.heartwatch.base.BaseFragment
import com.shunlin.heartwatch.database.RecordDatabase
import com.shunlin.heartwatch.databinding.FragmentRecordBinding
import com.shunlin.heartwatch.model.PageType
import com.shunlin.heartwatch.model.RecordEntity
import com.shunlin.heartwatch.ui.RecordAdapter
import com.shunlin.heartwatch.ui.RecordPopupWindow
import com.shunlin.heartwatch.ui.RecyclerItemDecoration
import com.shunlin.heartwatch.helper.DateKt
import com.shunlin.heartwatch.helper.RecordType
import com.shunlin.heartwatch.helper.logE
import com.shunlin.heartwatch.helper.other
import com.shunlin.heartwatch.helper.yes
import com.shunlin.heartwatch.vm.FragmentViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import java.util.Calendar

class RecordFragment : BaseFragment<FragmentRecordBinding, FragmentViewModel>() {
    override fun initViewBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentRecordBinding {
        return FragmentRecordBinding.inflate(inflater, container, false)
    }

    override fun initViewModel(): FragmentViewModel {
        return ViewModelProvider(this)[FragmentViewModel::class.java]
    }

    override fun initData() {
        binding.recordTopAddBg.setOnClickListener {
            startRecordNewActivity(PageType.Record.add)
        }
        binding.recordTopMoreBg.setOnClickListener {
            startRecordMoreActivity()
        }
        binding.recordTopFilterBg.setOnClickListener {
            if (viewModel.recordPopupWindow == null)
                viewModel.recordPopupWindow = RecordPopupWindow(context = this.requireActivity(), onClick = {

                    if (viewModel.recordType == it) {
                        return@RecordPopupWindow
                    }

                    getRecordData()
                    binding.recordTopFilterText.text = it
                    viewModel.recordType = it
                }, onDismiss = {
                    binding.recordTopFilterImage.setImageResource(R.mipmap.ic_record_packup)
                })
            if (viewModel.recordPopupWindow!!.isShowing) {
                viewModel.recordPopupWindow!!.dismiss()
            } else {
                viewModel.recordPopupWindow?.showAsDropDown(binding.recordTopFilterBg)
                binding.recordTopFilterImage.setImageResource(R.mipmap.ic_record_unfold)
            }
        }

        binding.recordRv.addItemDecoration(RecyclerItemDecoration(6))
        binding.recordRv.layoutManager = LinearLayoutManager(this.requireActivity(), LinearLayoutManager.VERTICAL, false)
        viewModel.recordAdapter = RecordAdapter(
            requireActivity(),
            listOf(),
            onEditClick = {
                startRecordNewActivity(PageType.Record.edit, it)
            })

        binding.recordRv.adapter = viewModel.recordAdapter

    }

    override fun onResume() {
        super.onResume()
        getRecordData()
    }

    private fun getRecordData() {
        mutableListOf<RecordEntity>().apply {
            CoroutineScope(Dispatchers.IO + SupervisorJob()).launch {
                var avgSys = 0
                var avgDia = 0
                RecordDatabase.getDatabase(requireActivity()).recordDao().queryAll().filter {

                    when (viewModel.recordType) {
                        RecordType.RECENT -> {
                            it.time < DateKt.getToday() && it.time > DateKt.getYesterday()
                        }

                        RecordType.WEEK -> {
                            it.time < DateKt.getDayOfWeek(Calendar.SATURDAY, 0) && it.time > DateKt.getDayOfWeek(Calendar.SUNDAY, 0)
                        }

                        RecordType.MONTH -> {
                            it.time < DateKt.getThisMonth() && it.time > DateKt.getLastMonth()
                        }

                        RecordType.LASTMONTH -> {
                            it.time < DateKt.getLastMonth() && it.time > DateKt.getLastLastMonth()
                        }

                        else -> {
                            it.time < System.currentTimeMillis()
                        }
                    }
                }.let {
                    val list = it.subList(0, (it.size > 5).yes { 5 }.other { it.size })
                    list.forEach { record ->
                            avgSys += record.sys
                            avgDia += record.dias
                        }
                    CoroutineScope(Dispatchers.Main + SupervisorJob()).launch {
                        binding.recordTopSys.text = list.isEmpty().yes { "0" }.other { "${avgSys.div(list.size)}" }
                        binding.recordTopDias.text = list.isEmpty().yes { "0" }.other { "${avgDia.div(list.size)}" }
                        viewModel.recordAdapter.setList(list)
                    }
                }
            }
        }


    }
}