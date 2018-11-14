package ilnur.com.tour.ui.main

import android.annotation.SuppressLint
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ilnur.com.tour.R
import ilnur.com.tour.model.Hotel
import kotlinx.android.synthetic.main.item.view.*

class MainAdapter(val action: onCurrentItemAction, var hotels: List<Hotel>) :
    RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    fun setData(hotels: List<Hotel>) {
        this.hotels = hotels
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return hotels.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false))
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.hotelTitleTextView.text = "Hotel \"".plus(hotels[position].name).plus("\"")
        holder.countVariantAirlineTextView.text = hotels[position].flights.size.toString()
        holder.minCostTextView.text = hotels[position].price.toString()
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {
        private val rootView = view.rootView!!
        val hotelTitleTextView = view.hotelTitleTextView!!
        val countVariantAirlineTextView = view.countVariantAirlineTextView!!
        val minCostTextView = view.minCostTextView!!

        init {
            this.rootView.setOnClickListener(this)
        }

        override fun onClick(view: View) {
            val position = layoutPosition
            val hotel = hotels[position]
            action.onItemClick(hotel)
        }
    }

    interface onCurrentItemAction {
        fun onItemClick(hotel: Hotel)
    }
}