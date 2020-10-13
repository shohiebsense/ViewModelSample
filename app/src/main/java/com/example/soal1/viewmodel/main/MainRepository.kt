package com.example.soal1.viewmodel.main

import com.example.soal1.global.Preferences
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MainRepository(val baseUrl: String, val listener : Preferences.URLBuilder.GenericResponseListener) {


    suspend fun getMenu(){
        try{
            val request = Preferences.URLBuilder.generateGetRequest(baseUrl+"menu")
            withContext(Dispatchers.IO){
                Preferences.URLBuilder.executeGetRequest(request, listener)
            }
        } catch (e: Throwable){
            e.printStackTrace()
        }
    }
}