package com.davidsmt.usecases.general

import com.davidsmt.domain.ResourceException

/**
 * Created by David SMT on 19/01/2019.
 */
abstract class BaseUseCase<ResultType, ParameterType> {
    abstract fun execute(
        params: ParameterType? = null,
        onSuccess: (ResultType) -> Unit,
        onError: (ResourceException?) -> Unit
    )
}