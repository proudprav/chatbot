package com.praveen.chatbot.event_assistent.data

import android.util.Log
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.android.extension.responseJson
import com.google.gson.Gson
import com.praveen.chatbot.event_assistent.domain.*
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.subjects.PublishSubject
import org.json.JSONObject
import java.lang.Exception

class EventAssistantRepositoryImpl : EventAssistantRepository {
    private val assistantsSubject = PublishSubject.create<List<EventAssistant>>()
    private val assistantsResults = arrayListOf<EventAssistant>()


    override fun getEventAssistant(event: Event): Single<EventAssistant> {
        val request = Fuel.get("query", listOf("query" to event.name))
        return Single.create {
            request.run {
                responseJson { _, _, result ->
                    run {
                        val assistant = parseEventResponse(result.get().obj())
                        val eventAssistant = EventAssistant(event, assistant)
                        assistantsResults.add(eventAssistant)
                        assistantsSubject.onNext(assistantsResults)
                        it.onSuccess(eventAssistant)
                    }
                }
            }

        }
    }

    override fun getAssistant(): Observable<List<EventAssistant>> {
        return assistantsSubject
    }


    private fun parseEventResponse(response: JSONObject): Assistant {
        val gson = Gson()
        val apiResponse = gson.fromJson(response.toString(), EventAssistantApiResponse::class.java)
        val assistants = apiResponse.result.fulfillment.messages.map { it.speech }
        var action: AssistantActionType? = null
        apiResponse.result.action.run {
            try {
                action = AssistantActionType.valueOf(this)
            } catch (exception: Exception) {
                Log.e("exception", exception.toString())
            }
        }

        return Assistant(User("2", name = apiResponse.result.source), assistants,
                action = action?.let {
                    AssistantAction(it, apiResponse.result.actionParams)
                })
    }

}