package com.praveen.chatbot.event_assistent.domain

import io.reactivex.Observable
import io.reactivex.Single


interface EventAssistantRepository {
    fun getEventAssistant(event: Event) : Single<EventAssistant>
    fun getAssistant(): Observable<List<EventAssistant>>
}