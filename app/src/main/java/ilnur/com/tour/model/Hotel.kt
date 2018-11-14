package ilnur.com.tour.model

import com.google.gson.annotations.SerializedName

data class Hotel(
    @SerializedName("id") val id: Int,
    @SerializedName("flights") val flights: List<Int>,
    @SerializedName("name") val name: String,
    @SerializedName("price") val price: Double
)