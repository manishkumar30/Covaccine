package com.example.covaccine

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class MainActivity : AppCompatActivity() {
   private var SPLASH_TIME = 3000L
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Handler().postDelayed(
                {
                    var intent=Intent(this,MainPage::class.java)
                    startActivity(intent)
                    finish()
                }
               , SPLASH_TIME
        )
    }
}