package com.praveen.chatbot.event_assistent.domain

import io.reactivex.Observable

class GetAssistant(private val repository: EventAssistantRepository) {

    fun run(): Observable<List<EventAssistant>> {
        return repository.getAssistant()
    }
}