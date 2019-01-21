package com.davidsmt.vehicleapp.ui.general

/**
 * Created by David SMT on 18/01/2019.
 */
open class BasePresenter<V : BaseView>(
    var view: V?
) {
    open fun attachView(view: V) {
        this.view = view
    }

    fun detachView() {
        this.view = null
    }

}