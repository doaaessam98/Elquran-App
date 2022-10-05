package com.example.elquran

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment


class TasbehFragment: Fragment(){
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.tasbeh_fragment,container,false)

    }

     var count=0
     var count1=0
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var image_sebha=view.findViewById(R.id.sebha)as ImageView
        var imag_refresh=view.findViewById<ImageView>(R.id.refresh)as ImageView
        var textView_count_of_tasbih=view.findViewById(R.id.count_of_tasbih)as TextView
        var textView_sum_of_tasbih=view.findViewById(R.id.sum_of_tasbih)as TextView
          var  spinner=view.findViewById<Spinner>(R.id.spinner)as  Spinner
        image_sebha.setOnClickListener(View.OnClickListener {
            count++
            count1++
            textView_count_of_tasbih.setText(count.toString())
            textView_sum_of_tasbih.setText(count1.toString())



        })

        imag_refresh.setOnClickListener(View.OnClickListener {
            textView_count_of_tasbih.setText(null)
            textView_sum_of_tasbih.setText(null)

            count=0
            count1=0

        })


       val adapter =context?.let {
            ArrayAdapter.createFromResource(
                it,
                R.array.kind_of_zekr, android.R.layout.simple_spinner_item)
        }
        // Specify the layout to use when the list of choices appears
        if (adapter != null) {
            adapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item)
        }
        // Apply the adapter to the spinner
        spinner.adapter = adapter

        spinner.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

            override fun onItemSelected(parent: AdapterView<*>,
                                        view: View, position: Int, id: Long) {
               // (parent.getChildAt(0) as TextView).text=null


                textView_count_of_tasbih.setText(null)
                count=0

            }




     }}


     }












