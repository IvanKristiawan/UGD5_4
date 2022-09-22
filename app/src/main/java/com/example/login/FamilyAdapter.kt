package com.example.login

import android.annotation.SuppressLint
import android.provider.ContactsContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.login.R
import com.example.login.family.Family
import kotlinx.android.synthetic.main.activity_family_adapter.view.*

class FamilyAdapter (private val notes: ArrayList<Family>, private val
listener: OnAdapterListener) :
    RecyclerView.Adapter<FamilyAdapter.NoteViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            NoteViewHolder {
        return NoteViewHolder(

            LayoutInflater.from(parent.context).inflate(R.layout.activity_family_adapter,parent, false)
        )
    }
    override fun onBindViewHolder(holder: NoteViewHolder, position:
    Int) {
        val note = notes[position]
        holder.view.text_title.text = note.name
        holder.view.text_title.setOnClickListener{
            listener.onClick(note)
        }
        holder.view.icon_edit.setOnClickListener {
            listener.onUpdate(note)
        }
        holder.view.icon_delete.setOnClickListener {
            listener.onDelete(note)
        }
    }
    override fun getItemCount() = notes.size
    inner class NoteViewHolder( val view: View) :
        RecyclerView.ViewHolder(view)
    @SuppressLint("NotifyDataSetChanged")
    fun setData(list: List<Family>){
        notes.clear()
        notes.addAll(list)
        notifyDataSetChanged()
    }
    interface OnAdapterListener {
        fun onClick(note: Family)
        fun onUpdate(note: Family)
        fun onDelete(note: Family)
    }
}