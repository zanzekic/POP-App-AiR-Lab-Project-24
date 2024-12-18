package hr.foi.air.popapp

import androidx.compose.ui.test.assertAll
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.onSiblings
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.test.printToLog
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class UITest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun loginFailure() {
        composeTestRule.onNodeWithText("Login").performClick()

        val invalidUsername = "invalidUsername"
        val invalidPassword = "invalidPassword"

        //unijeti neispravno kor ime i lozinku
        composeTestRule.onNodeWithText("Username").performTextInput(invalidUsername)
        composeTestRule.onNodeWithText("Password").performTextInput(invalidPassword)

        //kliknuti na login
        composeTestRule.onNodeWithText("Login").performClick()

        //provjerit je li se ispise poruka da su podaci neispravni
        composeTestRule.onNodeWithTag("errorMessage").onSiblings()
            .assertAll(hasText("Please check your credentials!"))
    }

    @Test
    fun loginSuccess() {

        //kliknuti na gumb za prijavu
        composeTestRule.onNodeWithText("Login").performClick()

        //ispravno korisničko ime i lozinku
        val validUsername = "cvelasquez"
        val validPassword = "test123"

        //unijeti ispravno korisnicko ime i lozinku
        composeTestRule.onNodeWithText("Username").performTextInput(validUsername)
        composeTestRule.onNodeWithText("Password").performTextInput(validPassword)

        //kliknuti na prijavi se
        composeTestRule.onNodeWithText("Login").performClick()

        //pricekati da se prikaze intro element
        composeTestRule.waitUntil {
            composeTestRule.onAllNodesWithTag("intro").fetchSemanticsNodes().size == 1
        }

        //provjeriti prikaze li se na ekranu poruka o uspjesnoj prijavi
        composeTestRule.onRoot().printToLog("TAG")

        //provjeriti je li se prikazuje poruka dobrodoslice
        composeTestRule.onNodeWithTag("intro").onSiblings()
            .assertAll(hasText(("Welcome, Catherine")))

    }

    @Test
    fun registrationTest() {

        //kliknuti na gumb za registraciju
        composeTestRule.onNodeWithText("Register").performClick()

        //unijeti podatke za registraciju
        val firstName = "Zana"
        val lastName = "Zekic"
        val userName = "zzekic"
        val email = "zana.zekic@foi.hr"
        val password = "test123"

        //podaci za registraciju
        composeTestRule.onNodeWithText("First name").performTextInput(firstName)
        composeTestRule.onNodeWithText("Last name").performTextInput(lastName)
        composeTestRule.onNodeWithText("Username").performTextInput(userName)
        composeTestRule.onNodeWithText("Email").performTextInput(email)
        composeTestRule.onNodeWithText("Password").performTextInput(password)
        composeTestRule.onNodeWithText("Confirm password").performTextInput(password)

        //kliknuti na registriraj se
        composeTestRule.onNodeWithText("Register").performClick()

    }

}