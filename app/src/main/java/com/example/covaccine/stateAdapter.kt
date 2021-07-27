package com.example.covaccine

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class stateAdapter(private var caseStatelist:List<StateCase>):RecyclerView.Adapter<stateAdapter.MyViewHolder>()
{
    class MyViewHolder(view: View):RecyclerView.ViewHolder(view){
        var region:TextView =view.findViewById(R.id.region)
        var ccases:TextView = view.findViewById(R.id.confirmSTCase)
        var cnewcases:TextView = view.findViewById(R.id.newConfirmSTCase)
        var rcases:TextView = view.findViewById(R.id.recoverSTcases)
        var rnewcases:TextView = view.findViewById(R.id.newrecoverSTcase)
        var dcases:TextView = view.findViewById(R.id.deathSTcases)
        var dnewcases:TextView = view.findViewById(R.id.newdeathSTcases)
        var acases:TextView = view.findViewById(R.id.ActiveSTcase)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.state,parent,false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var state = caseStatelist[position]

        holder.region.text = state.State
        holder.ccases.text = state.ConfirmedCases.toString()
        holder.cnewcases.text = state.newConfirmedCases.toString()
        holder.rcases.text = state.recoveredCases.toString()
        holder.rnewcases.text=state.newRecoverCases.toString()
        holder.dcases.text =state.deathCases.toString()
        holder.dnewcases.text=state.newDeatCases.toString()
        holder.acases.text =state.activeCases.toString()
    }

    override fun getItemCount(): Int {
        return caseStatelist.size
    }


}