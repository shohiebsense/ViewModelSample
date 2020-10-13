package com.example.soal1.viewmodel.home

import com.example.soal1.global.Preferences
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class HomeRepository(val baseUrl: String, val listener : Preferences.URLBuilder.GenericResponseListener)  {

    suspend fun getImage() {
        try{
            val request = Preferences.URLBuilder.generateGetRequest(baseUrl+"image")
            withContext(Dispatchers.IO){
                val responseStr = Preferences.URLBuilder.executeGetRequest(request, listener)
            }
        }catch (e: Throwable){
            e.printStackTrace()
        }
    }

    suspend fun getImageList(){
        try{
            val request = Preferences.URLBuilder.generateGetRequest(baseUrl+"imagelist")
            withContext(Dispatchers.IO){
                val responseStr = Preferences.URLBuilder.executeGetRequest(request, listener)
            }
        }catch (e: Throwable){
            e.printStackTrace()
        }
    }
}