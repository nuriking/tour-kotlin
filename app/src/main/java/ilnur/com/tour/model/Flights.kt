package ilnur.com.tour.model

import com.google.gson.annotations.SerializedName

data class Flights (@SerializedName("flights") val flights: List<Flight>)