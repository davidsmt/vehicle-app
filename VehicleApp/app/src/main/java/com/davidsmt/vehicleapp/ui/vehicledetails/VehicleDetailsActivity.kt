package com.davidsmt.vehicleapp.ui.vehicledetails

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.davidsmt.vehicleapp.R
import com.davidsmt.vehicleapp.databinding.ActivityVehicleDetailsBinding
import com.davidsmt.vehicleapp.ui.general.BaseActivity
import com.davidsmt.vehicleapp.ui.models.VehicleUI

class VehicleDetailsActivity : BaseActivity(), VehicleDetailsView {

    private lateinit var presenter: VehicleDetailsPresenter
    private lateinit var binding: ActivityVehicleDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_vehicle_details)

        var vehicle: VehicleUI? = null
        if (intent.hasExtra(VEHICLE_EXTRA)) {
            vehicle = intent.getParcelableExtra(VEHICLE_EXTRA)
        }

        presenter = VehicleDetailsPresenter(this, vehicle)

        presenter.attachView(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }

    override fun showDetails(vehicle: VehicleUI?) {
        binding.vehicle = vehicle
    }

    companion object {
        private const val VEHICLE_EXTRA = "VEHICLE_EXTRA"

        fun start(activity: Activity, vehicle: VehicleUI) {
            val starter = Intent(activity, VehicleDetailsActivity::class.java)
            starter.putExtra(VEHICLE_EXTRA, vehicle)
            activity.startActivity(starter)
        }
    }
}