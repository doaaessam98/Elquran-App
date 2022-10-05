package com.example.elquran

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_soura_detils.*
import kotlinx.android.synthetic.main.soura_item.*
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import kotlin.math.log

class SouraDetils : AppCompatActivity() {
lateinit var adopter:AyaAdopter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_soura_detils)
        val souraName=intent.getStringExtra(Constants.EXTRA_SOURA_NAME)
        val  SOURFILE=intent.getIntExtra(Constants.EXTRA_SOURA_FILE_NAME,-1)
        souraNameText.setText("سورة"+souraName)
        val souraList=readSuraFile("${SOURFILE+1}.txt")
        adopter=AyaAdopter(souraList)
        recycler_aya_contain.adapter=adopter
    }
    fun readSuraFile(fileName:String):List<String>{

        var  souraList =mutableListOf<String>()
        var  reader :BufferedReader
        try {
            var file :InputStream=assets.open(fileName)
            reader=BufferedReader(InputStreamReader(file))
            var line:String?=reader.readLine()
            while (line!=null){
                souraList.add(line)
                line=reader.readLine()
            }

        }catch (io:Exception){
            io.printStackTrace()
        }
        return souraList
    }
}
