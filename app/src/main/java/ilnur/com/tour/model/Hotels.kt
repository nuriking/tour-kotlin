package ilnur.com.tour.model

import com.google.gson.annotations.SerializedName

data class Hotels(@SerializedName("hotels") val hotels: List<Hotel>)