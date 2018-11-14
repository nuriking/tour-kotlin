package ilnur.com.tour.ui.main

import android.content.Context
import android.support.annotation.LayoutRes
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import ilnur.com.tour.R
import ilnur.com.tour.model.Flight
import android.widget.TextView
import kotlinx.android.synthetic.main.item_flight.view.*

class FlightAdapter(context: Context, @LayoutRes private val layoutResource: Int, var flights: List<Flight>) :
    ArrayAdapter<Flight>(context, layoutResource, flights) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var holder: ViewHolder
        var retView: View
        val flight = getItem(position)
        if (convertView == null) {
            retView = LayoutInflater.from(parent.context).inflate(layoutResource, parent, false)
            holder = ViewHolder(retView)
            holder.airlineNameTextView.text = flight!!.airline.name
            holder.airlinePriceTextView.text = flight.price.toString()
            retView.tag = holder
        } else {
            holder = convertView.tag as ViewHolder
            retView = convertView
        }
        return retView
    }

    override fun getItem(position: Int): Flight? {
        return flights[position]
    }

    internal class ViewHolder(view: View) {
        var airlineNameTextView: TextView = view.tvName
        var airlinePriceTextView: TextView= view.tvPrice
    }
}