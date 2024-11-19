package hr.foi.air.popapp.navigation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import hr.foi.air.popapp.context.Auth
import hr.foi.air.popapp.ui.components.MenuItem
import hr.foi.air.popapp.ui.theme.POPAppTheme
import java.util.*

@Composable
fun HomePage(onMenuOptionSelected: (optionName: String) -> Unit) {
    val menuItems = listOf(
        "Products" to Icons.Default.ShoppingCart,
        "Balance" to Icons.Default.Star,
        "Invoices" to Icons.AutoMirrored.Filled.List,
    )

    Column(modifier = Modifier.background(MaterialTheme.colorScheme.background)) {
        Text(
            text = "Welcome, ${Auth.loggedInUserData?.firstName ?: "[Unknown name]"}",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.fillMaxWidth(),
            color = MaterialTheme.colorScheme.inversePrimary,
            textAlign = TextAlign.Center
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(20.dp)
        ) {
            items(menuItems) { (text, icon) ->
                MenuItem(text, icon) { onMenuOptionSelected(text.lowercase(Locale.getDefault())) }
            }
        }
    }
}

@Preview
@Composable
fun HomePagePreview() {
    POPAppTheme {
        HomePage({})
    }
}
