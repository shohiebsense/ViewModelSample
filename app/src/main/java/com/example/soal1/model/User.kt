package com.example.soal1.model

/**
 * Data class that captures user information for logged in users retrieved from LoginRepository
 */
data class User(
    val userId: String,
    val displayName: String,
    var token : String? = null,
) {

    object Pref {
        val PREF_USER_ID = "PREF_USER_ID"
        val PREF_DISPLAY_NAME = "PREF_DISPLAY_NAME"
        val PREF_TOKEN = "PREF_TOKEN"
    }


    interface ResponseListener {
        fun onSuccessResponse(user: User)
    }
}
