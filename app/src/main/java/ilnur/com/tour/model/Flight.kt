package ilnur.com.tour.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Flight(
        @SerializedName("id") val id: Int,
        @SerializedName("companyId") val companyId: Int,
        @SerializedName("departure") val departure: String,
        @SerializedName("arrival") val arrival: String,
        @SerializedName("price") val price: Int
){
    lateinit var airline: Airline
}

