package diogo.com.chat_firestore.chat.view

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import diogo.com.chat_firestore.R
import diogo.com.chat_firestore.data.chat.Message
import kotlinx.android.synthetic.main.chat_item.view.*
import java.text.SimpleDateFormat

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