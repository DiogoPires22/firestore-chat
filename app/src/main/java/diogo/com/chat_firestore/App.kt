package diogo.com.chat_firestore

import android.app.Application
import com.google.firebase.FirebaseApp
import diogo.com.chat_firestore.di.appModules
import org.koin.android.ext.android.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        FirebaseApp.initializeApp(applicationContext)
        startKoin(this, appModules)
    }
}