package com.praveen.chatbot.event_assistent.view

import com.praveen.chatbot.event_assistent.domain.User

open class AssistantVM(open val user: User)

data class InputAssistant(override val user: User, val input: String) : AssistantVM(user)

data class OutputAssistant(override val user: User, val output: String) : AssistantVM(user)

data class EventAssistantViewModel(
        val eventTextFieldText: String,
        val response: List<AssistantVM>
)

