package com.praveen.chatbot

import android.app.Application
import com.github.kittinunf.fuel.core.FuelManager
import java.util.*

class AssistantApplication : Application() {

    private val ACCESS_TOKEN = "0b93313ca4c5407593f3e35376f3c849"

    override fun onCreate() {
        super.onCreate()

        FuelManager.instance.baseHeaders = mapOf("Authorization" to "Bearer $ACCESS_TOKEN")

        FuelManager.instance.basePath = "https://api.dialogflow.com/v1"

        FuelManager.instance.baseParams = listOf(
                "v" to "20170712",                  // latest protocol
                "sessionId" to UUID.randomUUID(),   // random Id
                "lang" to "en"                      // English language
        )
    }
}