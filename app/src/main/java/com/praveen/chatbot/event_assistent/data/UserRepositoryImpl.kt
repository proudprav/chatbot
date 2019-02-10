package com.praveen.chatbot.event_assistent.data

import com.praveen.chatbot.event_assistent.domain.User
import com.praveen.chatbot.event_assistent.domain.UserRepository

class UserRepositoryImpl : UserRepository {
    override fun getUser(): User {
        return User("1", name = "Abhishek")
    }
}
