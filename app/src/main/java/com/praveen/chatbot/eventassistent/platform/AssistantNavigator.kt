package com.praveen.chatbot.eventassistent.platform

import android.content.Context
import android.content.Intent
import android.media.AudioManager
import com.praveen.chatbot.eventassistent.view.AssistantContract
import java.text.SimpleDateFormat
import java.util.*


class AssistantNavigator(private val context: Context) : AssistantContract.Navigator {

    override fun startVolumeUpService() {
        val audioManager = context.getSystemService(Context.AUDIO_SERVICE) as AudioManager
        audioManager.adjustStreamVolume(AudioManager.STREAM_MUSIC, AudioManager.ADJUST_RAISE,
                AudioManager.FLAG_SHOW_UI)
    }
    override fun startVolumeDownService() {
        val audioManager = context.getSystemService(Context.AUDIO_SERVICE) as AudioManager
        audioManager.adjustStreamVolume(AudioManager.STREAM_MUSIC, AudioManager.ADJUST_LOWER,
                AudioManager.FLAG_SHOW_UI)
    }

    override fun startVolumeMuteService() {
        val audioManager = context.getSystemService(Context.AUDIO_SERVICE) as AudioManager
        audioManager.adjustStreamVolume(AudioManager.STREAM_MUSIC, AudioManager.ADJUST_MUTE,
                AudioManager.FLAG_SHOW_UI);
    }

    override fun startYouTube(params: Map<String, String>) {
        Intent(Intent.ACTION_SEARCH).apply {
            setPackage("com.google.android.youtube")
            params["any"]?.let {
                putExtra("query", it)
            }
            context.startActivity(this)
        }

    }


}