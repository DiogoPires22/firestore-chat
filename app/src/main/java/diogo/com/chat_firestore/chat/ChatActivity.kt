package diogo.com.chat_firestore.chat

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.google.firebase.FirebaseApp
import diogo.com.chat_firestore.R
import diogo.com.chat_firestore.data.chat.ChatRepository
import org.koin.android.ext.android.inject

class ChatActivity : AppCompatActivity() {

    private val rep: ChatRepository by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)
        FirebaseApp.initializeApp(applicationContext)

        rep.getAndSubscribe({ item ->
            Log.d("PIPITU",item.message)

        }, {

        })
    }
}
