package com.example.elquran

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

     class AyaAdopter(var items:List<String>) : RecyclerView.Adapter<AyaAdopter.viewHolder>() {



         override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
         var view=LayoutInflater.from(parent.context).inflate(R.layout.aya_content,parent,false)
             return viewHolder(view)
         }

         override fun getItemCount(): Int {
      return items.size
         }

         override fun onBindViewHolder(holder: viewHolder, position: Int) {
          var item=items.get(position)
             holder.elAya.setText(item +"(" +(position+1 )+ ")")
         }

         class viewHolder(itemView: View) :RecyclerView.ViewHolder(itemView){
             var elAya:TextView
             init {
                 elAya=itemView.findViewById(R.id.contant_aya)
             }

         }}