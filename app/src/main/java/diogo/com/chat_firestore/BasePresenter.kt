package diogo.com.chat_firestore

interface BasePresenter<T>{
    val view: T?
    fun onCreate()
    fun onResume()
    fun onPause()
    fun onDestroy()
    fun detachView()
    fun attachView(view:T)
}