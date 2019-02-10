package com.praveen.chatbot.eventassistent.data

import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.android.extension.responseJson
import com.google.gson.Gson
import com.praveen.chatbot.eventassistent.domain.*
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.subjects.PublishSubject
import org.json.JSONObject

class EventAssistantRepositoryImpl : EventAssistantRepository {
    private val assistantsSubject = PublishSubject.create<List<EventAssistant>>()
    private val assistantsResults = arrayListOf<EventAssistant>()
    private val mapper = AssistantDataMapper()


    override fun getEventAssistant(event: Event): Single<EventAssistant> {
        val request = Fuel.get("query", listOf("query" to event.name))
        return Single.create {
            request.run {
                responseJson { _, _, result ->
                    run {
                        val apiResponse = parseEventResponse(result.get().obj())
                        val eventAssistant = mapper.map(apiResponse, event)
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


    private fun parseEventResponse(response: JSONObject): EventAssistantApiResponse {
        val gson = Gson()
        return gson.fromJson(response.toString(), EventAssistantApiResponse::class.java)
    }

}