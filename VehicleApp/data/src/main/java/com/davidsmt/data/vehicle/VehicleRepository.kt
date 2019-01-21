package com.davidsmt.data.vehicle

import com.davidsmt.data.general.ApiFactory
import com.davidsmt.data.vehicle.remote.VehicleRemoteDataSource
import com.davidsmt.domain.Resource
import com.davidsmt.domain.Vehicle

class VehicleRepository private constructor(private val vehicleRemoteDataSource: VehicleRemoteDataSource) {

    fun getVehicles(): Resource<List<Vehicle>?> = vehicleRemoteDataSource.getVehicles()

    companion object {
        @Volatile private var instance: VehicleRepository? = null

        fun getInstance() = instance ?: create()

        private fun create(): VehicleRepository = synchronized(this) {
            instance ?: VehicleRepository(VehicleRemoteDataSource(ApiFactory.vehicleApi)).also { instance = it }
        }
    }

}