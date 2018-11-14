package ilnur.com.tour.ui.main

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import ilnur.com.tour.R
import ilnur.com.tour.model.Flight
import android.widget.TextView

class FlightAdapter(context: Context, var flights: List<Flight>) : ArrayAdapter<Flight>(context, R.layout.item_flight) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val flight = getItem(position)
        val tvName = convertView!!.findViewById(R.id.tvName) as TextView
        val tvPrice = convertView.findViewById(R.id.tvPrice) as TextView
        tvName.text = flight!!.airline.name
        tvPrice.text = flight.price.toString()
        return super.getView(position, convertView, parent)
    }

    override fun getItem(position: Int): Flight? {
        return flights[position]
    }
}