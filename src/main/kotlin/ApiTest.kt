package org.example

import com.example.Services.CarValidationService
import com.example.repositories.CarRepository
import io.ktor.client.plugins.websocket.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.server.testing.*
import io.ktor.client.statement.*
import io.ktor.websocket.*
import kotlinx.serialization.json.*
import io.mockk.*
import org.junit.jupiter.api.Nested
import org.junit.Before
import org.junit.experimental.runners.Enclosed
import org.junit.jupiter.api.BeforeEach
import org.junit.runner.RunWith
import kotlin.test.*

@RunWith(Enclosed::class)
class ApiTest {

    @Test
    fun testGetPizzaMenu() = testApplication {
        application {
            module()
        }

        val response = client.get("/pizza_menu")
        assertEquals(HttpStatusCode.OK, response.status)
        val json = response.bodyAsText()
        val pizzas = Json.decodeFromString<List<Pizza>>(json)

        assertAll(
            { assertTrue(pizzas.isNotEmpty(), "Пиццы должны быть не пустыми") },
            // Пример проверки содержимого
            { assertTrue(pizzas.any { it.name == "Margherita" }, "Должна быть пицца Margherita") }
        )
    }

    @Test
    fun testGetPizzaMenuPizzaWithdrawal() = testApplication {
        application {
            module()
        }
        val test_name = "pizza_with_mushrooms"
        val response = client.get("/pizza_menu/$test_name")
        assertEquals(HttpStatusCode.OK, response.status)
        val json = response.bodyAsText()
        val pizzas = Json.decodeFromString<List<Pizza>>(json)

        assertAll(
            { assertTrue(pizzas.isNotEmpty(), "Пиццы должны быть не пустыми") },
            { assertTrue(pizzas.any { it.name != test_name }, "Должна быть пицца с названием $test_name") }
        )
    }

}