package com.praveen.chatbot.eventassistent.data

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
        @SerializedName("speech")
        val speech: String,

        @SerializedName("messages")
        val messages: List<Message>
)

class Message(
        @SerializedName("payload")
        val payload: Payload?
)
class Payload(
        @SerializedName("richtext")
        val richText: List<RichText>
)
class RichText(
        @SerializedName("text")
        val text: String
)

object ActionTypeUtil{
         const val DATE = "DATE"
         const val TIME = "TIME"

}


