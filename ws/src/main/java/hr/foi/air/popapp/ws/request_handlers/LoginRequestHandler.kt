package hr.foi.air.popapp.ws.request_handlers

import com.google.gson.Gson
import hr.foi.air.popapp.core.network.RequestHandler
import hr.foi.air.popapp.core.network.ResponseListener
import hr.foi.air.popapp.core.network.models.ErrorResponseBody
import hr.foi.air.popapp.core.network.models.SuccessfulResponseBody
import hr.foi.air.popapp.ws.models.LoggedInUserData
import hr.foi.air.popapp.ws.models.LoginBody
import hr.foi.air.popapp.ws.network.NetworkService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginRequestHandler(private val requestBody: LoginBody) :
    TemplateRequestHandler<LoggedInUserData>() {
    override fun getServiceCall(): Call<SuccessfulResponseBody<LoggedInUserData>> {
        val service = NetworkService.authService
        return service.loginUser(requestBody)
    }
}