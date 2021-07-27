package com.example.covaccine

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.DatePicker
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_search.*
import java.util.*

class Search : AppCompatActivity() {
   var d = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

    }

    fun track(view: View) {
        var p = pincode.editableText.toString()
        var dt = d
        if(p.isNullOrBlank()||dt.isNullOrBlank())
        {
            Toast.makeText(this,"Please Enter Correct Details",Toast.LENGTH_SHORT).show()
        }
        else{
            var intent = Intent(this,Slotdisplay::class.java)
            intent.putExtra("Pin",p)
            intent.putExtra("Date",dt)
            startActivity(intent)
        }
    }

    fun Date(view: View) {
        var editText = findViewById<EditText>(R.id.Date)

        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        val dpd = DatePickerDialog(this,
                DatePickerDialog.OnDateSetListener{ view, year, month, dayOfMonth ->
                    d="""$dayOfMonth-${month+1}-$year"""
                    editText.setText(d)
                },
                 year,
                month,
                day
                )
        dpd.datePicker.minDate=c.timeInMillis
        dpd.show()

    }


}