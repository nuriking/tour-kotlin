package ilnur.com.tour.ui.main

import android.app.AlertDialog
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import com.arellomobile.mvp.presenter.InjectPresenter
import ilnur.com.tour.R
import ilnur.com.tour.model.Flight
import ilnur.com.tour.model.Hotel
import ilnur.com.tour.ui.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity(), IMain, MainAdapter.onCurrentItemAction {
    @InjectPresenter
    lateinit var presenter: MainPresenter

    var adapter: MainAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onError(errorText: String) {
        Toast.makeText(this, errorText, Toast.LENGTH_LONG).show()
    }

    override fun onShowLoading() {
        progressBar.visibility = View.INVISIBLE
    }

    override fun onHideLoading() {
        progressBar.visibility = View.GONE
    }

    override fun fillHotel(hotels: List<Hotel>) {
        if (this.adapter == null) {
            hotelsRecView.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
            adapter = MainAdapter(this, hotels)
            hotelsRecView.adapter = adapter
        } else {
            adapter!!.setData(hotels)
        }
    }

    override fun fillFlight(flights: List<Flight>) {
        val builderSingle = AlertDialog.Builder(this)
        builderSingle.setNegativeButton("cancel") { dialog, _ -> dialog.dismiss() }

        val arrayAdapter = FlightAdapter(this, R.layout.item_flight, flights)

        builderSingle.setAdapter(arrayAdapter) { dialog, which ->
            val flight = arrayAdapter.getItem(which)
            val builderInner = AlertDialog.Builder(this)
            builderInner.setMessage(flight!!.airline.name)
            builderInner.setTitle("Your Selected Item is")
            builderInner.setPositiveButton(
                "Ok"
            ) { dialog, _ -> dialog.dismiss() }
            builderInner.show()
        }
        builderSingle.show()
    }

    override fun onItemClick(hotel: Hotel) {
        presenter.getInfo(hotel)
    }
}
