package ilnur.com.tour.ui.main

import ilnur.com.tour.model.Airlines
import ilnur.com.tour.model.Flight
import ilnur.com.tour.model.Flights
import ilnur.com.tour.model.Hotels
import ilnur.com.tour.network.APIManager
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MainModel @Inject constructor(var apiManager: APIManager) {

    fun getFlightList(): Single<Flights> {
        return this.apiManager.getFlightList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun getHotelList(): Single<Hotels> {
        return this.apiManager.getHotelList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun getAirlineList(): Single<Airlines> {
        return this.apiManager.getAirlineList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}