package com.shunlin.heartwatch.ui

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import com.shunlin.heartwatch.helper.dp2px

class RecyclerItemDecoration(private val item:Int):ItemDecoration() {
    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)
        item.dp2px(parent.context).let {
            outRect.top = it
            outRect.bottom = it
            outRect.right = it
            outRect.left = it
        }
    }
}