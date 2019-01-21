package com.davidsmt.data.vehicle.remote

import com.davidsmt.data.vehicle.remote.models.toVehicleModel
import com.davidsmt.domain.Resource
import com.davidsmt.domain.ResourceException
import com.davidsmt.domain.Vehicle


/**
 * Created by David SMT on 18/01/2019.
 */
class VehicleRemoteDataSource(private val api: VehicleApi) {

    fun getVehicles(): Resource<List<Vehicle>?> {
        lateinit var resource: Resource<List<Vehicle>?>
        try {
            val response = api.getVehicles().execute()
            when {
                response.isSuccessful -> {
                    val vehicles = response.body()
                    resource = Resource.success(vehicles?.vehicles?.map { it.toVehicleModel() })
                }
                else -> {
                    // Parse error response if API is ready for it
                    resource = Resource.error(ResourceException.ApiError("VehicleRemoteDataSource - API error"))
                }
            }
        } catch (exception: Exception) {
            resource = Resource.error(
                ResourceException.RemoteResponseError("VehicleRemoteDataSource - Exception error", exception)
            )
        }

        return resource
    }

}