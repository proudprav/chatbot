package com.praveen.chatbot.eventassistent.view

import android.util.Log
import com.praveen.chatbot.eventassistent.data.EventAssistantRepositoryImpl
import com.praveen.chatbot.eventassistent.data.UserRepositoryImpl
import com.praveen.chatbot.eventassistent.domain.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable

class EventAssistantPresenter(val navigator: AssistantContract.Navigator) : AssistantContract.Presenter<AssistantContract.View> {
    private val eventRepo = EventAssistantRepositoryImpl()
    private lateinit var view: AssistantContract.View
    private val sendEvent = SendEvent(eventRepo)
    private val mapper: EventAssistantToAssistantViewModelMapper = EventAssistantToAssistantViewModelMapper()
    private val getAssistant = GetAssistant(eventRepo)
    private val getUser: GetLoggedInUser = GetLoggedInUser(UserRepositoryImpl())
    private val disposables = CompositeDisposable()

    override fun setView(view: AssistantContract.View) {
        this.view = view
    }

    override fun start() {
        getAssistant.run()
                .map { eventAssistant ->  mapper.map(eventAssistant) }
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe{disposable -> disposables.add(disposable)}
                .subscribe({view.render(it)}){error -> Log.e("error", error.toString())}
    }

    override fun onEventClicked(eventName: String) {
        sendEvent.run(Event(eventName, getUser.run()))
                .doOnSubscribe{disposables.add(it)}
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ handleAssistantAction(it)})
                { error -> Log.e("sendEvent", " error $error") }
    }

    private fun handleAssistantAction(eventAssistant: EventAssistant) {
        eventAssistant.assistant?.run {
            this.action?.run {
                when(actionType) {
                    AssistantActionType.VOLUME_UP -> navigator.startVolumeUpService()
                    AssistantActionType.VOLUME_DOWN -> navigator.startVolumeDownService()
                    AssistantActionType.VOLUME_MUTE -> navigator.startVolumeMuteService()
                    AssistantActionType.YOUTUBE -> params?.apply { navigator.startYouTube(this) }
                    else -> Log.e("presenter", " invalid action type")
                }
            }
        }
    }

    override fun destroy() {
        disposables.dispose()
    }


}