package diogo.com.chat_firestore.chat.view

import diogo.com.chat_firestore.BaseView
import diogo.com.chat_firestore.data.chat.Message


interface GenericView{
    fun getBaseView(): BaseView
}

interface ChatView:GenericView {
    fun showItem(item: Message)
}
