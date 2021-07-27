package com.example.covaccine

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_slotdisplay.*
import org.json.JSONException

class Slotdisplay : AppCompatActivity(), itemClicked {
   lateinit var slotlist:List<Session>
   private lateinit var customAdapter: customAdapter
   lateinit var recyclerView: RecyclerView
    var pin = ""
    var date = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_slotdisplay)
        pin = intent.getStringExtra("Pin").toString()
        date = intent.getStringExtra("Date").toString()
        recyclerView = findViewById(R.id.recyclerView)
        slotlist = ArrayList<Session>()
        prepareItems()
    }



    private fun prepareItems() {
        prgress.visibility= View.VISIBLE
        errorD.visibility = View.GONE
       val url = "https://cdn-api.co-vin.in/api/v2/appointment/sessions/public/findByPin?pincode=$pin&date=$date"
        val queue = Volley.newRequestQueue(this)
        val request =
            JsonObjectRequest(Request.Method.GET, url,null,{ response->
                try {
                    val sessions = response.getJSONArray("sessions")
                    if(sessions.length().equals(0))
                    {
                        prgress.visibility = View.GONE
                        errorD.visibility = View.VISIBLE
                    }
                    for(i in 0 until sessions.length()){


                        val sessionObj = sessions.getJSONObject(i)
                        val name = sessionObj.getString("name")
                        val Age = sessionObj.getInt("min_age_limit")
                        val available_capacity = sessionObj.getInt("available_capacity")
                        val available_capacity_dose1 = sessionObj.getInt("available_capacity_dose1")
                        val available_capacity_dose2 = sessionObj.getInt("available_capacity_dose2")
                        val vaccine = sessionObj.getString("vaccine")
                        val address = sessionObj.getString("address")+", "+sessionObj.getString("district_name")+
                                ", "+sessionObj.getString("state_name")

                        prgress.visibility = View.GONE

                        val sessionlist = Session(name,"Age : $Age+","Total\n$available_capacity","D1\n$available_capacity_dose1",
                                "D2\n$available_capacity_dose2",vaccine,address)
                        slotlist = slotlist+sessionlist
                    }
                    customAdapter = customAdapter(slotlist,this)
                    recyclerView.layoutManager=LinearLayoutManager(this)
                    recyclerView.adapter=customAdapter
                }catch (e:JSONException){
                    prgress.visibility = View.GONE
                    errorD.visibility =View.VISIBLE
                    e.printStackTrace()
                }
            },{error ->
                    prgress.visibility = View.GONE
                    errorD.visibility = View.VISIBLE
                    Toast.makeText(this,"Fail to get Data",Toast.LENGTH_SHORT).show()

            })

        queue.add(request)

    }

    override fun Onitemclick(item: Session) {
       val intent = Intent(this,Slotdetails::class.java)
        intent.putExtra("name",item.name)
        intent.putExtra("age",item.min_age_limit)
        intent.putExtra("address",item.address)
        intent.putExtra("available",item.available_capacity)
        intent.putExtra("vaccine",item.vaccine)
        intent.putExtra("dose1",item.available_capacity_dose1)
        intent.putExtra("dose2",item.available_capacity_dose2)
        startActivity(intent)
    }
}