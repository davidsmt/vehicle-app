package com.davidsmt.vehicleapp.ui.vehiclelist

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.davidsmt.data.vehicle.VehicleRepository
import com.davidsmt.usecases.GetVehicles
import com.davidsmt.vehicleapp.R
import com.davidsmt.vehicleapp.ui.general.BaseActivity
import com.davidsmt.vehicleapp.ui.models.VehicleUI
import com.davidsmt.vehicleapp.ui.vehicledetails.VehicleDetailsActivity
import com.davidsmt.vehicleapp.ui.vehiclelistmvp.adapters.VehiclesAdapter
import kotlinx.android.synthetic.main.activity_vehicle_list.*

class VehicleListActivity : BaseActivity() {

    private val viewModelFactory = VehicleListViewModelFactory(
            GetVehicles(VehicleRepository.getInstance())
    )
    private lateinit var viewModel: VehicleListViewModel
    private lateinit var vehiclesAdapter: VehiclesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vehicle_list)
        viewModel = ViewModelProvider(this, viewModelFactory).get(VehicleListViewModel::class.java)

        initAdapter()
        setupObservers()

        viewModel.start()
    }

    private fun initAdapter() {
        vehiclesAdapter = VehiclesAdapter(
                object : VehiclesAdapter.Listener {
                    override fun onClick(vehicle: VehicleUI) {
                        viewModel.onVehicleItemClicked(vehicle)
                    }
                }
        )
        vehicles_recyclerview.adapter = vehiclesAdapter
    }

    private fun setupObservers() {
        viewModel.getVehicles().observe(this, Observer {
            vehiclesAdapter.submitList(it)
        })
        viewModel.getEvents().observe(this, Observer { event ->
            when (event) {
                VehicleListEvent.ShowContent -> showVehicles()
                VehicleListEvent.ShowEmptyListError -> showEmptyListError()
                VehicleListEvent.ShowGeneralError -> showGeneralError()
                is VehicleListEvent.ShowVehicleDetailsScreen -> showVehicleDetailsScreen(event.vehicle)
            }
        })
    }

    private fun showVehicles() {
        loading_error_view.finishLoading {
            vehicles_recyclerview.visibility = View.VISIBLE
        }
    }

    private fun showEmptyListError() {
        loading_error_view.showErrorMessage(getString(R.string.empty_vehicle_list_error_message)) {
            viewModel.onRetryButtonPressed()
        }
    }

    private fun showGeneralError() {
        loading_error_view.showErrorMessage(getString(R.string.general_error_message)) {
            viewModel.onRetryButtonPressed()
        }
    }

    private fun showVehicleDetailsScreen(vehicle: VehicleUI) {
        VehicleDetailsActivity.start(this, vehicle)
    }
}
