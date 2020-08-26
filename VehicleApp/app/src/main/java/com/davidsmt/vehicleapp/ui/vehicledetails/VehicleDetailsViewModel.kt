package com.davidsmt.vehicleapp.ui.vehicledetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.davidsmt.vehicleapp.ui.models.VehicleUI

class VehicleDetailsViewModel : ViewModel() {

    private val vehicleLiveData = MutableLiveData<VehicleUI>()

    fun getVehicle(): LiveData<VehicleUI> = vehicleLiveData

    fun start(vehicle: VehicleUI?) {
        vehicleLiveData.value = vehicle
    }

}
