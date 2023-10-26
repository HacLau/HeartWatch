package com.shunlin.heartwatch.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.shunlin.heartwatch.R

data class StepModel(
    @StringRes
    var title: Int,
    @StringRes
    var content: Int,
    @DrawableRes
    var image: Int
)

val stepList: MutableList<StepModel> = mutableListOf<StepModel>().apply {
    add(StepModel(R.string.title_step_1, R.string.content_step_1, R.mipmap.bg_step_1))
    add(StepModel(R.string.title_step_2, R.string.content_step_2, R.mipmap.bg_step_2))
    add(StepModel(R.string.title_step_3, R.string.content_step_3, R.mipmap.bg_step_3))
}