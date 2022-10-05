package com.example.elquran

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class Splash_Screan : AppCompatActivity() {
        lateinit var handler:Handler
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash__screan)

         handler= Handler()
        handler.postDelayed({
            val  intent=Intent(this,Main2Activity::class.java)
            startActivity(intent)
            finish()





        },600)

           }


    }

