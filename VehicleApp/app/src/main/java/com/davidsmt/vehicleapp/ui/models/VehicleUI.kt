package com.davidsmt.vehicleapp.ui.models

import android.os.Parcelable
import com.davidsmt.domain.Vehicle
import kotlinx.android.parcel.Parcelize

/**
 * Created by David SMT on 18/01/2019.
 *
 * NOTE: This class can be ignored and only use domain object, but depends on
 * the complexity of the domain object, having UI models can avoid undesired
 * scenarios for having fields in presentation layer that are not necessary.
 */
@Parcelize
data class VehicleUI(
    val vehicleId: String? = null,
    val vrn: String? = null,
    val country: String? = null,
    val color: String? = null,
    val type: String? = null,
    val defaultValue: String? = null
) : Parcelable

fun Vehicle.toVehicleUIModel(): VehicleUI {
    val vehicleIdText: String = if (vehicleId == null) "" else vehicleId.toString()
    val defaultText: String = if (default == null) "" else default.toString()

    return VehicleUI(
        vehicleIdText,
        vrn,
        country,
        color,
        type,
        defaultText
    )
}