package com.cuvelo.shopfully.test.ui.main

import android.content.Context
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView


class GridLayoutManagerWrapper(context: Context) : GridLayoutManager(context, 2) {

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