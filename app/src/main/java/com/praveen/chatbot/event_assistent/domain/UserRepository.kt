package com.praveen.chatbot.event_assistent.domain

interface UserRepository {
    fun getUser(): User
}