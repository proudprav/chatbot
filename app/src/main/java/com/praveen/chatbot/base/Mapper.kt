package com.praveen.chatbot.base

interface Mapper<in T, out R> {
    fun map(input: T): R
}