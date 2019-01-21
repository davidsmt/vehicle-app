package com.davidsmt.data.vehicle.remote

import com.davidsmt.data.vehicle.remote.models.VehiclesResponse
import retrofit2.Call
import retrofit2.http.GET

/**
 * Created by David SMT on 20/01/2019.
 */
interface VehicleApi {

    @GET("vehicles")
    fun getVehicles(): Call<VehiclesResponse>

}