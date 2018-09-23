package diogo.com.chat_firestore

import android.app.Application
import diogo.com.chat_firestore.di.appModules
import org.koin.android.ext.android.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin(this, appModules)
    }
}