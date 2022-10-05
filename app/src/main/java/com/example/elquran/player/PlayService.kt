package com.example.elquran.player

import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import android.os.Binder
import android.os.Build
import android.os.IBinder
import android.widget.RemoteViews
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.example.elquran.MyApplication
import com.example.elquran.MyApplication.Companion.CHANNEL_ID
import com.example.elquran.R

class PlayService :Service() {

    var mediaplayer=MediaPlayer()
    override fun onCreate() {
        super.onCreate()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        var urlToPlay=intent?.getStringExtra("url")
        var channel_name=intent?.getStringExtra("name")
        var action=intent?.getStringExtra("action")


        if (urlToPlay != null && channel_name != null)
            startMediaPlayer(urlToPlay, channel_name)

        if (action != null) {
            if (action == PLAY_Action) {
                playPusedMediaPlayer()
            } else if (action == STOP_Action) {
                stopMediaPlayer()

            }
        }
        return START_NOT_STICKY
    }

    private fun playPusedMediaPlayer() {
        if (mediaplayer.isPlaying){
            mediaplayer.pause()
    }
    else if (!mediaplayer.isPlaying){
            mediaplayer.start()
        }
}
    @RequiresApi(Build.VERSION_CODES.O)
    fun startMediaPlayer(urlToPlay: String, name: String) {
        stopMediaPlayer()
        mediaplayer=MediaPlayer()
        mediaplayer?.setDataSource(this, Uri.parse(urlToPlay))
        mediaplayer?.prepareAsync()
        mediaplayer?.setOnPreparedListener {
            it.start()

    }
        createNotificationForMediaPlayer(name)

    }

     fun stopMediaPlayer() {
        if (mediaplayer?.isPlaying!!) {
            mediaplayer?.stop()
           mediaplayer?.reset()}
           // stopForeground(true)
            stopSelf()

    }
    fun pausMediaPlayer() {
        if (mediaplayer?.isPlaying!!) {
            mediaplayer.pause()
            // mediaplayer?.reset()
        }
    }
 var radio_media_player_notification_id=20
    @RequiresApi(Build.VERSION_CODES.O)
    fun  createNotificationForMediaPlayer(name:String){


        val notificationLayout = RemoteViews(packageName, R.layout.radio_notification_small)
        notificationLayout.setTextViewText(R.id.notification_title,"islamic app radio")
        notificationLayout.setTextViewText(R.id.notification_desc,name)
        notificationLayout.setOnClickPendingIntent(R.id.notifiation_play,getPlayButtonPendingInt())
        notificationLayout.setOnClickPendingIntent(R.id.notifiation_stop,getStopButtonPendingInt())

        val customNotification = NotificationCompat.Builder(this, CHANNEL_ID)
        var builder = NotificationCompat.Builder(this,MyApplication.CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_notification_icon)
            .setStyle(NotificationCompat.DecoratedCustomViewStyle())
            .setCustomContentView(notificationLayout)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)


        startForeground(radio_media_player_notification_id,builder.build())


    }

    val PLAY_Action="play"
    val STOP_Action="stop"

    @RequiresApi(Build.VERSION_CODES.O)
    fun getPlayButtonPendingInt():PendingIntent{
        val intent=Intent(this,PlayService::class.java)
        intent.putExtra("action",PLAY_Action)
        val pengingInt=PendingIntent.getService(this,0,intent,PendingIntent.FLAG_UPDATE_CURRENT)
        return pengingInt
    }
    @RequiresApi(Build.VERSION_CODES.O)

    fun getStopButtonPendingInt():PendingIntent{
        val intent=Intent(this,PlayService::class.java)
        intent.putExtra("action",STOP_Action)
        val pengingIntent=PendingIntent.getService(this,0,intent,PendingIntent.FLAG_UPDATE_CURRENT)
        return pengingIntent
    }
    private val binder = LocalBinder()

    override fun onBind(intent: Intent?): IBinder? {
return binder
    }
    inner class LocalBinder : Binder() {
        fun getService(): PlayService = this@PlayService
    }
}