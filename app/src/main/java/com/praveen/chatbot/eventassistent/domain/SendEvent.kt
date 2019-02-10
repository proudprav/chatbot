package com.praveen.chatbot.eventassistent.domain

import io.reactivex.Single

class SendEvent(private val eventAssistantRepository: EventAssistantRepository) {

    fun run(event: Event): Single<EventAssistant> {
        return eventAssistantRepository.getEventAssistant(event)
    }

}