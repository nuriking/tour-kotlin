package ilnur.com.tour.ui.main

import android.annotation.SuppressLint
import com.arellomobile.mvp.InjectViewState
import ilnur.com.tour.app.App
import ilnur.com.tour.model.*
import ilnur.com.tour.ui.BasePresenter
import javax.inject.Inject

@InjectViewState
class MainPresenter : BasePresenter<IMain>() {

    @Inject
    lateinit var model: MainModel

    private lateinit var flights: List<Flight>
    private lateinit var hotels: List<Hotel>
    private lateinit var airlines: List<Airline>

    init {
        App().getAppComponent().inject(this)
    }

    override fun attachView(view: IMain?) {
        super.attachView(view)
        getHotelList()
    }

    @SuppressLint("CheckResult")
    fun getHotelList() {
        model.getHotelList()
            .doOnSubscribe { getView().onShowLoading() }
            .doAfterSuccess {
                getFlightList()
                getAirlineList()
            }
            .doFinally { getView().onHideLoading() }
            .subscribe(this::onSuccessLoadHotelList, this::onError)
    }

    private fun onSuccessLoadHotelList(hotels: Hotels) {
        this.hotels = hotels.hotels
        getView().fillHotel(this.hotels)
    }

    @SuppressLint("CheckResult")
    fun getFlightList() {
        model.getFlightList()
            .doOnSubscribe { getView().onShowLoading() }
            .doFinally { getView().onHideLoading() }
            .subscribe(this::onSuccessLoadFlightList, this::onError)
    }

    private fun onSuccessLoadFlightList(flights: Flights) {
        this.flights = flights.flights
    }

    @SuppressLint("CheckResult")
    fun getAirlineList() {
        model.getAirlineList()
            .doOnSubscribe { getView().onShowLoading() }
            .doFinally { getView().onHideLoading() }
            .subscribe(this::onSuccessLoadAirlineList, this::onError)
    }

    private fun onSuccessLoadAirlineList(airlines: Airlines) {
        this.airlines = airlines.companies
    }

    private fun onError(throwable: Throwable) {
        getView().onError(throwable.message.toString())
    }

    fun getInfo(hotel: Hotel) {
        val needFlights: ArrayList<Flight> = ArrayList()

        for (hotelFlight in hotel.flights) {
            for (flight in this.flights) {
                if (hotelFlight == flight.id) {
                    needFlights.add(flight)
                }
            }
        }
        for (airLine in this.airlines) {
            for (needFlight in needFlights) {
                if (airLine.id == needFlight.companyId) {
                    needFlight.airline = airLine
                }
            }
        }
        getView().fillFlight(needFlights, hotel)
    }

    fun textChangedSearch(text: CharSequence) {
        val newHotels = ArrayList<Hotel>()
        for (hotel in this.hotels) {
            if (hotel.name.toLowerCase().contains(text.toString().toLowerCase())) {
                newHotels.add(hotel)
            }
        }
        getView().fillHotel(newHotels)
    }
}