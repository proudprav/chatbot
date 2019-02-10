package com.praveen.chatbot.event_assistent.domain

class GetLoggedInUser constructor(private val userRepository: UserRepository) {
    fun run(): User {
        return userRepository.getUser()
    }
}