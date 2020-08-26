package com.davidsmt.vehicleapp.ui.vehicledetails

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.davidsmt.vehicleapp.R
import com.davidsmt.vehicleapp.databinding.ActivityVehicleDetailsBinding
import com.davidsmt.vehicleapp.ui.general.BaseActivity
import com.davidsmt.vehicleapp.ui.models.VehicleUI

class VehicleDetailsActivity : BaseActivity() {

    private lateinit var viewModel: VehicleDetailsViewModel
    private var _binding: ActivityVehicleDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = DataBindingUtil.setContentView(this, R.layout.activity_vehicle_details)
        binding.setLifecycleOwner(this)
        viewModel = ViewModelProvider(this).get(VehicleDetailsViewModel::class.java)

        setupObservers()

        var vehicle: VehicleUI? = null
        if (intent.hasExtra(VEHICLE_EXTRA)) {
            vehicle = intent.getParcelableExtra(VEHICLE_EXTRA)
        }

        viewModel.start(vehicle)
    }

    private fun setupObservers() {
        viewModel.getVehicle().observe(this, Observer {
            binding.vehicle = it
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
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
