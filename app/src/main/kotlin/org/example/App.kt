package org.example

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

suspend fun main() {
    val client = HttpClient(CIO) {
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
            })
        }
    }

    try {
        val post: Post = client.get("https://jsonplaceholder.typicode.com/posts/1").body()
        println("Successfully fetched post:")
        println(post)
    } catch (e: Exception) {
        println("Error fetching post: ${e.message}")
    } finally {
        client.close()
    }
}
