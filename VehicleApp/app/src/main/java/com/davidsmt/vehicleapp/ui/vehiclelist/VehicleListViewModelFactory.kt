package com.davidsmt.vehicleapp.ui.vehiclelist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.davidsmt.usecases.GetVehicles


class VehicleListViewModelFactory(private val getVehicles: GetVehicles) :
        ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(VehicleListViewModel::class.java)) {
            return VehicleListViewModel(getVehicles) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
