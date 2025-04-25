package com.example.coinapp.services

import com.example.coinapp.models.AssetsResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import javax.inject.Inject

class CoinCapApiService @Inject constructor(
    private val client: HttpClient

) {
    suspend fun getAssets(): AssetsResponse {
        val response: HttpResponse = client.get("https://rest.coincap.io/v3/assets?apiKey=6b485c584d692e1a35d6025d8fae446d0458f1ff80c1722ab2173b6f9ae99cea")
        return response.body()
    }
}