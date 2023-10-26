package com.shunlin.heartwatch.ui

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.shunlin.heartwatch.R

class TitleView : ConstraintLayout {
    lateinit var back: ImageView
    lateinit var title: TextView

    constructor(context: Context) : super(context) {
        initView(context)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        initView(context)
        setStyle(context, attrs)
    }

    private fun setStyle(context: Context, attrs: AttributeSet?) {
        if (attrs != null) {
            val array = context.obtainStyledAttributes(attrs, R.styleable.TitleView)
            array.getString(R.styleable.TitleView_title).let {
                title.text = it
            }
            array.recycle()
        }
    }

    private fun initView(context: Context) {
        val view = LayoutInflater.from(context).inflate(R.layout.layout_title, this, true)
        back = view.findViewById(R.id.title_back)
        title = view.findViewById(R.id.title_title)
    }
}