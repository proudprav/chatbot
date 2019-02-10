package com.praveen.chatbot.eventassistent.domain

data class Event ( val name: String, val user: User)

data class EventAssistant(val event: Event, val assistant: Assistant? = null)

data class Assistant(val user: User, val result: List<String>,val  action: AssistantAction? = null)

data class AssistantAction(val actionType: AssistantActionType, val params: Map<String, String>?)

data class User(val id: String, val name: String, val image: String? = null)

enum class AssistantActionType{
    YOUTUBE,
    VOLUME_UP,
    VOLUME_DOWN,
    VOLUME_MUTE,
    DATE,
    OTHER
}