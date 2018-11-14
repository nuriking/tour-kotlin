package ilnur.com.tour

class Constant {
    companion object {
        const val API_ROOT = "https://api.myjson.com/"

        val SECURITY_PREFERENCES = "security_preferences"
        val TOKEN = "token"
        val CITY = "city"

        const val RESULT_CLOSER_ACTIVITY = 0
        const val RESULT_AUTHORIZATION = 1
        const val RESULT_FAVORITES = 2
        const val RESULT_PROFILE = 3
        const val RESULT_CITY = 4
        const val RESULT_CREATE_VEHICLE = 5
        const val RESULT_ADD_ROUTE = 6

        val PROVIDE_DISTANCE:Float = 0.0f
        val PROVIDE_TIME:Long = 1000
        val ACCESS_ON_THE_ROUTE = 1
        private val MAX_SPEED = 30//(m/s)
        val DISTANCE_ERROR = MAX_SPEED * PROVIDE_TIME / 1000
    }
}