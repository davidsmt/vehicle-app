package com.davidsmt.vehicleapp.ui.vehiclelistmvp

import com.davidsmt.domain.ResourceException
import com.davidsmt.domain.Vehicle
import com.davidsmt.usecases.GetVehicles
import com.davidsmt.vehicleapp.ui.general.BasePresenter
import com.davidsmt.vehicleapp.ui.models.VehicleUI
import com.davidsmt.vehicleapp.ui.models.toVehicleUIModel

/**
 * Created by David SMT on 18/01/2019.
 */
class VehicleListPresenter(
    view: VehicleListView?,
    private val getVehicles: GetVehicles
) : BasePresenter<VehicleListView>(view) {

    override fun attachView(view: VehicleListView) {
        super.attachView(view)

        loadVehicles()
    }

    fun loadVehicles() {
        getVehicles.execute(
            onSuccess = { list: List<Vehicle> ->
                // Mapping can be moved in BaseUseCase to improve performance in case a lot of items are received
                view?.showVehicles(list.map { it.toVehicleUIModel() })
            },
            onError = { resourceException: ResourceException? ->
                when (resourceException) {
                    is ResourceException.NullOrEmptyResource -> {
                        view?.showEmptyListError()
                    }
                    else -> {
                        view?.showGeneralError()
                    }
                }
            })
    }

    fun onVehicleItemClicked(vehicle: VehicleUI) {
        view?.showVehicleDetailsScreen(vehicle)
    }

    fun onRetryButtonPressed() {
        loadVehicles()
    }
}