package ilnur.com.tour.ui

import android.support.v7.app.AlertDialog
import com.arellomobile.mvp.MvpAppCompatActivity
import ilnur.com.tour.app.App

open class BaseActivity : MvpAppCompatActivity(), IBaseActivity {

    override fun onResume() {
        super.onResume()
        App().get().setCurrentActivity(this)
    }

    fun showNetworkFailureModal() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Ошибка")
        builder.setMessage("Проверьте соединение с интернетом")

        builder.setCancelable(false)
        builder.setNegativeButton("Закрыть") { dialog, id ->
            run {
                dialog.dismiss()
                finish()
            }
        }

        val alert = builder.create()
        alert.show()
    }
}