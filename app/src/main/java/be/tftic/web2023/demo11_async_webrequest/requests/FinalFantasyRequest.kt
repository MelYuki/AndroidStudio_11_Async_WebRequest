package be.tftic.web2023.demo11_async_webrequest.requests

import android.util.Log
import be.tftic.web2023.demo11_async_webrequest.models.FinalFantasyCharacter
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.HttpURLConnection
import java.net.URL

class FinalFantasyRequest() {

    suspend fun searchRandomCharacter(): FinalFantasyCharacter {
        return withContext(Dispatchers.IO) {

            val url = URL("https://www.moogleapi.com/api/v1/characters/random")

            val connection = url.openConnection() as? HttpURLConnection

            connection?.run {
                requestMethod = "GET"
                setRequestProperty("content-type", "application/json; charset=utf-8")
                doInput = true

                val json = inputStream.bufferedReader().lineSequence().joinToString("\n")

                val result = Gson().fromJson(json, FinalFantasyCharacter::class.java)
                return@withContext result
            }

            throw Exception("Connection not open")
        }
    }

}