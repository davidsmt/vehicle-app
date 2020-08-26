package com.davidsmt.vehicleapp.ui.vehiclelistmvp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.davidsmt.vehicleapp.R
import com.davidsmt.vehicleapp.databinding.LayoutRecyclerViewHeaderBinding
import com.davidsmt.vehicleapp.databinding.LayoutVehicleItemBinding
import com.davidsmt.vehicleapp.ui.customviews.ListAdapterWithHeader
import com.davidsmt.vehicleapp.ui.models.VehicleUI

/**
 * Created by David SMT on 18/01/2019.
 */
class VehiclesAdapter(
    private val listener: Listener?
) : ListAdapterWithHeader<VehicleUI, RecyclerView.ViewHolder>(VehiclesAdapterItemCallback()) {

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            0 -> HEADER_TYPE
            else -> ITEM_TYPE
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            HEADER_TYPE -> HeaderViewHolder(
                LayoutRecyclerViewHeaderBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
            else -> ItemViewHolder(
                LayoutVehicleItemBinding.inflate(LayoutInflater.from(parent.context), parent, false),
                listener
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            ITEM_TYPE -> {
                val itemViewHolder = (holder as ItemViewHolder)
                val vehicle = getItem(position)
                itemViewHolder.apply {
                    bind(vehicle)
                    itemView.tag = vehicle
                }
            }
        }
    }

    class ItemViewHolder(
        private val binding: LayoutVehicleItemBinding,
        listener: Listener?
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                val vehicle = this@ItemViewHolder.binding.vehicle
                vehicle?.let { item ->
                    listener?.onClick(item)
                }
            }
        }

        fun bind(item: VehicleUI) {
            binding.apply {
                vehicle = item
                executePendingBindings()
            }
        }
    }

    class HeaderViewHolder(binding: LayoutRecyclerViewHeaderBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.title = binding.root.context.resources.getString(R.string.vehicle_page_title)
        }
    }

    interface Listener {
        fun onClick(vehicle: VehicleUI)
    }

    companion object {
        private const val HEADER_TYPE = 0
        private const val ITEM_TYPE = 1
    }

}