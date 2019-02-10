package com.praveen.chatbot.event_assistent.view

interface AssistantContract {
    interface View {
        fun render(viewModel: EventAssistantViewModel)
    }

    interface Presenter<T: View> {
        fun setView(view: T)
        fun start()
        fun onEventClicked(eventName: String)
        fun destroy()
    }

    interface Navigator {
        fun startVolumeUpService()
        fun startYouTube( params : Map<String, String>)
    }
}