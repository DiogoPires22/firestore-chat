package diogo.com.chat_firestore.chat.view

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import diogo.com.chat_firestore.R
import diogo.com.chat_firestore.chat.presenter.ChatPresenter
import diogo.com.chat_firestore.data.chat.Message
import kotlinx.android.synthetic.main.activity_chat.*
import kotlinx.android.synthetic.main.chat_item.view.*
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf
import java.text.SimpleDateFormat

class ChatActivity : AppCompatActivity(), ChatView {


    private val presenter: ChatPresenter by inject { parametersOf(this) }

    private lateinit var listAdapter: ChatAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        presenter.subscribeChatMessages()

        configList()
    }

    override fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun showItem(item: Message) {
        listAdapter.listItems.add(item)
        listAdapter.notifyItemChanged(listAdapter.listItems.size)

        chat_list.postDelayed({
            chat_list.smoothScrollToPosition(listAdapter.listItems.size)
        }, 200)
    }

    private fun configList() {
        listAdapter = ChatAdapter(context = this.applicationContext)
        chat_list.adapter = listAdapter

        val layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
        chat_list.layoutManager = layoutManager
    }


    class ChatAdapter(val listItems: MutableList<Message> = mutableListOf(), private val context: Context) : RecyclerView.Adapter<ChatAdapter.ChatViewHolder>() {


        private val dateFormat = SimpleDateFormat("dd/MM/yy hh:mm")

        override fun getItemCount(): Int {
            return listItems.size
        }

        override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
            listItems[position].let { item ->
                holder.senderInfo.text = "chubaca (${dateFormat.format(item.timestamp)}) :"
                holder.message.text = item.message
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
            val view = LayoutInflater.from(context).inflate(R.layout.chat_item, parent, false)

            return ChatViewHolder(view)
        }

        class ChatViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val senderInfo = itemView.sender_info

            val message = itemView.message_text

        }


    }
}
