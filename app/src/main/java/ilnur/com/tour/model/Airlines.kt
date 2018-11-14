package ilnur.com.tour.model

import com.google.gson.annotations.SerializedName

data class Airlines(@SerializedName("companies") val companies: List<Airline>)