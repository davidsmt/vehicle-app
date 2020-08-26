package com.davidsmt.vehicleapp.ui.vehicledetailsmvp

import com.davidsmt.vehicleapp.ui.general.BasePresenter
import com.davidsmt.vehicleapp.ui.models.VehicleUI

/**
 * Created by David SMT on 20/01/2019.
 */
class VehicleDetailsPresenter(
    view: VehicleDetailsView?,
    private val vehicle: VehicleUI?
) : BasePresenter<VehicleDetailsView>(view) {

    override fun attachView(view: VehicleDetailsView) {
        super.attachView(view)
        this.view?.showDetails(vehicle)
    }

}
