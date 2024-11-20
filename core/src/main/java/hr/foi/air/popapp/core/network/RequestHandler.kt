package hr.foi.air.popapp.core.network

interface RequestHandler<T> {
    fun sendRequest(responseListener: ResponseListener<T>)
}