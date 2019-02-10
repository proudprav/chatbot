package com.praveen.chatbot.eventassistent.domain

interface UserRepository {
    fun getUser(): User
}