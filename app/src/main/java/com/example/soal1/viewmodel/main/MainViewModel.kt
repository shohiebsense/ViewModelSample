package com.example.soal1.viewmodel.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainViewModel(val mainRepository: MainRepository) : ViewModel() {

    fun getMenu(){
        viewModelScope.launch {
            mainRepository.getMenu()
        }
    }
}