package com.example.soal1.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.soal1.global.Preferences
import com.example.soal1.viewmodel.main.MainRepository
import com.example.soal1.viewmodel.main.MainViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class MainViewModelFactory(val url: String, val listener: Preferences.URLBuilder.GenericResponseListener) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(MainViewModel::class.java)){
            return MainViewModel(MainRepository(url, listener))  as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }


}