package com.example.covaccine

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_statelist.*
import org.json.JSONException

class Statelist : AppCompatActivity() {
    lateinit var caselist:List<StateCase>
    private lateinit var stateAdapter: stateAdapter
    lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_statelist)
        recyclerView=findViewById(R.id.stateList)
        caselist=ArrayList<StateCase>()
        getStateCase()
    }

    private fun getStateCase() {
        progress2.visibility=View.VISIBLE
        stateList.visibility=View.GONE
        error2.visibility = View.GONE
        val url1 = "https://api.apify.com/v2/key-value-stores/toDWvRj1JpTXiM8FF/records/LATEST?disableRedirect=true"
        val queue1 = Volley.newRequestQueue(this)
        val request =
                JsonObjectRequest(Request.Method.GET,url1,null, { response ->
                    try {
                        val regionData = response.getJSONArray("regionData")
                        for (i in 0 until regionData.length()) {
                            val obj = regionData.getJSONObject(i)
                            val region = obj.getString("region")
                            val confirm = obj.getInt("totalInfected")
                            val recovered = obj.getInt("recovered")
                            val newrecov = obj.getInt("newRecovered")
                            val decease = obj.getInt("deceased")
                            val newdecease = obj.getInt("newDeceased")
                            val active = obj.getInt("activeCases")
                            val newAct = obj.getInt("newInfected")

                            val newConfirm = newrecov + newdecease + newAct

                            val newList = StateCase(region, confirm, recovered, decease, active, newConfirm, newrecov, newdecease)
                            caselist = caselist + newList

                            progress2.visibility=View.GONE
                            stateList.visibility=View.VISIBLE
                        }
                        stateAdapter = stateAdapter(caselist)
                        recyclerView.layoutManager = LinearLayoutManager(this)
                        recyclerView.adapter = stateAdapter
                    } catch (e: JSONException) {
                        progress2.visibility=View.GONE
                        error2.visibility = View.VISIBLE
                        e.printStackTrace()
                    }
                } ,{
                    error ->
                    progress2.visibility=View.GONE
                    error2.visibility = View.VISIBLE
                    Toast.makeText(this,"Unable to fetch Data",Toast.LENGTH_SHORT).show()
                })
        queue1.add(request)
    }
}