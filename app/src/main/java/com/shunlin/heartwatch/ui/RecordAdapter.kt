package com.shunlin.heartwatch.ui

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.shunlin.heartwatch.R
import com.shunlin.heartwatch.model.RecordEntity
import com.shunlin.heartwatch.model.RecordItemType
import com.shunlin.heartwatch.helper.DateKt

class RecordAdapter(
    private val context: Context,
    private var list: List<RecordEntity>,
    private val onEditClick: (RecordEntity) -> Unit = {}
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            RecordItemType.title -> {
                RecordTopViewHolder(LayoutInflater.from(context).inflate(R.layout.layout_record_top,null,false))
            }

            else -> {
                RecordViewHolder(LayoutInflater.from(context).inflate(R.layout.layout_record_item,null,false))
            }

        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        setItemDegree(list[position].level, holder)
        when (getItemViewType(position)) {
            RecordItemType.title -> {
                if (holder is RecordTopViewHolder) {
                    list[position]?.let {

                    }
                }
            }

            RecordItemType.record -> {
                if (holder is RecordViewHolder) {
                    list[position]?.let {
                        holder.systolic.text = it.sys.toString()
                        holder.diastolic.text = it.dias.toString()
                        holder.editButton.setOnClickListener { _ ->
                            onEditClick.invoke(it)
                        }
                        holder.degreeTime.text = DateKt.setShowTime(it.time)
                    }
                }
            }
        }

    }

    override fun getItemViewType(position: Int): Int {
        return list[position].type
    }

    private fun setItemDegree(level: Int, holder: RecyclerView.ViewHolder) {
        var color = R.color.record_degree_0
        when (level) {
            0 -> {
                color = R.color.record_degree_0
            }

            1 -> {
                color = R.color.record_degree_1
            }

            2 -> {
                color = R.color.record_degree_2
            }

            3 -> {
                color = R.color.record_degree_3
            }

            4 -> {
                color = R.color.record_degree_4
            }

            5 -> {
                color = R.color.record_degree_5
            }
        }
        when (holder) {
            is RecordViewHolder -> {
                holder.degreeText.text = context.getString(R.string.record_degree_title_0)
                ColorStateList.valueOf(ContextCompat.getColor(context, color)).let {
                    holder.degreeColor.imageTintList = it
                    holder.degreeText.setTextColor(it)
                }
            }

            is RecordTopViewHolder -> {
                ColorStateList.valueOf(ContextCompat.getColor(context, color)).let {

                }
            }
        }

    }

    @SuppressLint("NotifyDataSetChanged")
    fun setList(dataList: List<RecordEntity>) {
        list = dataList
        notifyDataSetChanged()
    }

    inner class RecordViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var systolic:TextView = view.findViewById(R.id.item_sys)
        var diastolic:TextView = view.findViewById(R.id.item_dias)

        var degreeColor:ImageView = view.findViewById(R.id.item_degree_color)
        var degreeText:TextView = view.findViewById(R.id.item_degree_text)
        var degreeTime:TextView = view.findViewById(R.id.item_degree_time)
        var editButton:AppCompatButton = view.findViewById(R.id.item_edit)
    }

    inner class RecordTopViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    }
}