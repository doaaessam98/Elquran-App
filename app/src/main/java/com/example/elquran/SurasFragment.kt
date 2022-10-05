package com.example.elquran

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.sours_flagment_fragment.*
import android.content.Intent as Intent

class SurasFragment:Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.sours_flagment_fragment,container,false)
            }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adopter=SouraNameAdopter()
        var image:ImageView=view.findViewById(R.id.image_soura)
        adopter.souraList=DataHolder.ArSuras
        recycle.adapter=adopter
        adopter.onItemClockListener=object :SouraNameAdopter.onItemClickListener{
            override fun onItemClick(pos: Int, item: String) {
               var intent=Intent(context,SouraDetils::class.java)
               intent.putExtra(Constants.EXTRA_SOURA_NAME,item)

               intent.putExtra(Constants.EXTRA_SOURA_FILE_NAME,pos)

               startActivity(intent)

                }            }




    }
    }
