package ilnur.com.tour.app

import dagger.Component
import ilnur.com.tour.network.NetworkModule
import ilnur.com.tour.ui.main.MainPresenter
import javax.inject.Singleton

@Singleton
@Component(modules = [(NetworkModule::class), (AppModule::class)])

interface AppComponent {
    fun inject(app: App)
    fun inject(app: MainPresenter)

}