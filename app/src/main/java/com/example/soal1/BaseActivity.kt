package com.example.soal1

import androidx.appcompat.app.AppCompatActivity
import com.example.soal1.global.Preferences

open class BaseActivity : AppCompatActivity() {
    val preferences = Preferences(this)

}