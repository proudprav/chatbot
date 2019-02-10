package com.praveen.chatbot.event_assistent.platform

import android.graphics.BitmapFactory
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.github.bassaer.chatmessageview.model.ChatUser
import com.github.bassaer.chatmessageview.model.Message
import com.github.bassaer.chatmessageview.view.ChatView
import com.praveen.chatbot.R
import com.praveen.chatbot.event_assistent.view.*

class EventAssistantActivity : AppCompatActivity(), AssistantContract.View {
    private val presenter = EventAssistantPresenter(AssistantNavigator(this))
    private lateinit var assistantView: ChatView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter.setView(this)
        assistantView = findViewById(R.id.my_chat_view)
    }

    override fun onStart() {
        super.onStart()
        presenter.start()
        assistantView.setOnClickSendButtonListener(View.OnClickListener {
            presenter.onEventClicked(assistantView.inputText)
        })
    }

    override fun render(viewModel: EventAssistantViewModel) {
        assistantView.getMessageView().removeAll()
        assistantView.inputText = viewModel.eventTextFieldText
        viewModel.response.forEach { assistant ->
            when (assistant) {
                is InputAssistant ->
                    assistantView.send(
                            Message.Builder().apply {
                                setUser(ChatUser(assistant.user.id.toInt(), assistant.user.name,
                                        BitmapFactory.decodeResource(resources,
                                                R.drawable.ic_account_circle)))
                                setText(assistant.input)
                            }.build()
                    )

                is OutputAssistant -> {
                    assistantView.send(
                            Message.Builder().apply {
                                setUser(ChatUser(assistant.user.id.toInt(), assistant.user.name,
                                        BitmapFactory.decodeResource(resources,
                                                R.drawable.ic_account_circle)))
                                setText(assistant.output)
                                setRight(true)
                            }.build()
                    )

                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.destroy()
    }

}