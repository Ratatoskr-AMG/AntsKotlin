package com.example.antskotlin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.antskotlin.Surface

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val surface = Surface(this, resources)
        setContentView(surface)
    }
}