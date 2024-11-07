package hr.foi.air.popapp.ws.request_handlers

import com.google.gson.Gson
import hr.foi.air.popapp.core.network.RequestHandler
import hr.foi.air.popapp.core.network.ResponseListener
import hr.foi.air.popapp.core.network.models.ErrorResponseBody
import hr.foi.air.popapp.core.network.models.SuccessfulResponseBody
import hr.foi.air.popapp.ws.models.responses.Product
import hr.foi.air.popapp.ws.network.NetworkService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductsRequestHandler(private val jwt: String) : RequestHandler {
    override fun sendRequest(responseListener: ResponseListener) {
        val service = NetworkService.productsService
        val serviceCall = service.getProducts("Bearer $jwt")

        serviceCall.enqueue(object : Callback<SuccessfulResponseBody<Product>> {
            override fun onResponse(
                call: Call<SuccessfulResponseBody<Product>>,
                response: Response<SuccessfulResponseBody<Product>>
            ) {
                if (response.isSuccessful) {
                    val body = response.body() as SuccessfulResponseBody<Product>
                    responseListener.onSuccessfulResponse(body)
                } else {
                    val errorResponse = Gson().fromJson(response.errorBody()?.string(), ErrorResponseBody::class.java)
                    responseListener.onErrorResponse(errorResponse)
                }
            }

            override fun onFailure(call: Call<SuccessfulResponseBody<Product>>, t: Throwable) {
                responseListener.onNetworkFailure(t)
            }
        })
    }
}