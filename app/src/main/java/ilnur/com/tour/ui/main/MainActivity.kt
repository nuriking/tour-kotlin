package ilnur.com.tour.ui.main

import android.app.AlertDialog
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import com.arellomobile.mvp.presenter.InjectPresenter
import ilnur.com.tour.R
import ilnur.com.tour.model.Flight
import ilnur.com.tour.model.Hotel
import ilnur.com.tour.ui.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity(), IMain, HotelAdapter.onCurrentItemAction {
    @InjectPresenter
    lateinit var presenter: MainPresenter

    private var adapter: HotelAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        searchHotelTextInputEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                presenter.textChangedSearch(s)
            }

            override fun afterTextChanged(s: Editable) {}
        })
        clearButton.setOnClickListener { searchHotelTextInputEditText.text!!.clear() }
    }

    override fun onError(errorText: String) {
        Toast.makeText(this, errorText, Toast.LENGTH_LONG).show()
    }

    override fun onShowLoading() {
        progressBar.visibility = View.VISIBLE
    }

    override fun onHideLoading() {
        progressBar.visibility = View.GONE
    }

    override fun fillHotel(hotels: List<Hotel>) {
        if (this.adapter == null) {
            hotelsRecView.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
            adapter = HotelAdapter(this, hotels)
            hotelsRecView.adapter = adapter
        } else {
            adapter!!.setData(hotels)
        }
    }

    override fun fillFlight(flights: List<Flight>, hotel: Hotel) {
        val arrayAdapter = FlightAdapter(this, R.layout.item_flight, flights)
        val builderSingle = AlertDialog.Builder(this)
        builderSingle.setNegativeButton("Отмена") { dialog, _ -> dialog.dismiss() }
            .setTitle("Выберите авиакомпанию")
            .setAdapter(arrayAdapter) { dialog, which ->
                val flight = arrayAdapter.getItem(which)
                val builderInner = AlertDialog.Builder(this)
                builderInner.setMessage(
                    "Отель: ".plus(hotel.name).plus("\n")
                        .plus("Авиакомпания: ".plus(flight!!.airline.name))
                        .plus(", стоимость: ".plus(flight.price.toString().plus("р")))
                )
                    .setTitle("Вы выбрали")
                    .setPositiveButton(
                        "Ок"
                    ) { dialog, _ -> dialog.dismiss() }
                    .show()
            }
            .show()
    }

    override fun onItemClick(hotel: Hotel) {
        presenter.getInfo(hotel)
    }
}
