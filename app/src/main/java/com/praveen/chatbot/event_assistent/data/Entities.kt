package com.praveen.chatbot.event_assistent.data

import com.google.gson.annotations.SerializedName

class EventAssistantApiResponse(
        @SerializedName("result")
        val result: Result)


class Result(
        @SerializedName("source")
        val source: String,

        @SerializedName("action")
        val action: String,

        @SerializedName("parameters")
        val actionParams: Map<String, String>,

        @SerializedName("fulfillment")
        val fulfillment: Fulfillment
)

class Fulfillment(
        @SerializedName("messages")
        val messages: List<Message>
)

class Message(
        @SerializedName("speech")
        val speech: String
)