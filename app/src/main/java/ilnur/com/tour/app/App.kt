package ilnur.com.tour.app

import android.os.Handler
import android.os.Looper
import android.support.multidex.MultiDexApplication
import ilnur.com.tour.Constant
import ilnur.com.tour.network.NetworkModule
import ilnur.com.tour.network.interseptor.NetworkNotAvailableInterceptor
import ilnur.com.tour.ui.BaseActivity

class App : MultiDexApplication(), NetworkNotAvailableInterceptor.IOnNetworkIsNotAvailableFireListener {

    companion object {
        private lateinit var instance: App
        private lateinit var appComponent: AppComponent
        private lateinit var currentActivity: BaseActivity
    }

    init {
        instance = this

        appComponent = DaggerAppComponent
            .builder()
            .appModule(AppModule(this))
            .networkModule(NetworkModule(getUrl(), this))
            .build()
        appComponent.inject(this)
    }

    fun get(): App {
        return instance
    }

    fun getAppComponent(): AppComponent {
        return appComponent
    }

    fun setCurrentActivity(activity: BaseActivity) {
        currentActivity = activity
    }

    fun getUrl(): String {
        return Constant.API_ROOT
    }

    override fun call(e: Throwable) {
        val handler = Handler(Looper.getMainLooper()) { message ->
            if (currentActivity != null) {
                currentActivity.showNetworkFailureModal()
            }
            false
        }
        val message = handler.obtainMessage()
        message.sendToTarget()
    }

}