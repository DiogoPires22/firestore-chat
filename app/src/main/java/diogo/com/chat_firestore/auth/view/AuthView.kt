package diogo.com.chat_firestore.auth.view

import diogo.com.chat_firestore.chat.view.GenericView

interface AuthView : GenericView {
    fun changeLoadingStatus(show: Boolean)
    fun finishLogin()
}