package com.example.covaccine

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast

class MainPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_page)
    }

    fun vaccineSlots(view: View) {
        var intent = Intent(this,Search::class.java)
        startActivity(intent)
    }

    fun covidtracker(view: View) {
       var intent = Intent(this,covidTracker::class.java)
        startActivity(intent)
    }
}