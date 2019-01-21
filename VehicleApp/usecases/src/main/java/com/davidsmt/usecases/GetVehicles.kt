package com.davidsmt.usecases

import com.davidsmt.data.vehicle.VehicleRepository
import com.davidsmt.domain.ResourceException
import com.davidsmt.domain.ResourceStatus
import com.davidsmt.domain.Vehicle
import com.davidsmt.usecases.general.BaseUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class GetVehicles(
    private val vehicleRepository: VehicleRepository
) : BaseUseCase<List<Vehicle>, Void>() {

    override fun execute(params: Void?, onSuccess: (List<Vehicle>) -> Unit, onError: (ResourceException?) -> Unit) {
        // This coroutine scope can be injected, and linked to activity lifecycle, if it is needed
        CoroutineScope(Dispatchers.Main).launch {
            val resource = withContext(Dispatchers.IO) { vehicleRepository.getVehicles() }
            when (resource.state) {
                ResourceStatus.SUCCESS -> {
                    val list = resource.value
                    when {
                        list.isNullOrEmpty() -> onError(ResourceException.NullOrEmptyResource("Null or empty list"))
                        else -> onSuccess(list)
                    }
                }
                ResourceStatus.ERROR -> {
                    onError(resource.error)
                }
            }
        }
    }

}
