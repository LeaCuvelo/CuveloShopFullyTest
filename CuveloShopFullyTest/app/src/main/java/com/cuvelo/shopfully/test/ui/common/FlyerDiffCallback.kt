package com.cuvelo.shopfully.test.ui.common

import androidx.recyclerview.widget.DiffUtil
import com.cuvelo.shopfully.domain.FlyerDataDomain

class FlyerDiffCallback(
    private val newFlyerList: List<FlyerDataDomain>,
    private val oldFlyerList: List<FlyerDataDomain>): DiffUtil.Callback() {

    override fun getOldListSize() = oldFlyerList.size
    override fun getNewListSize() = newFlyerList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldFlyer = oldFlyerList[oldItemPosition]
        val newFlyer = newFlyerList[newItemPosition]

        return oldFlyer == newFlyer
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldFlyerList[oldItemPosition] == newFlyerList[newItemPosition]

}


