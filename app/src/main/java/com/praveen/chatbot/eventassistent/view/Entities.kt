package com.praveen.chatbot.eventassistent.view

import com.praveen.chatbot.eventassistent.domain.User

open class AssistantVM(open val user: User)

data class InputAssistant(override val user: User, val input: String) : AssistantVM(user)

data class OutputAssistant(override val user: User, val output: List<String>) : AssistantVM(user)

data class EventAssistantViewModel(
        val eventTextFieldText: String,
        val response: List<AssistantVM>
)

