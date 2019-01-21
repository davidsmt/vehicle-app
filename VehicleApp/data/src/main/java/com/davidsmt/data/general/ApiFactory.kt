package com.davidsmt.data.general

import com.davidsmt.data.vehicle.remote.VehicleApi


/**
 * Created by David SMT on 20/01/2019.
 */
object ApiFactory {

    val vehicleApi: VehicleApi = RetrofitHelper.getInstance().create(VehicleApi::class.java)

}
