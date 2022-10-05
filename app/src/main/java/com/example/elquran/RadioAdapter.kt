package com.example.elquran

import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.elquran.ApiManger.model.RadiosChannels
import kotlinx.android.synthetic.main.radio_item.*

class RadioAdapter():RecyclerView.Adapter<RadioAdapter.viewHolder> (){

     var channels=listOf<RadiosChannels?>()


        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
            var view=LayoutInflater.from(parent.context).inflate(R.layout.radio_item,parent,false)
            return viewHolder(view)


        }

        override fun getItemCount(): Int {
            return channels.size
        }


        override fun onBindViewHolder(holder: viewHolder, position: Int) {
         holder.title.setText(channels.get(position)?.name)

             if (onPlayerClickListener!=null){
                val item=channels.get(position)
                     holder.play.setOnClickListener(View.OnClickListener {
                        onPlayerClickListener?.onItemClick(position,item)

                     })



        }
            if (onStopClickListener!=null){
                val item=channels.get(position)
                holder.stop.setOnClickListener(View.OnClickListener {
                  onStopClickListener?.onItemClick(position,item)
                })



            }

        }
    fun changeData(data: List<RadiosChannels?>) {
        this.channels =data
        notifyDataSetChanged()

    }
    interface OnItemClickListener {
        fun onItemClick(pos: Int, item: RadiosChannels?)
    }

    var onPlayerClickListener: OnItemClickListener? = null
    var onStopClickListener: OnItemClickListener? = null



    class viewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        lateinit var title:TextView
        lateinit var play:ImageView
        lateinit var stop:ImageView

        init {
            title=itemView.findViewById(R.id.channel_title)
            play=itemView.findViewById(R.id.image_play)
            stop=itemView.findViewById(R.id.image_stop)



        }



    }


    }