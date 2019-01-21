package com.davidsmt.vehicleapp.ui.vehicledetails

import com.davidsmt.vehicleapp.ui.general.BaseView
import com.davidsmt.vehicleapp.ui.models.VehicleUI

/**
 * Created by David SMT on 20/01/2019.
 */
interface VehicleDetailsView : BaseView {
    fun showDetails(vehicle: VehicleUI?)
}