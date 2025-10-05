package org.example

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.application.ApplicationCallPipeline.ApplicationPhase.Plugins
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun main() {
    embeddedServer(Netty, port = 8080, module = Application::module).start(wait = true)
}

fun Application.module() {
    io.ktor.server.engine.embeddedServer(Netty, port = 8080) {
        // Middleware для логирования
        intercept(Plugins) {
            val method = call.request.httpMethod.value
            val path = call.request.uri
            println("Request: $method $path")
        }

        routing {
            val pizza = mutableListOf<Pizza>(
                Pizza("pizza_with_mushrooms","mushrooms",110),
                Pizza("pizza_with_two_cheeses","cheese",150),
                Pizza("pizza_with_vegetables","vegetables",100)
            )


            get("/pizza_menu"){
                call.respond(pizza)
            }
            get("/pizza_menu/{name}") {
                val nameParam = call.parameters["name"]
                val name = nameParam?.toIntOrNull()
                if (name == null) {
                    return@get call.respond(HttpStatusCode.BadRequest, mapOf("error" to "Invalid id parameter"))
                }

                val pizza_one = pizza.find { it.name.equals(name) }
                if (pizza_one == null) {
                    return@get call.respond(HttpStatusCode.NotFound, mapOf("error" to "Item not found"))
                }
                call.respond(pizza_one)

            }

            post("/adding_pizza"){
                val newPizza = try {
                    call.receive<Pizza>()
                } catch(e: Exception) {
                    return@post call.respond(HttpStatusCode.BadRequest, mapOf("error" to "Invalid JSON"))
                }
                if (pizza.any { it.name == newPizza.name }) {

                    return@post call.respond(HttpStatusCode.Conflict, mapOf("error" to "Item with this id already exists"))
                }
                pizza.add(newPizza)
                call.respond(HttpStatusCode.Created, newPizza)
            }

            delete("/pizza_removal"){
                val nameParam = call.request.queryParameters["name"]
                val name = nameParam?.toIntOrNull()
                if (name == null) {
                    return@delete call.respond(HttpStatusCode.BadRequest, mapOf("error" to "Missing or invalid id query parameter"))
                }
                val removed = pizza.removeIf { it.name.equals(name) }
                if (!removed) {
                    return@delete call.respond(HttpStatusCode.NotFound, mapOf("error" to "Item not found"))
                }
                call.respond(HttpStatusCode.NoContent)
            }

        }

        //обработка ошибок
        install(StatusPages){
            exception<Throwable> { call, cause ->
                call.respondText(text = "500: $cause" , status = HttpStatusCode.InternalServerError)
            }
            status(HttpStatusCode.NotFound) { call, status ->
                call.respondText(text = "404: Page Not Found", status = status)
            }
        }
    }.start(wait = true)
}