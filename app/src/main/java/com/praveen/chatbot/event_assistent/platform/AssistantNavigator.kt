package com.praveen.chatbot.event_assistent.platform

import android.content.Context
import android.content.Intent
import android.media.AudioManager
import com.praveen.chatbot.event_assistent.view.AssistantContract

class AssistantNavigator(private val context: Context) : AssistantContract.Navigator {

    override fun startVolumeUpService() {
        val audioManager = context.getSystemService(Context.AUDIO_SERVICE) as AudioManager
        audioManager.adjustVolume(AudioManager.ADJUST_RAISE, AudioManager.FLAG_PLAY_SOUND)
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