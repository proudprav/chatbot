package com.praveen.chatbot.eventassistent.data

import android.util.Log
import com.praveen.chatbot.eventassistent.domain.*
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*


class AssistantDataMapper  {
     fun map(input: EventAssistantApiResponse, event: Event): EventAssistant {
         val result = arrayListOf<String>()

         input.result.fulfillment.speech.let {
            if (it.isNotEmpty()) result.add(it)
        }

         input.result.fulfillment.messages
                 .map { message ->
                     message.payload?.apply { richText.map { it.text }.apply {
                         result.addAll(this) } }
                 }
        input.result.action.let {
            when(it){
                ActionTypeUtil.DATE -> {val c = Calendar.getInstance().getTime()
                    val df = SimpleDateFormat("dd-MMM-yyyy")
                result.add(df.format(c))}
                ActionTypeUtil.TIME-> {val c = Calendar.getInstance().getTime()
                    val df = SimpleDateFormat("hh:mm a")
                    result.add(df.format(c))}
                else -> Unit
            }

        }




        var action: AssistantActionType? = null
        input.result.action.run {
            try {
                action = AssistantActionType.valueOf(this)
            } catch (exception: Exception) {
                Log.e("exception", exception.toString())
            }
        }

        return EventAssistant(
                event = event,
                assistant = Assistant(
                        user = User("2", input.result.source),
                        result = result,
                        action = action?.let {
                            AssistantAction(it, input.result.actionParams)
                        }

                )
        )
    }

}