package com.praveen.chatbot.eventassistent.view

import com.praveen.chatbot.base.Mapper
import com.praveen.chatbot.eventassistent.domain.EventAssistant

class EventAssistantToAssistantViewModelMapper : Mapper<List<EventAssistant>,
        EventAssistantViewModel> {
    override fun map(input: List<EventAssistant>): EventAssistantViewModel {
        val assistantViewModels: List<AssistantVM> = input.flatMap {
            val list = arrayListOf<AssistantVM>()
            list.add(InputAssistant(it.event.user, it.event.name))
            list.apply {
                it.assistant?.let { assistant ->
                    list.add(OutputAssistant(assistant.user, assistant.result))
                }
            }
            list
        }
        return EventAssistantViewModel("", assistantViewModels)
    }
}
