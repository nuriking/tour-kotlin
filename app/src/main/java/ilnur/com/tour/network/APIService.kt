package ilnur.com.tour.network

import ilnur.com.tour.model.Airlines
import ilnur.com.tour.model.Flights
import ilnur.com.tour.model.Hotels
import io.reactivex.Single
import retrofit2.http.*

interface APIService {
    @GET("bins/12q3ws")
    fun getHotelList():Single<Hotels>
    @GET("bins/zqxvw")
    fun getFlightList():Single<Flights>
    @GET("bins/8d024")
    fun getAirlineList():Single<Airlines>
}