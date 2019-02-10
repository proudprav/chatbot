package com.praveen.chatbot.eventassistent.data

import com.praveen.chatbot.eventassistent.domain.*
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.subjects.PublishSubject

class EventAssistantFakeRepo : EventAssistantRepository {

    private val assistantsSubject = PublishSubject.create<List<EventAssistant>>()
    private val assistantsResults = arrayListOf<EventAssistant>()

    override fun getEventAssistant(event: Event): Single<EventAssistant> {
        return Single.create{
            assistantsResults.apply {
                val event = EventAssistant(event, Assistant(User("2", name = "server"),
                        listOf("help", "music", "other")))
                add(event)
                it.onSuccess(event)
            }.also {
                assistantsSubject.onNext(it)
            }
        }
    }

    override fun getAssistant(): Observable<List<EventAssistant>> {
        return assistantsSubject
    }
}