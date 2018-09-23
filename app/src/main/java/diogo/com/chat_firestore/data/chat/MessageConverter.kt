package diogo.com.chat_firestore.data.chat

import com.google.firebase.firestore.DocumentSnapshot
import diogo.com.chat_firestore.data.Converter
import java.util.*


class MessageConverter : Converter<Message> {
    override fun toDatabase(item: Message): Map<String, Any> {
        val map = mutableMapOf<String, Any>()

        with(map) {
            put(Message.FIELD_MESSAGE, item.message)
            put(Message.FIELD_TIMESTAMP, item.timestamp)
            //put(Message.FIELD_USER, item.timestamp)
        }

        return map
    }

    override fun toMemory(item: DocumentSnapshot): Message {
        return Message(item.id, item.get(Message.FIELD_MESSAGE) as String, item.get(Message.FIELD_TIMESTAMP) as Date)
    }


}