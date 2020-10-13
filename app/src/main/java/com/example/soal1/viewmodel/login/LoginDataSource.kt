package com.example.soal1.viewmodel.login

import com.example.soal1.global.Preferences
import com.example.soal1.model.User
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.FormBody
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import java.io.IOException
import java.util.*

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
class LoginDataSource(val url: String) {
    val client = OkHttpClient()

    suspend fun login(username: String, password: String): User? {
        try {
            val body = FormBody.Builder()
                .add("username", username)
                .add("password", password).build()

            val request =Preferences.URLBuilder.generatePostRequest(body, url+"login/")
            return withContext(Dispatchers.IO){
                val responseStr = Preferences.URLBuilder.executePostRequest(request)
                Gson().fromJson(responseStr, User::class.java)
            }
        } catch (e: Throwable) {
            e.printStackTrace()
            return null
        }
    }

    fun logout() {
        // TODO: revoke authentication
    }
}