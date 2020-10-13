package com.example.soal1.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.soal1.model.User
import com.example.soal1.viewmodel.login.LoginDataSource
import com.example.soal1.viewmodel.login.LoginRepository
import com.example.soal1.viewmodel.login.LoginViewModel

/**
 * ViewModel provider factory to instantiate LoginViewModel.
 * Required given LoginViewModel has a non-empty constructor
 */
class LoginViewModelFactory(val url: String, val listener: User.ResponseListener) :
    ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel(
                loginRepository = LoginRepository(
                    dataSource = LoginDataSource(url),
                ), listener
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}