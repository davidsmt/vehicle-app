package com.davidsmt.vehicleapp.ui.vehiclelist

import android.os.Bundle
import android.view.View
import com.davidsmt.data.vehicle.VehicleRepository
import com.davidsmt.usecases.GetVehicles
import com.davidsmt.vehicleapp.R
import com.davidsmt.vehicleapp.ui.general.BaseActivity
import com.davidsmt.vehicleapp.ui.vehicledetails.VehicleDetailsActivity
import com.davidsmt.vehicleapp.ui.vehiclelist.adapters.VehiclesAdapter
import com.davidsmt.vehicleapp.ui.models.VehicleUI
import kotlinx.android.synthetic.main.activity_vehicle_list.*

class VehicleListActivity : BaseActivity(), VehicleListView {

    private var presenter: VehicleListPresenter = VehicleListPresenter(
        this,
        GetVehicles(VehicleRepository.getInstance())
    )
    private lateinit var vehiclesAdapter: VehiclesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vehicle_list)

        initAdapter()

        presenter.attachView(this)
    }

    private fun initAdapter() {
        vehiclesAdapter = VehiclesAdapter(
            object : VehiclesAdapter.Listener {
                override fun onClick(vehicle: VehicleUI) {
                    presenter.onVehicleItemClicked(vehicle)
                }
            }
        )
        vehicles_recyclerview.adapter = vehiclesAdapter
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }

    override fun showVehicles(vehicles: List<VehicleUI>) {
        loading_error_view.finishLoading {
            vehicles_recyclerview.visibility = View.VISIBLE
            vehiclesAdapter.submitList(vehicles)
        }
    }

    override fun showEmptyListError() {
        loading_error_view.showErrorMessage(getString(R.string.empty_vehicle_list_error_message)) {
            presenter.onRetryButtonPressed()
        }
    }

    override fun showGeneralError() {
        loading_error_view.showErrorMessage(getString(R.string.general_error_message)) {
            presenter.onRetryButtonPressed()
        }
    }

    override fun showVehicleDetailsScreen(vehicle: VehicleUI) {
        VehicleDetailsActivity.start(this, vehicle)
    }
}
