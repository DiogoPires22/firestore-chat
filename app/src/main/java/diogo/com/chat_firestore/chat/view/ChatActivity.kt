package diogo.com.chat_firestore.chat.view

import android.os.Bundle
import android.support.v7.widget.StaggeredGridLayoutManager
import diogo.com.chat_firestore.AbstractActivity
import diogo.com.chat_firestore.R
import diogo.com.chat_firestore.chat.presenter.ChatPresenter
import diogo.com.chat_firestore.data.chat.Message
import kotlinx.android.synthetic.main.activity_chat.*
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf


class ChatActivity : AbstractActivity(), ChatView {


    private val presenter: ChatPresenter by inject { parametersOf(this) }

    private lateinit var listAdapter: ChatAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        presenter.subscribeChatMessages()

        configList()

        configActions()
    }

    override fun getBaseView() = this

    override fun showItem(item: Message) {
        listAdapter.listItems.add(item)
        listAdapter.notifyItemChanged(listAdapter.listItems.size)

        chat_list.postDelayed({
            chat_list.smoothScrollToPosition(listAdapter.listItems.size)
        }, 200)
    }

    private fun configActions() {
        chat_btn.setOnClickListener({
            if (chat_box.text.isNullOrEmpty()) {
                showMessage("Set text in input to send a Message!")
                return@setOnClickListener
            }

            presenter.sendMessage(chat_box.text.toString(),
                    { message ->

                        submitOnUiThread { chat_box.text.clear() }

                    }
                    , { error -> showMessage("Error") })
        })
    }

    private fun configList() {
        listAdapter = ChatAdapter(context = this.applicationContext)
        chat_list.adapter = listAdapter

        val layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
        chat_list.layoutManager = layoutManager
    }
}
