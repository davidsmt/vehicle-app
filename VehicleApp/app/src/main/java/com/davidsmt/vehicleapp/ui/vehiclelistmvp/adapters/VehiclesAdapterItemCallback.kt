package com.davidsmt.vehicleapp.ui.vehiclelistmvp.adapters

import androidx.recyclerview.widget.DiffUtil
import com.davidsmt.vehicleapp.ui.models.VehicleUI

/**
 * Created by David SMT on 18/01/2019.
 */
class VehiclesAdapterItemCallback : DiffUtil.ItemCallback<VehicleUI>() {
    override fun areItemsTheSame(oldItem: VehicleUI, newItem: VehicleUI): Boolean {
        return oldItem.vehicleId == newItem.vehicleId
    }

    override fun areContentsTheSame(oldItem: VehicleUI, newItem: VehicleUI): Boolean {
        return oldItem == newItem
    }
}
