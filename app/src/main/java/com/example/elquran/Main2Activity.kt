package com.example.elquran

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity

class Main2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        val navView: BottomNavigationView=findViewById(R.id.nav_view)
         navView.setOnNavigationItemSelectedListener {item->

              val id:Int=item.itemId
             if (id==R.id.navigation_quran){
                 showQuranFragment()

             }else if(
                 id==R.id.navigation_tasbeh){
                 showTasbehFragment()
             }else{
             id==R.id.navigation_radio
            showRadioFragment()
         }



return@setOnNavigationItemSelectedListener true

         }
      navView.selectedItemId=R.id.navigation_quran
    }
    fun showQuranFragment(){
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container,SurasFragment()).commit()
    }
    fun showTasbehFragment(){
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container,TasbehFragment()).commit()
    }
    fun showRadioFragment(){
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container,RadioFragment()).commit()
    }
}
