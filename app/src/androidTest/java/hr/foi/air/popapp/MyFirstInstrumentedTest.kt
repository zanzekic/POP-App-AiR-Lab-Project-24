package hr.foi.air.popapp

import androidx.test.ext.junit.runners.AndroidJUnit4
import hr.foi.air.popapp.ws.models.responses.Product
import org.json.JSONObject
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class MyFirstInstrumentedTest {

    private fun parseProduct (json: String): Product {
        val jsonObject = JSONObject(json)
        return Product(
            id = jsonObject.getInt("id"),
            name = jsonObject.getString("name")
        )
    }

    @Test
    fun testParseProduct () {
        val json = """{"id": 1, "name": "Product 1"}"""
        val product = parseProduct(json)
        assertEquals(1, product.id)
        assertEquals("Product 1", product.name)
    }


}