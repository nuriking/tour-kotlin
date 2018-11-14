package ilnur.com.tour.ui.main

import ilnur.com.tour.model.Flight
import ilnur.com.tour.model.Hotel
import ilnur.com.tour.ui.IBaseView

interface IMain : IBaseView {
    fun fillHotel(hotels: List<Hotel>)

    fun fillFlight(flights: List<Flight>)
}