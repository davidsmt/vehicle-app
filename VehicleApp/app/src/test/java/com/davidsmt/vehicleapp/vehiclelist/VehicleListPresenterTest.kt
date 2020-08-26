package com.davidsmt.vehicleapp.vehiclelist

import com.davidsmt.domain.ResourceException
import com.davidsmt.domain.Vehicle
import com.davidsmt.usecases.GetVehicles
import com.davidsmt.vehicleapp.ui.vehiclelistmvp.VehicleListPresenter
import com.davidsmt.vehicleapp.ui.vehiclelistmvp.VehicleListView
import com.davidsmt.vehicleapp.ui.models.VehicleUI
import com.davidsmt.vehicleapp.ui.models.toVehicleUIModel
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.anyOrNull
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.spy
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

/**
 * Created by David SMT on 20/01/2019.
 */
@RunWith(MockitoJUnitRunner::class)
class VehicleListPresenterTest {

    @Mock
    private lateinit var view: VehicleListView

    @Mock
    private lateinit var getVehicles: GetVehicles

    private lateinit var presenter: VehicleListPresenter
    private lateinit var presenterSpy: VehicleListPresenter

    @Before
    fun setUp() {
        presenter = VehicleListPresenter(view, getVehicles)
        presenterSpy = spy(presenter)
    }

    @Test
    fun onAttachView_loadVehicles() {
        presenterSpy.attachView(view)

        verify(presenterSpy).loadVehicles()
    }

    @Suppress("UNCHECKED_CAST")
    @Test
    fun loadVehicles_showVehicles() {
        // given
        val vehicle = Vehicle(
            vehicleId = 0,
            vrn = "vrn",
            country = "country",
            color = "color",
            type = "type",
            default = true
        )
        val vehicles = listOf(vehicle);
        val vehiclesUI = listOf(vehicle.toVehicleUIModel());

        whenever(getVehicles.execute(anyOrNull(), any(), any())).thenAnswer { invocation ->
            val callback = invocation.arguments[USE_CASE_ON_SUCCESS_ARGUMENT] as (List<Vehicle>) -> Unit
            callback.invoke(vehicles)
        }

        // when
        presenter.loadVehicles()

        // then
        verify(view).showVehicles(vehiclesUI)
    }

    @Suppress("UNCHECKED_CAST")
    @Test
    fun loadVehicles_showEmptyListError() {
        // given
        val resourceException: ResourceException = ResourceException.NullOrEmptyResource("")

        whenever(getVehicles.execute(anyOrNull(), any(), any())).thenAnswer { invocation ->
            val callback = invocation.arguments[USE_CASE_ON_ERROR_ARGUMENT] as (ResourceException?) -> Unit
            callback.invoke(resourceException)
        }

        // when
        presenter.loadVehicles()

        // then
        verify(view).showEmptyListError()
    }

    @Suppress("UNCHECKED_CAST")
    @Test
    fun loadVehicles_showGeneralError() {
        // given
        val resourceException: ResourceException = ResourceException.RemoteResponseError("")

        whenever(getVehicles.execute(anyOrNull(), any(), any())).thenAnswer { invocation ->
            val callback = invocation.arguments[USE_CASE_ON_ERROR_ARGUMENT] as (ResourceException?) -> Unit
            callback.invoke(resourceException)
        }

        // when
        presenter.loadVehicles()

        // then
        verify(view).showGeneralError()
    }

    @Test
    fun onVehicleItemClicked_showVehicleDetailsScreen() {
        // given
        val vehicle = VehicleUI()

        // when
        presenter.onVehicleItemClicked(vehicle)

        // then
        verify(view).showVehicleDetailsScreen(vehicle)
    }

    @Test
    fun onRetryButtonPressed_loadVehicles() {
        // when
        presenterSpy.onRetryButtonPressed()

        // then
        verify(presenterSpy).loadVehicles()
    }

    companion object {
        private const val USE_CASE_ON_SUCCESS_ARGUMENT = 1
        private const val USE_CASE_ON_ERROR_ARGUMENT = 2
    }

}