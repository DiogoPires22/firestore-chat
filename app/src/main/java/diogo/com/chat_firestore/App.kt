package diogo.com.chat_firestore

import android.app.Application
import diogo.com.chat_firestore.di.chatModule
import diogo.com.chat_firestore.di.commonModule
import org.koin.android.ext.android.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin(this, listOf(commonModule, chatModule))
    }
}