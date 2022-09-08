package com.example.login

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import android.widget.TextView
import com.example.login.entity.Dosen
import com.example.login.entity.Travel

class RVTravelAdapter(private val data: Array<Travel>) : RecyclerView.Adapter<RVTravelAdapter.viewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder{
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.rv_item_travel, parent, false)
        return viewHolder(itemView)
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int){
        val currentItem = data[position]
        holder.tvNamaDosen.text = "Rp ${currentItem.cost.toString()}"
        holder.tvDetailDosen.text = currentItem.name
    }

    override fun getItemCount(): Int {
        return data.size
    }

    class viewHolder(itemView : View) :  RecyclerView.ViewHolder(itemView){
        val tvNamaDosen : TextView = itemView.findViewById(R.id.tv_nama_dosen)
        val tvDetailDosen : TextView = itemView.findViewById(R.id.tv_details_dosen)
    }

}
