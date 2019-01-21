package com.davidsmt.domain

data class Vehicle(
    val vehicleId: Long? = null,
    val vrn: String? = null,
    val country: String? = null,
    val color: String? = null,
    val type: String? = null,
    val default: Boolean? = null
)
