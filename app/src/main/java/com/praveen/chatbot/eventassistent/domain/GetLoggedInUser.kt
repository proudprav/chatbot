package com.praveen.chatbot.eventassistent.domain

class GetLoggedInUser constructor(private val userRepository: UserRepository) {
    fun run(): User {
        return userRepository.getUser()
    }
}