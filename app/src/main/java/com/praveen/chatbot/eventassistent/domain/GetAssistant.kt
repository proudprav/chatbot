package com.praveen.chatbot.eventassistent.domain

import io.reactivex.Observable

class GetAssistant(private val repository: EventAssistantRepository) {

    fun run(): Observable<List<EventAssistant>> {
        return repository.getAssistant()
    }
}