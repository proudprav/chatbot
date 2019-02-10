package com.praveen.chatbot.eventassistent.domain

import io.reactivex.Observable
import io.reactivex.Single


interface EventAssistantRepository {
    fun getEventAssistant(event: Event) : Single<EventAssistant>
    fun getAssistant(): Observable<List<EventAssistant>>
}