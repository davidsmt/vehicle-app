package com.davidsmt.data.vehicle.remote.models

import com.davidsmt.domain.Vehicle
import com.google.gson.annotations.SerializedName

/**
 * Created by David SMT on 20/01/2019.
 */
data class VehiclesResponse(
    @SerializedName("count") val count: Long? = null,
    @SerializedName("vehicles") val vehicles: List<VehicleResponse>? = null,
    @SerializedName("currentPage") val currentPage: Long? = null,
    @SerializedName("nextPage") val nextPage: Long? = null,
    @SerializedName("totalPages") val totalPages: Long? = null
)

data class VehicleResponse(
    @SerializedName("vehicleId") val vehicleId: Long? = null,
    @SerializedName("vrn") val vrn: String? = null,
    @SerializedName("country") val country: String? = null,
    @SerializedName("color") val color: String? = null,
    @SerializedName("type") val type: String? = null,
    @SerializedName("default") val default: Boolean? = null
)

fun VehicleResponse.toVehicleModel(): Vehicle = Vehicle(vehicleId, vrn, country, color, type, default)