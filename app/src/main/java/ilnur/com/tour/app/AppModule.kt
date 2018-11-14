package ilnur.com.tour.app

import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
class AppModule(val app: App) {

    @Named("BaseUrl")
    @Provides
    fun provideBaseUrl(): String {
        return app.getUrl()
    }
}