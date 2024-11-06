package hr.foi.air.popapp.core.network

interface RequestHandler {
    fun sendRequest(responseListener: ResponseListener)
}