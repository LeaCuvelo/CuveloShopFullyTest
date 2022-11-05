package com.cuvelo.shopfully.test.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.cuvelo.shopfully.domain.FlyerDataDomain
import com.cuvelo.shopfully.test.R
import com.cuvelo.shopfully.test.databinding.ItemFlyerBinding
import com.cuvelo.shopfully.test.ui.common.FlyerDiffCallback
import com.squareup.picasso.Picasso

class FlyersAdapter (private val listener: (String, String) -> Unit) :
    RecyclerView.Adapter<FlyersAdapter.FlyerViewHolder>() {

    var flyersList: List<FlyerDataDomain> = arrayListOf()
    private lateinit var binding: ItemFlyerBinding

    fun updateItems(newList: List<FlyerDataDomain>?) {
        if (newList != null) {
            val result = DiffUtil.calculateDiff(FlyerDiffCallback(flyersList, newList))
            flyersList = newList
            result.dispatchUpdatesTo(this)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlyerViewHolder {
        binding = ItemFlyerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FlyerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FlyerViewHolder, position: Int) {
        val flyer = flyersList[position]
        holder.bind(flyer)
        holder.itemView.setOnClickListener { listener(flyer.title, flyer.id) }
    }

    override fun getItemCount(): Int = flyersList.size

    //region ViewHolder

    inner class FlyerViewHolder(private val binding: ItemFlyerBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(flyer: FlyerDataDomain) {
            binding.flyer = flyer

            Picasso.get()
                .load("https://it-it-media.shopfully.cloud/images/volantini/${flyer.id}@3x.jpg")
                .placeholder(R.drawable.ic_placeholder)
                .error(R.drawable.ic_error)
                .into(binding.ivFlyerBackground)

        }
    }

    //endregion ViewHolder

}