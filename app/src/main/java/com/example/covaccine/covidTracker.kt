package com.example.covaccine

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_covid_tracker.*
import org.json.JSONException
import org.json.JSONObject

class covidTracker : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_covid_tracker)
        getData()
    }

    private fun getData() {
        progress.visibility=View.VISIBLE
        India.visibility=View.GONE
        err.visibility=View.GONE
        val url = "https://api.apify.com/v2/key-value-stores/toDWvRj1JpTXiM8FF/records/LATEST?disableRedirect=true"
        val queue = Volley.newRequestQueue(this)
        val request =
            JsonObjectRequest(Request.Method.GET,url,null,{ response ->
                try {

                    val active = response.getInt("activeCases")
                    val newAct = response.getInt("activeCasesNew")
                    val recovered = response.getInt("recovered")
                    val newRec = response.getInt("recoveredNew")
                    val deaths = response.getInt("deaths")
                    val newDea = response.getInt("deathsNew")
                    val confirm = response.getInt("totalCases")
                    val newConf = newRec+newDea+newAct
                    progress.visibility = View.GONE
                    India.visibility = View.VISIBLE

                    findViewById<TextView>(R.id.confirm).text= confirm.toString()
                    findViewById<TextView>(R.id.recovered).text= recovered.toString()
                    findViewById<TextView>(R.id.deaths).text= deaths.toString()
                    findViewById<TextView>(R.id.newDeaths).text= newDea.toString()
                    findViewById<TextView>(R.id.newRecovered).text= newRec.toString()
                    findViewById<TextView>(R.id.Active).text= active.toString()
                    findViewById<TextView>(R.id.newConf).text= newConf.toString()


                }
                catch (e:JSONException){
                    progress.visibility=View.GONE
                    err.visibility=View.VISIBLE
                   e.printStackTrace()
                }
            },{ error ->
                progress.visibility=View.GONE
                err.visibility = View.VISIBLE
             Toast.makeText(this,"Error ",Toast.LENGTH_SHORT).show()
            })

        queue.add(request)
    }


    fun Statelist(view: View) {
       val intent = Intent(this,Statelist::class.java)
        startActivity(intent)
    }
}