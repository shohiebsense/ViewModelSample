package com.example.soal1.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.soal1.global.Preferences
import com.example.soal1.viewmodel.home.HomeRepository
import com.example.soal1.viewmodel.home.HomeViewModel

/**
 * Required given LoginViewModel has a non-empty constructor
 */
class HomeViewModelFactory(val url: String, val listener: Preferences.URLBuilder.GenericResponseListener) :
    ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(
                HomeRepository(url, listener)
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}