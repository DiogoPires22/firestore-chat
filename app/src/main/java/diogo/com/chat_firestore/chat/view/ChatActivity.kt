package diogo.com.chat_firestore.chat.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import diogo.com.chat_firestore.R
import diogo.com.chat_firestore.chat.presenter.ChatPresenter
import diogo.com.chat_firestore.data.chat.Message
import org.koin.android.ext.android.get
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class ChatActivity : AppCompatActivity(), ChatView {


    private val presenter: ChatPresenter by inject { parametersOf(this) }


    fun coco() = parametersOf(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        presenter.subscribeChatMessages()
    }

    override fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun showItem(item: Message) {
    }
}
