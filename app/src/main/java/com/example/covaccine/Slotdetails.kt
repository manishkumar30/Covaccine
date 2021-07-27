package com.example.covaccine

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.browser.customtabs.CustomTabsIntent

class Slotdetails : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_slotdetails)
        val name  = findViewById<TextView>(R.id.centre)
        val age  = findViewById<TextView>(R.id.age)
        val vaccine  = findViewById<TextView>(R.id.vaccine)
        val avialable  = findViewById<TextView>(R.id.total)
        val dose1  = findViewById<TextView>(R.id.dose1)
        val dose2 = findViewById<TextView>(R.id.dose2)
        val address  = findViewById<TextView>(R.id.address)

       name.setText(intent.getStringExtra("name"))
       age.setText(intent.getStringExtra("age"))
        address.setText(intent.getStringExtra("address"))
        avialable.setText(intent.getStringExtra("available"))
        vaccine.setText(intent.getStringExtra("vaccine"))
        dose1.setText(intent.getStringExtra("dose1"))
        dose2.setText(intent.getStringExtra("dose2"))
    }

    fun cowin(view: View) {
        val url = "https://selfregistration.cowin.gov.in/"
        val builder = CustomTabsIntent.Builder()
        val customTabsIntent = builder.build()
        customTabsIntent.launchUrl(this, Uri.parse(url))
    }
}