package com.example.covaccine

import android.view.LayoutInflater
import android.view.OrientationEventListener
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

internal class customAdapter(private var sessionlist:List<Session>,private val listener:itemClicked):
RecyclerView.Adapter<customAdapter.MyViewHolder>() {
    internal inner class MyViewHolder(view: View): RecyclerView.ViewHolder(view){
        var nameView :TextView = view.findViewById(R.id.nameView)
        var AgeView :TextView = view.findViewById(R.id.AgeView)
        var d1:TextView = view.findViewById(R.id.d1)
        var d2:TextView = view.findViewById(R.id.d2)
        var dose:TextView = view.findViewById(R.id.dose)
        var doses:LinearLayout = view.findViewById(R.id.doses)
        var booked:TextView = view.findViewById(R.id.book)
        var vaccine:TextView=view.findViewById(R.id.vac)
        var dd:TextView = view.findViewById(R.id.dd)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.slots,parent,false)
        val viewHolder = MyViewHolder(itemView)
        itemView.setOnClickListener{
            listener.Onitemclick(sessionlist[viewHolder.adapterPosition])
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var item = sessionlist[position]

        holder.nameView.text = item.name
        holder.AgeView.text = item.min_age_limit
        holder.vaccine.text = item.vaccine

        if(item.available_capacity.equals("Total\n0")) {
            holder.doses.visibility = View.GONE
            holder.booked.visibility=View.VISIBLE
            holder.dd.visibility=View.GONE
        }
        else{
            holder.dose.text = item.available_capacity
            holder.d1.text=item.available_capacity_dose1
            holder.d2.text=item.available_capacity_dose2
            holder.booked.visibility = View.GONE
            holder.doses.visibility=View.VISIBLE
            holder.dd.visibility=View.VISIBLE
        }

    }

    override fun getItemCount(): Int {
        return  sessionlist.size
    }

}
interface itemClicked{
    fun Onitemclick(item:Session)
}