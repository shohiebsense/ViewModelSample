package com.example.soal1.viewmodel.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class HomeViewModel(val homeRepository: HomeRepository) : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }

    fun getImage(){
        viewModelScope.launch {
            homeRepository.getImage()
        }
    }


    fun getImageList(){
        viewModelScope.launch {
            homeRepository.getImageList()
        }
    }
    //val text: LiveData<String> = _text
}