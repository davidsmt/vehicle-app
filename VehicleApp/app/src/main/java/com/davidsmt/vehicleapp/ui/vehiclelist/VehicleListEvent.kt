package com.davidsmt.vehicleapp.ui.vehiclelist

import com.davidsmt.vehicleapp.ui.models.VehicleUI

sealed class VehicleListEvent {
    object ShowContent : VehicleListEvent()

    object ShowEmptyListError : VehicleListEvent()

    object ShowGeneralError : VehicleListEvent()

    data class ShowVehicleDetailsScreen(val vehicle: VehicleUI) : VehicleListEvent()

}