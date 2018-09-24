package diogo.com.chat_firestore.auth.presenter


interface AuthPresenter {

    fun login(username: String, onComplete: () -> Unit, onError: (error: Throwable) -> Unit)

}