package ilnur.com.tour.network.interseptor

import okhttp3.Interceptor
import okhttp3.Response
import retrofit2.HttpException
import java.io.IOException
import java.util.*

class NetworkNotAvailableInterceptor() : BaseInterceptor() {
    companion object {
        val networkNotAvailableCodeList = Arrays.asList<Int>()
    }

    private lateinit var callback: IOnNetworkIsNotAvailableFireListener

    constructor(callback: IOnNetworkIsNotAvailableFireListener) : this() {
        this.callback = callback
    }

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        try {
            return super.intercept(chain)
        } catch (e: IOException) {
            if (e.message!!.contains("api.myjson.com")) {
                onError(e)
            }
            throw e
        } catch (e: Exception) {
            onError(e)
            throw e
        }

    }

    private fun onError(e: Throwable) {
        callback.call(e)
    }

    override fun onError(response: Response): Response {
        if (networkNotAvailableCodeList.contains(response.code())) {
            onError(HttpException(retrofit2.Response.error<Any>(response.code(), response.body())))
        }
        return response
    }

    interface IOnNetworkIsNotAvailableFireListener {
        fun call(e: Throwable)
    }
}