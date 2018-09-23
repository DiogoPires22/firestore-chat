package diogo.com.chat_firestore.di

import diogo.com.chat_firestore.data.Converter
import diogo.com.chat_firestore.data.chat.ChatRepository
import diogo.com.chat_firestore.data.chat.ChatRepositoryImpl
import diogo.com.chat_firestore.data.chat.Message
import diogo.com.chat_firestore.data.chat.MessageConverter
import org.koin.dsl.module.module

val chatModule = module("chat") {

    factory<ChatRepository> { ChatRepositoryImpl(get()) }
    single<Converter<Message>> { MessageConverter() }
}

val commonModule = module("CommonModule") {
    //    single { FirebaseApp.getInstance()!! }
}