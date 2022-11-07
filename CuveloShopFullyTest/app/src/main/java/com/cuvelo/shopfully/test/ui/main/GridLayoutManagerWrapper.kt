package com.cuvelo.shopfully.test.ui.main

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView


class GridLayoutManagerWrapper : GridLayoutManager {

    constructor(context: Context) : super(context,2) {

    }

    constructor(
        context: Context?,
        attrs: AttributeSet?,
        defStyleAttr: Int,
        defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes) {

    }

    override fun onLayoutChildren(recycler: RecyclerView.Recycler?, state: RecyclerView.State?) {
        try {
            super.onLayoutChildren(recycler, state)
        } catch (e: IndexOutOfBoundsException) {
            Log.e("GLMWrapper", "Invalid view holder adapter position")
        }
    }

    override fun supportsPredictiveItemAnimations(): Boolean {
        return false
    }
}