package com.davidsmt.vehicleapp.ui.vehiclelistmvp

import com.davidsmt.vehicleapp.ui.general.BaseView
import com.davidsmt.vehicleapp.ui.models.VehicleUI

/**
 * Created by David SMT on 18/01/2019.
 */
interface VehicleListView : BaseView {
    fun showVehicles(vehicles: List<VehicleUI>)
    fun showEmptyListError()
    fun showGeneralError()
    fun showVehicleDetailsScreen(vehicle: VehicleUI)

}