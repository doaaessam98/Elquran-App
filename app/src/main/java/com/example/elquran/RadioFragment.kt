package com.example.elquran

import android.app.Activity
import android.content.ComponentName
import android.content.Context
import android.graphics.Color
import android.media.AudioManager
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.elquran.ApiManger.ApiManger
import com.example.elquran.ApiManger.model.RadioResponse
import com.example.elquran.ApiManger.model.RadiosChannels
import com.example.elquran.player.PlayService
import kotlinx.android.synthetic.main.fragment_radio.*
import kotlinx.android.synthetic.main.radio_item.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.content.Intent
import android.content.ServiceConnection
import android.os.IBinder

import java.net.URL

/**
 * A simple [Fragment] subclass.
 */
class RadioFragment :Fragment(){
 val adapter=RadioAdapter()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_radio, container, false)
    }

    lateinit var player:MediaPlayer
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
      // var URl :String="http://api.mp3quran.net/radios/radio_arabic.json"
        var play=view.findViewById<ImageView>(R.id.image_play)
        super.onViewCreated(view, savedInstanceState)
        recycler_view.adapter=adapter
        getChannelsFromApi()

    adapter.onPlayerClickListener=object:RadioAdapter.OnItemClickListener{
        override fun onItemClick(pos: Int, item:RadiosChannels?) {
            startPlayService(item!!)
          // startPlay(item)
            //image_play.setImageResource(R.drawable.ic_start_play_foreground);

            // item?.name

        }
    }

        adapter.onStopClickListener=object:RadioAdapter.OnItemClickListener{
            override fun onItemClick(pos: Int, item: RadiosChannels?) {
     stopPlayService(item!!)
     // stopChaneel()
                //image_play.setImageResource(R.drawable.ic_play_foreground);

            }
        }

        }

    private fun startPlayService(item: RadiosChannels) {
        if (mBound){
            mService.startMediaPlayer(item.radioUrl!!,item.name!!)
        }
        /*val intent=Intent(activity,PlayService::class.java)
        intent.putExtra("url",item)
        activity?.startService(intent)*/

    }
    private fun stopPlayService(item: RadiosChannels) {
        if (mBound){
            mService.stopMediaPlayer()
        /*val intent=Intent(activity,PlayService::class.java)
        intent.putExtra("url",item)
        activity?.stopService(intent)*/
    }}

        var mediaplayer:MediaPlayer?=null








    private fun getChannelsFromApi() {
        ApiManger.getWebServices().getRadioChannels().enqueue(object :Callback<RadioResponse>{

            override fun onFailure(call: Call<RadioResponse>, t: Throwable) {
                Toast.makeText(activity, t.localizedMessage ?: "error", Toast.LENGTH_LONG)
                    .show()




            }

            override fun onResponse(call: Call<RadioResponse>, response: Response<RadioResponse>) {

                    //hideLoadingDialog
                progress_bar.visibility=View.GONE
                    val channels = response.body()?.radios;
                    adapter.changeData(channels ?: listOf())
                }


        })
    }
    private fun startService() {
        val intent=Intent(activity,PlayService::class.java)
        activity?.startService(intent)

    }
    override fun onStart() {
        super.onStart()
        // Bind to LocalService
        startService()
       bindService()
        }


    override fun onStop() {
        super.onStop()
        activity?.unbindService(connection)
        mBound=false
    }
    fun  bindService(){
     val intent=Intent(activity,PlayService::class.java)
     activity?.bindService(intent,connection,Context.BIND_AUTO_CREATE)
 }



    private lateinit var mService: PlayService
    private var mBound: Boolean = false
    private val connection = object : ServiceConnection {

        override fun onServiceConnected(className: ComponentName, service: IBinder) {
            // We've bound to LocalService, cast the IBinder and get LocalService instance
            val binder = service as PlayService.LocalBinder
            mService = binder.getService()
            mBound = true
        }

        override fun onServiceDisconnected(arg0: ComponentName) {
            mBound = false
        }
    }

    /*private fun startPlay(uRl:String) {
        stopChaneel()
        if (uRl== null){
            return
        }
        mediaplayer=MediaPlayer()
        mediaplayer?.setDataSource(uRl)
        mediaplayer?.prepareAsync()
        mediaplayer?.setOnPreparedListener(MediaPlayer.OnPreparedListener {
            it.start()
            // image_play.setBackgroundColor(Color.rgb(100, 100, 50));
            image_play.setImageResource(R.drawable.ic_start_play_foreground);



        })


    }*/

    /*private fun stopChaneel() {
      mediaplayer?.stop()
      image_play.setImageResource(R.drawable.ic_play_foreground);




  }*/

}



