package hr.foi.air.popapp.ws.request_handlers

import com.google.gson.Gson
import hr.foi.air.popapp.core.network.RequestHandler
import hr.foi.air.popapp.core.network.ResponseListener
import hr.foi.air.popapp.core.network.models.ErrorResponseBody
import hr.foi.air.popapp.core.network.models.ResponseBody
import hr.foi.air.popapp.core.network.models.SuccessfulResponseBody
import hr.foi.air.popapp.ws.models.RegistrationBody
import hr.foi.air.popapp.ws.models.RegistrationResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegistrationRequestHandler(private val requestBody: RegistrationBody) : RequestHandler {
    override fun sendRequest(responseListener: ResponseListener) {
        val service = hr.foi.air.popapp.ws.network.NetworkService.authService
        val serviceCall = service.registerUser(requestBody)

        serviceCall.enqueue(object : Callback<SuccessfulResponseBody<RegistrationResponse>> {
            override fun onResponse(call: Call<SuccessfulResponseBody<RegistrationResponse>>, response: Response<SuccessfulResponseBody<RegistrationResponse>>) {

                if (response.isSuccessful) {
                    responseListener.onSuccessfulResponse(response.body() as SuccessfulResponseBody<*>)
                } else {
                    val errorResponse = Gson().fromJson(response.errorBody()?.string(), ErrorResponseBody::class.java)
                    responseListener.onErrorResponse(errorResponse)
                }

            }

            override fun onFailure(call: Call<SuccessfulResponseBody<RegistrationResponse>>, t: Throwable) {
                responseListener.onNetworkFailure(t)
            }
        })
    }
}