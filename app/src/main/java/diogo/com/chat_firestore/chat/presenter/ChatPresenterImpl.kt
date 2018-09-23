package diogo.com.chat_firestore.chat.presenter

import diogo.com.chat_firestore.AbstractPresenter
import diogo.com.chat_firestore.chat.view.ChatView
import diogo.com.chat_firestore.data.chat.ChatRepository
import diogo.com.chat_firestore.data.chat.Message

class ChatPresenterImpl(val chatView: ChatView?, private val chatRepository: ChatRepository) : AbstractPresenter(chatView), ChatPresenter {

    private val messageList = mutableListOf<Message>()

    override fun subscribeChatMessages() {
        chatRepository.getAndSubscribe(
                { item ->
                    if (!exist(item.id)) {
                        messageList.add(item)
                        chatView?.showItem(item)
                    }
                },
                { error -> chatView?.showMessage(error.message ?: error.localizedMessage) })
    }

    private fun exist(id: String): Boolean {
        return messageList.find { it.id == id } != null
    }

}