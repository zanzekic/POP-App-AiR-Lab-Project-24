package hr.foi.air.popapp.core

import org.junit.Assert.assertArrayEquals
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date

class MyFirstUnitTest {

    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun addition_isNotCorrect() {
        assertNotEquals(4, 2 + 5)
    }

    @Test
    fun checkDate() {
        val sdf = SimpleDateFormat("dd/MM/yyyy")
        val currentDate = sdf.format(Date())
        //postavit danasnji datum
        val date = "12/12/2024"
        //assertEquals(currentDate, date);
    }

    private fun isWeekend(date: String): Boolean {
        val sdf = SimpleDateFormat("dd/MM/yyyy")
        val calendar = Calendar.getInstance().apply { time = sdf.parse(date)!! }
        val dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK)
        return dayOfWeek == Calendar.SATURDAY || dayOfWeek == Calendar.SUNDAY
    }

    @Test
    fun isWeekendSaturdayTest() {
        val date = "30/11/2024" // Subota
        assertTrue(isWeekend(date))
    }

    @Test
    fun isWeekendMondayTest() {
        val date = "25/11/2024" // Ponedjeljak
        assertFalse(isWeekend(date))
    }


    @Test
    fun checkList() {
        val list = listOf("a1", "a2")
        assertTrue(list.contains("a1"))
    }

    @Test
    fun checkListSize() {
        val list = listOf("a1", "a2")
        assertEquals(2, list.size)
    }

    @Test
    fun filteringListTest() {
        val list = listOf("a1", "a2", "a3", "a4")
        val result = list.filter { it.startsWith("a1") }
        assertEquals(listOf("a1"), result)
    }

    @Test
    fun checkArray() {
        val arr: IntArray = intArrayOf(10, 20, 30, 40, 50)
        assertArrayEquals(intArrayOf(10, 20, 30, 40, 50), arr)
    }

    //provjera ispravnosti email adrese
    private fun isValidEmail(email: String): Boolean {
        val emailRegex = Regex("^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})")
        return email.matches(emailRegex)
    }

    //provjera je li email ispravan – isValidEmail mora vratiti true
    @Test
    fun validEmail_isCorrect() {
        val validEmail = "test@example.com"
        assertTrue(isValidEmail(validEmail))
    }

    //provjera je li za neispravan email isValidEmail vrati false
    @Test
    fun invalidEmail_isCorrect() {
        val invalidEmail = "invalid.email"
        assertFalse(isValidEmail(invalidEmail))
    }


    //provjera ibana
    private fun isValidIban(iban: String): Boolean {
        val ibanRegex = Regex("^[A-Z]{2}[0-9]{2}[A-Z0-9]{11,30}\$")
        return iban.matches(ibanRegex)
    }

    //provjera je li iban ispravan – isValidIban mora vratiti true
    @Test
    fun validIban_isCorrect() {
        val validIban = "HR9823600005951613832"
        assertTrue(isValidIban(validIban))
    }

    //provjera je li za neispravan iban isValidIban vrati false
    @Test
    fun invalidIban_isCorrect() {
        val invalidIban = "2345"
        assertFalse(isValidIban(invalidIban))
    }


    //izracun popusta na postavljenu cijenu
    private fun calculateDiscount(price: Double, discount: Double): Double {
        require(price > 0) { "Cijena mora biti veća od 0" }
        require(discount in 0.0..100.0) { "Popust mora biti između 0 i 100" }
        return price - (price * discount / 100)
    }

    @Test
    fun calculateDiscountTest() {
        val price = 200.0
        val discount = 10.0
        val result = calculateDiscount(price, discount)
        assertEquals(180.0, result, 0.001)
    }

    @Test(expected = IllegalArgumentException::class)
    fun calculateDiscountNegativePriceExceptionTest() {
        calculateDiscount(-50.0, 10.0)
    }

    @Test(expected = IllegalArgumentException::class)
    fun calculateDiscountInvalidDiscountExceptionTest() {
        calculateDiscount(100.0, 110.0)
    }

}