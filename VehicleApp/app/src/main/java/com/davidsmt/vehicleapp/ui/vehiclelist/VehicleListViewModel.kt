package com.davidsmt.vehicleapp.ui.vehiclelist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.davidsmt.domain.ResourceException
import com.davidsmt.domain.Vehicle
import com.davidsmt.usecases.GetVehicles
import com.davidsmt.vehicleapp.ui.general.SingleLiveEvent
import com.davidsmt.vehicleapp.ui.models.VehicleUI
import com.davidsmt.vehicleapp.ui.models.toVehicleUIModel

class VehicleListViewModel(
        private val getVehicles: GetVehicles
) : ViewModel() {

    private val events: SingleLiveEvent<VehicleListEvent> = SingleLiveEvent()
    private val vehiclesLiveData = MutableLiveData<List<VehicleUI>>()

    fun getEvents(): LiveData<VehicleListEvent> = events

    fun getVehicles(): LiveData<List<VehicleUI>> = vehiclesLiveData

    fun start() {
        loadVehicles()
    }

    private fun loadVehicles() {
        getVehicles.execute(
                onSuccess = { list: List<Vehicle> ->
                    vehiclesLiveData.value = list.map { it.toVehicleUIModel() }
                    events.value = VehicleListEvent.ShowContent
                },
                onError = { resourceException: ResourceException? ->
                    when (resourceException) {
                        is ResourceException.NullOrEmptyResource -> events.value = VehicleListEvent.ShowEmptyListError
                        else -> events.value = VehicleListEvent.ShowGeneralError
                    }
                })
    }

    fun onVehicleItemClicked(vehicle: VehicleUI) {
        events.value = VehicleListEvent.ShowVehicleDetailsScreen(vehicle)
    }

    fun onRetryButtonPressed() {
        loadVehicles()
    }

}
