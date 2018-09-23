package diogo.com.chat_firestore.data.chat

import com.google.firebase.firestore.DocumentSnapshot
import diogo.com.chat_firestore.data.Converter
import java.util.*


class MessageConverter : Converter<Message> {
    override fun toDatabase(item: Message): Map<String, Any> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun toMemory(item: DocumentSnapshot): Message {
        return Message(item.id, item.get(Message.FIELD_MESSAGE) as String, item.get(Message.FIELD_TIMESTAMP) as Date)
    }


}