package com.example.elquran

import android.content.Context
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class SouraNameAdopter():RecyclerView.Adapter<SouraNameAdopter.viewHolder>() {

             var souraList=listOf<String>()


         override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
            val view=LayoutInflater.from(parent.context).inflate(R.layout.soura_item,parent,false)

             return viewHolder(view)
          }

          override fun getItemCount(): Int {
              return souraList.size
          }
          interface  onItemClickListener{
              fun onItemClick(pos:Int,item:String)
          }
    var onItemClockListener:onItemClickListener?=null



          override fun onBindViewHolder(holder: viewHolder, position: Int) {

            holder.name.setText(souraList.get(position))
            if (onItemClockListener!=null){
                holder.itemView.setOnClickListener(View.OnClickListener {item->

                   onItemClockListener?.onItemClick(position,souraList.get(position))
                })

            }

          }
         class viewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
            lateinit  var name:TextView
             init {
                 name=itemView.findViewById(R.id.soura_name)






          }


      }}