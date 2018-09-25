package diogo.com.chat_firestore.chat.presenter

import diogo.com.chat_firestore.AbstractPresenter
import diogo.com.chat_firestore.chat.view.ChatView
import diogo.com.chat_firestore.data.chat.ChatRepository
import diogo.com.chat_firestore.data.chat.Message
import java.util.*

class ChatPresenterImpl(val chatView: ChatView?, private val chatRepository: ChatRepository) : AbstractPresenter(chatView?.getBaseView()), ChatPresenter {


    private val messageList = mutableListOf<Message>()

    override fun subscribeChatMessages() {
        chatRepository.getAndSubscribe(
                { item ->
                    if (!exist(item.id)) {
                        messageList.add(item)
                        chatView?.showItem(item)
                    }
                },
                { error -> chatView?.getBaseView()?.showMessage(error.message ?: error.localizedMessage) })
    }

    private fun exist(id: String): Boolean {
        return messageList.find { it.id == id } != null
    }

    override fun sendMessage(message: String, onComplete: (id: String) -> Unit, onError: (throwable: Throwable) -> Unit) {
        Thread {
            val m: Message? = Message(message = message, timestamp = Date())

            chatRepository.create(m!!)?.let(onComplete) ?: run {
                onError(Exception("Create Error"))
            }
        }.start()
    }

}