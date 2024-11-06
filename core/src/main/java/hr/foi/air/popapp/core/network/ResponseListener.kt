package hr.foi.air.popapp.core.network

import hr.foi.air.popapp.core.network.models.ErrorResponseBody
import hr.foi.air.popapp.core.network.models.SuccessfulResponseBody

interface ResponseListener {
    fun<T> onSuccessfulResponse(response: SuccessfulResponseBody<T>)
    fun onErrorResponse(response: ErrorResponseBody)
    fun onNetworkFailure(t: Throwable)
}