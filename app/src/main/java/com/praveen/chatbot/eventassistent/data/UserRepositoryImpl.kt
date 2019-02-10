package com.praveen.chatbot.eventassistent.data

import com.praveen.chatbot.eventassistent.domain.User
import com.praveen.chatbot.eventassistent.domain.UserRepository

class UserRepositoryImpl : UserRepository {
    override fun getUser(): User {
        return User("1", name = "Praveen")
    }
}
