package be.tftic.web2023.demo11_async_webrequest.requests

import be.tftic.web2023.demo11_async_webrequest.models.FinalFantasyCharacter
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.HttpURLConnection
import java.net.URL

class FinalFantasyRequest2(val onRequestResultListener : OnRequestResultListener) {

    // region Listener
    interface OnRequestResultListener {
        fun onRequestSuccessResult(character: FinalFantasyCharacter)
        fun onRequestErrorResult(message: String)
    }

    // Le listener est initialiser depuis le constructeur primaire
    // -> Il n'est pas necessaire d'avoir un mutateur (Setter)

    // endregion

    suspend fun searchRandomCharacter(): Unit {
        withContext(Dispatchers.IO) {

            val url = URL("https://www.moogleapi.com/api/v1/characters/random")

            val connection = url.openConnection() as? HttpURLConnection

            connection?.run {
                requestMethod = "GET"
                setRequestProperty("content-type", "application/json; charset=utf-8")
                doInput = true

                try {
                    val json = inputStream.bufferedReader().lineSequence().joinToString("\n")

                    val result = Gson().fromJson(json, FinalFantasyCharacter::class.java)

                    withContext(Dispatchers.Main) { onRequestResultListener.onRequestSuccessResult(result) }
                }
                catch (error : Exception) {
                    withContext(Dispatchers.Main) { onRequestResultListener.onRequestErrorResult(error.message ?: "Unknown Exception") }
                }
                return@run
            }

            withContext(Dispatchers.Main) { onRequestResultListener.onRequestErrorResult("Connection not open")}
        }
    }
}