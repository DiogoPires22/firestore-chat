package diogo.com.chat_firestore.di

import diogo.com.chat_firestore.auth.presenter.AuthPresenter
import diogo.com.chat_firestore.auth.presenter.AuthPresenterImpl
import diogo.com.chat_firestore.auth.view.AuthView
import diogo.com.chat_firestore.chat.presenter.ChatPresenter
import diogo.com.chat_firestore.chat.presenter.ChatPresenterImpl
import diogo.com.chat_firestore.chat.view.ChatView
import diogo.com.chat_firestore.data.Converter
import diogo.com.chat_firestore.data.chat.ChatRepository
import diogo.com.chat_firestore.data.chat.ChatRepositoryImpl
import diogo.com.chat_firestore.data.chat.Message
import diogo.com.chat_firestore.data.chat.MessageConverter
import diogo.com.chat_firestore.data.user.AuthConverter
import diogo.com.chat_firestore.data.user.User
import diogo.com.chat_firestore.data.user.UserRepository
import diogo.com.chat_firestore.data.user.UserRepositoryImpl
import org.koin.dsl.module.module


val chatModule = module("chat") {

    factory<ChatPresenter> { (view: ChatView) -> ChatPresenterImpl(view, get()) }
    single<ChatRepository> { ChatRepositoryImpl(get()) }
    single<Converter<Message>> { MessageConverter() }
}


val authModule = module("auth") {
    factory<AuthPresenter> { (view: AuthView) -> AuthPresenterImpl(view, get()) }
    single<UserRepository> { UserRepositoryImpl(get()) }
    single<Converter<User>> { AuthConverter() }
}

val commonModule = module("CommonModule") {
}

val appModules = listOf(chatModule, commonModule, authModule)