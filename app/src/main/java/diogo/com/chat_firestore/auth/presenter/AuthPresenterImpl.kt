package diogo.com.chat_firestore.auth.presenter

import android.content.SharedPreferences
import diogo.com.chat_firestore.AbstractPresenter
import diogo.com.chat_firestore.auth.view.AuthView
import diogo.com.chat_firestore.data.user.User
import diogo.com.chat_firestore.data.user.UserRepository
import user

class AuthPresenterImpl(val authView: AuthView?, private val userRepository: UserRepository, val pref: SharedPreferences) : AbstractPresenter(authView?.getBaseView()), AuthPresenter {


    override fun login(username: String, onComplete: () -> Unit, onError: (error: Throwable) -> Unit) {
        authView?.changeLoadingStatus(true)
        val user = User(name = username)
        userRepository.login(username,
                {
                    val user = userRepository.findById(id = it)
                    pref.user = it
                    authView?.finishLogin()
                    authView?.changeLoadingStatus(false)
                },
                {
                    authView?.changeLoadingStatus(false)
                    view?.showMessage("Error, Try later")
                })


    }
}