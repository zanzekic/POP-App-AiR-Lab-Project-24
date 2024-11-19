package hr.foi.air.popapp.ws.request_handlers

import com.google.gson.Gson
import hr.foi.air.popapp.core.network.RequestHandler
import hr.foi.air.popapp.core.network.ResponseListener
import hr.foi.air.popapp.core.network.models.ErrorResponseBody
import hr.foi.air.popapp.core.network.models.ResponseBody
import hr.foi.air.popapp.core.network.models.SuccessfulResponseBody
import hr.foi.air.popapp.ws.models.RegistrationBody
import hr.foi.air.popapp.ws.models.RegistrationResponse
import hr.foi.air.popapp.ws.models.responses.RegisteredUser
import hr.foi.air.popapp.ws.network.NetworkService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegistrationRequestHandler(private val requestBody: RegistrationBody) :
    TemplateRequestHandler<RegisteredUser>() {
    override fun getServiceCall(): Call<SuccessfulResponseBody<RegisteredUser>> {
        val service = NetworkService.authService
        return service.registerUser(requestBody)
    }
}