package com.davidsmt.vehicleapp.vehicledetails

import com.davidsmt.vehicleapp.ui.vehicledetails.VehicleDetailsPresenter
import com.davidsmt.vehicleapp.ui.vehicledetails.VehicleDetailsView
import com.davidsmt.vehicleapp.ui.models.VehicleUI
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

/**
 * Created by David SMT on 21/01/2019.
 */
@RunWith(MockitoJUnitRunner::class)
class VehicleDetailsPresenterTest {

    @Mock
    private lateinit var view: VehicleDetailsView

    @Mock
    private lateinit var vehicleUI: VehicleUI

    private lateinit var presenter: VehicleDetailsPresenter

    @Before
    fun setUp() {
        presenter = VehicleDetailsPresenter(view, vehicleUI)
    }

    @Test
    fun onAttachView_showDetails() {
        presenter.attachView(view)

        verify(view).showDetails(vehicleUI)
    }

}