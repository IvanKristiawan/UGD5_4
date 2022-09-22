package com.example.login

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import android.widget.TextView
import com.example.login.entity.Dosen
import com.example.login.entity.Riwayat
import com.example.login.entity.Travel

class RVRiwayatAdapter(private val data: Array<Riwayat>) : RecyclerView.Adapter<RVRiwayatAdapter.viewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RVRiwayatAdapter.viewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.rv_item_riwayat, parent, false)
        return RVRiwayatAdapter.viewHolder(itemView)
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int){
        val currentItem = data[position]
        holder.tvNamaDosen.text = currentItem.date
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